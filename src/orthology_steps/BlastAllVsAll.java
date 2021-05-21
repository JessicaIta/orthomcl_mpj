package orthology_steps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class BlastAllVsAll {
	
	public void executeBlast(int rank) {
		
		String nameArq = "goodProteins_"+rank;
		
		String bancoBlast = "makeblastdb -in "+nameArq+".fasta "
				+ "-title "+nameArq+" -dbtype prot "
				+ "-out "+nameArq+" -parse_seqids";
		
		// Execução do formato tabular
		String comandoBlast = "blastp -query "+nameArq+".fasta "
				+ "-db "+nameArq+" -outfmt 6 "
				+ "-out resultBlast_"+rank+" -evalue 1e-5";
		
		try {
			
			//Criação do banco do Blast
			Process execBanco = Runtime.getRuntime().exec(bancoBlast);
			
			if( execBanco.waitFor() == 0 ) {
				System.out.println("Banco criado");
			}
			
			//Execução do Blast
			Process execBlast = Runtime.getRuntime().exec(comandoBlast);

			InputStream in = execBlast.getErrorStream();
			Scanner scan = new Scanner(in);

			while( scan.hasNext() ) {
				System.out.println( scan.nextLine());
			}

			if( execBlast.waitFor() == 0 ) {
				System.out.println("programa finalizado");
			}
			
			scan.close();
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
