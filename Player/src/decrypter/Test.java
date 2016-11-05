package decrypter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {
	
	public static boolean password = false;
	public static void main(String[] args) throws IOException {
		
		File config = new File("src/decrypter/conf.txt");
		
		Scanner scan = new Scanner(config);
		
		Password.start();
		
		if(password){
			System.out.println("work");
		}
		else{
			System.out.println("nope");
		}
		
		
		while(scan.hasNextLine()){
			System.out.println(scan.nextLine());
		}
		
		CryptoUtils.start();

		
	}

}
