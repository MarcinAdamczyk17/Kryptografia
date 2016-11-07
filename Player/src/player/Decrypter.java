package player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.Key;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.crypto.spec.SecretKeySpec;

import decrypter.CryptoUtils;
import decrypter.PreCrypt;
import javafx.scene.media.Media;

public class Decrypter {
	
	private static String keyString = "d7a7ae6c4c2ccbc15d5934478e6479c5";
	
	public Decrypter(){
		decryptStarting();
		decryptFile();
	}

	private void decryptFile() {
		
		try {
			Scanner scanner = new Scanner(new File("play/starting.txt"));
			
			String[] params = new String[4];
			
			for (int i = 0; i < 4; ++i){
				params[i] = scanner.nextLine();
			}
			
			scanner.close();
			
			Scanner scan = new Scanner(new File("play/playlist.txt"));
			List<String> playlist = new LinkedList<String>();			
			
			while(scan.hasNextLine()){
				playlist.add(scan.nextLine());
			}
			
			scan.close();
			int i = 1;
			for(String song : playlist){
				PrintWriter writer = new PrintWriter("play/conf.txt");
				writer.println("AES");
				writer.println("CBC");
				writer.println(params[0]);
				writer.println(params[1]);
				writer.println("D");
				writer.println("play/" + i + ".enc");
				writer.println("play/" + i + ".mp3");
				
				writer.close();

				CryptoUtils.setKSPassword(params[2].toCharArray());
				CryptoUtils.setKeyPassword(params[3].toCharArray());
				
				PreCrypt.start(new File("play/conf.txt"));
				Startup.playlist.add(new Media("file:/home/marcin/Programming/krypto/Kryptografia/Player/play/" + i + ".mp3"));
				++i;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void decryptStarting() {
		
		byte[] keyBytes = hexStringToByteArray(keyString);
		
		SecretKeySpec sks = new SecretKeySpec(keyBytes, "AES");
		
		CryptoUtils.setKey(sks);
		
		PreCrypt.start(new File("play/decryptStarting.txt"));
		System.out.println(keyString.length());
		
	}

	private static byte[] hexStringToByteArray(String s) {
		int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
}
