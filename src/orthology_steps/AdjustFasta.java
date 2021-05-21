package orthology_steps;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class AdjustFasta {
	
	private boolean executeComando(String comando, boolean result) {
		
		Process exec;
		try {
			exec = Runtime.getRuntime().exec(comando);
			
			InputStream in = exec.getErrorStream();
		    Scanner scan = new Scanner(in);
		 
		    while( scan.hasNext() ) {
		        System.out.println( scan.nextLine());
		    }
		    
		    if( exec.waitFor() == 0 ) {
		        System.out.println("Programa executado");
		        result = true;
		    }
		    
			scan.close();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
		
	}
	
	public boolean adjustFasta(String nameFile, int rank, 
			String pFasta) {
		
		Boolean execPrimeiro, execPrincipal, result;
		
		Path path = Paths.get(nameFile);
		
		List<String> linhasArquivo;
		
		try {
			linhasArquivo = Files.readAllLines(path);
			
			String arqFasta = linhasArquivo.get(rank);
			
			System.out.println("O arquivo será processado " + arqFasta);
			
			// Criando diretório my_orthomcl_dir

			String dir = "compliant_fasta_"+rank;

			Path path_compliant = Paths.get(dir);

		    //java.nio.file.Files;
		    Files.createDirectories(path_compliant);

		    System.out.println("Diretório foi criado!");  

			String comando ="orthomclAdjustFasta " 
					+ dir + "/"
					+arqFasta.substring(0, 3)
					+ " fasta/"+arqFasta+" 1";
			
			String principal ="orthomclAdjustFasta " 
					+ dir + "/"
					+pFasta.substring(0, 3)
					+ " fasta/"+pFasta+" 1";
			
			// Executando o primeiro
			execPrimeiro= executeComando(comando, false);
			
			//Executando o principal
			execPrincipal = executeComando(principal, false);
		    
			if (execPrimeiro && execPrincipal) {
				result = true;
			}else {
				result = false;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		return result;
		
	}

}
