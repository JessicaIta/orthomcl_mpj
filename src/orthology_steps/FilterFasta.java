package orthology_steps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FilterFasta {

	public boolean filterFasta(int rank) {

		boolean result = false;

		//Comando do filterFasta
		String comando ="orthomclFilterFasta " 
				+ "compliant_fasta_"+ rank 
				+ "/ 10 20 [goodProteins_"+ rank
				+ " poorProteins_"+rank+"]";
		try {

			Process exec = Runtime.getRuntime().exec(comando);

			InputStream in = exec.getErrorStream();
			Scanner scan = new Scanner(in);

			while( scan.hasNext() ) {
				System.out.println( scan.nextLine());
			}

			if( exec.waitFor() == 0 ) {
				System.out.println("programa finalizado");
				result = true;
			}
			
			scan.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}


}
