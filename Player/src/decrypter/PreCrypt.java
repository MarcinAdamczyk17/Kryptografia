package decrypter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PreCrypt {

	public static void start(File config){
		
		
		@SuppressWarnings("resource")
		Scanner scanner = null;
		try {
			scanner = new Scanner(config);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] params = new String[7];
		for(int i = 0; i < 7; ++i){
			params[i] = scanner.nextLine();
			System.out.println(params[i]);
		}
		
		CryptoUtils cu = new CryptoUtils();
		cu.start(params);

	}
}
