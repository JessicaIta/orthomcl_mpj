package orthology_steps;

import mpi.*;
import java.util.Scanner;
import java.util.List;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;

public class Orthomcl {

	public static void main(String args[]) throws Exception {
		MPI.Init(args);
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();

		String arq = "arquivos_fasta.txt";
		
		String principalFasta = args[0];
		
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
			BlastAllVsAll bl = new BlastAllVsAll()
			bl.executeBlast(rank);
			
		}else {
			System.out.println("Não foi possível executar o BLAST.");
		}

	
		MPI.Finalize();

	}

}
