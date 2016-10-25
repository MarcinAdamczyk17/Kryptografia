package main;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.NoSuchPaddingException;

public class Test {

	
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchProviderException{
		
		Runnable[] decrypters = new Runnable[4];
		Thread[] threads = new Thread[4];
		
		for(int i = 0; i < 4; ++i){
			decrypters[i] = new Decrypt();
		}
		
		for(int i = 0; i < 4; ++i){
			threads[i] = new Thread(decrypters[i], Integer.toString(i));
		}
		
		for(int i = 0; i < 4; ++i){
			threads[i].start();
		}
		
		
	}
}
