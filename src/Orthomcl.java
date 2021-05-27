import mpi.*;


public class Orthomcl {

	public static void main(String args[]) throws Exception{
		MPI.Init(args);
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();
		double timeInitial, timeFinal;
		
		timeInitial = MPI.Wtime();

		String arq = "arquivos_fasta.txt";
		
		String principalFasta = args[3];
		
		System.out.println(principalFasta);
		
		boolean adjustFasta, filterFasta = false;
		
		//Adjustfasta
		AdjustFasta adj = new AdjustFasta();
		adjustFasta = adj.adjustFasta(arq, rank, principalFasta);
		
		if (adjustFasta){
			
			//FilterFasta
			FilterFasta ff = new FilterFasta();
			filterFasta = ff.filterFasta(rank);
			
		}else {
			System.out.println("Não foi possível completar o FilterFasta.");
		}
		
		if (filterFasta) {
			
			//BLAST
			BlastAllVsAll bl = new BlastAllVsAll();
			bl.executeBlast(rank);
			
		}else {
			System.out.println("Não foi possível executar o BLAST.");
		}
		
		timeFinal = MPI.Wtime();
		
		System.out.println("Foram gastos " +(timeFinal - timeInitial)+" segundos.");

	
		MPI.Finalize();

	}

}
