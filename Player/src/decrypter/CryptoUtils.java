package decrypter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.audio.AudioData;


public class CryptoUtils {


	private static File inputFile;
	private static File outputFile;
	private static String scheme;
	private static String type;
	private static String keyStorePath;
	private static String alias;
	private static int mode;
	private static String iv = "15101ccc5df148eea4bdd5b4cb3f6b68";
	private static char[] ksPassword;
	private static char[] keyPassword;
	private static IvParameterSpec ivspec;
	private static Key key = null;
	private static SecretKeySpec keySpec = null;
	
	public static void setKSPassword(char[] ksp){
		ksPassword = ksp;
	}
	
	public static void setKeyPassword(char[] kp){
		keyPassword = kp;
	}
	
	public static void setKey(Key k){
		keySpec = (SecretKeySpec) k;
	}
	
	public static void start(String[] params){
		scheme = params[0];
		type = params[1];
		keyStorePath = params[2];
		alias = params[3];
		inputFile = new File(params[5]);
		outputFile = new File(params[6]);
		ivspec = new IvParameterSpec(IV(iv));
		if(params[4].equals("E"))
			mode = Cipher.ENCRYPT_MODE;
		else if(params[4].equals("D"))
			mode = Cipher.DECRYPT_MODE;
		else{
			System.out.println("Wrong mode");
			return;
		}
		
		
		if(keySpec == null){
			System.out.println("enter");
			try {
				key = getKey();
			} catch (KeyStoreException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			crypt(mode, inputFile, outputFile, key);
		}
		else{
			crypt(mode, inputFile, outputFile, keySpec);
			keySpec = null;
		}


	}
	
	private static Key getKey() throws KeyStoreException, FileNotFoundException {

		KeyStore ks = KeyStore.getInstance("JCEKS");
		InputStream is = new FileInputStream(keyStorePath);
		
		try {
			ks.load(is, ksPassword);
		} catch (NoSuchAlgorithmException | CertificateException | IOException e) {
			System.out.println("Probably wrong ks password");
			e.printStackTrace();
		}

		try {
			key = ks.getKey(alias, keyPassword);
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			System.out.println("Probably wrong key password");
			e.printStackTrace();
		}
		
		return key;
		
	}
	
	private static void crypt(int mode, File input, File output, Key key){
		
		try {		
			InputStream in = new FileInputStream(input);
			OutputStream out = new FileOutputStream(output);
			
			Cipher cipher = Cipher.getInstance(scheme + "/" + type +"/PKCS5Padding");
			cipher.init(mode, key, ivspec);
			
			execute(in, out, cipher);
			out.close();
			
			
			
		} catch (IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | 
				ShortBufferException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void execute(InputStream in, OutputStream out, Cipher cipher) throws IOException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
		int blockSize = cipher.getBlockSize();
		int outputSize = cipher.getOutputSize(blockSize);
		
		byte[] inBytes = new byte[blockSize];
		byte[] outBytes = new byte[outputSize];
		
		int inLength = 0;
		boolean more = true;
		
		while(more){
			inLength = in.read(inBytes);
			if(inLength == blockSize){
				int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
				out.write(outBytes, 0, outLength);
			}
			else
				more = false;
		}
		
		if(inLength > 0)
			outBytes = cipher.doFinal(inBytes, 0, inLength);
		else
			outBytes = cipher.doFinal();
		out.write(outBytes);
	}
	
	private static byte[] IV(String iv){
		
		byte[] result = new byte[iv.length() / 2];
		
		for(int i = 0; i < result.length; ++i){
			result[i] = (byte) Integer.parseInt(iv.substring(2*i, 2*i + 2) , 16);
		}
		
		return result;
	}
 
    
}