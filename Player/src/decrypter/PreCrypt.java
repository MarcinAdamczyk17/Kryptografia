package decrypter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PreCrypt {

	public static void start(File config){
		
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(config);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String[] params = new String[7];
		for(int i = 0; i < 7; ++i){
			params[i] = scanner.nextLine();
			System.out.println(params[i]);
		}
		
		CryptoUtils.start(params);

	}
}
