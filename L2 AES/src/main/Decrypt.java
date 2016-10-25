package main;

import java.awt.Toolkit;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt implements Runnable{

	byte[] iv = IV("6cc114247bed5862981e103814839b32");
	String b64 = 
	"IHEhNncPJoThwM4F6AdVEybQrWc4n6OwQwYHS5FP1+7nztqddxOhLqRlJ3/uUuQkfn1w4TG+VOENfXptzCLUbw==";
	String keysuff = "0f86f77cae51f732a4066836723d0b79ef7e39ae70560c2930d10cb6";
	Decoder dec = Base64.getDecoder();
	byte[] decoded = dec.decode(b64.getBytes());
	IvParameterSpec ivs = new IvParameterSpec(iv);
	Cipher c;
	
	public void decodeMsg(String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException{
		
		byte[] kv = hexStringToByteArray(key);
		
		
	
		SecretKeySpec sks = new SecretKeySpec(kv, "AES");
	
		c.init(Cipher.DECRYPT_MODE, sks, ivs);
			
		byte[] end = c.update(decoded);
		if(check(end)){
			Toolkit.getDefaultToolkit().beep();
			printb(end);
			System.out.println("found for key: " + key);
		}
		
	}
	
	public byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	private boolean check(byte[] end) {
		int max = end.length >> 4;
	    int counter = 0;
		for(int i = 0; i < end.length; ++i){
			
			if(end[i] > 0) 
				continue;
			else if (counter > max)
				return false;
			else
				counter++;
		}
		return true;
	}
	
	public void printb(byte[] b){
		for(int i = 0; i < b.length; ++i){
			System.out.print((char)b[i] + " ");
			
		}
		System.out.println();
	}
	
	private byte[] IV(String iv){
		
		byte[] result = new byte[iv.length() / 2];
		
		for(int i = 0; i < result.length; ++i){
			result[i] = (byte) Integer.parseInt(iv.substring(2*i, 2*i + 2) , 16);
		}
		
		return result;
	}

	@Override
	public void run() {
		int id = Integer.parseInt(Thread.currentThread().getName());
		try {
			c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		System.out.println("Thread nr " + id +  " started");
		for (int a = 4 * id; a < 4 * id + 4; ++a){
			System.out.println("Thread nr " + id + " current first char: " + Integer.toHexString(a));
			for (int b = 0; b < 16; ++b){
				for (int c = 0; c < 16; ++c){
					for (int d = 0; d < 16; ++d){
						for (int e = 0; e < 16; ++e){
							for (int f = 0; f < 16; ++f){
								for (int g = 0; g < 16; ++g){
									for (int h = 0; h < 16; ++h){
										String key = Integer.toHexString(a) + Integer.toHexString(b) + Integer.toHexString(c) + Integer.toHexString(d) + Integer.toHexString(e) + Integer.toHexString(f) + Integer.toHexString(g) + Integer.toHexString(h) + keysuff;
										try {
											decodeMsg(key);
										} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
												| InvalidAlgorithmParameterException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println("Thread nr " + id +  " ended");
		
	}
	
}
