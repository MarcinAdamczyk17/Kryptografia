package decrypter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PreCrypt {

	public static void start(){
		File config = new File("src/decrypter/conf.txt");
		
		@SuppressWarnings("resource")
		Scanner scanner = null;
		try {
			scanner = new Scanner(config);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(scanner.hasNextLine()){
			System.out.println(scanner.nextLine());
		}
		
		CryptoUtils.start();

	}
}
