package decrypter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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

	static File secret = new File("/home/marcin/Programming/krypto/Kryptografia/Player/secret.key");
	static File file = new File("/home/marcin/Programming/krypto/Kryptografia/Player/my.mp3");
	static File encryptedFile = new File("/home/marcin/Programming/krypto/Kryptografia/Player/EncFile.encrypted");
	static File decryptedFile = new File("/home/marcin/Programming/krypto/Kryptografia/Player/DecFile.decrypted");
	
	static private IvParameterSpec ivspec;
	
	
	public static void start(){
		genKey();
		crypt(Cipher.ENCRYPT_MODE, file, encryptedFile);
		
		crypt(Cipher.DECRYPT_MODE, encryptedFile, decryptedFile);
	}
	
	private static void genKey(){
		
		try {
			
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			SecureRandom random = new SecureRandom();
			keygen.init(random);
			SecretKey key = keygen.generateKey();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(secret));
			
			byte iv[] = new byte[16];//generate random 16 byte IV AES is always 16bytes
            random.nextBytes(iv);
            ivspec = new IvParameterSpec(iv);
			
			out.writeObject(key);
			out.close();
			
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void crypt(int mode, File input, File output){
		
		try {
			ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(secret));
			
			Key key = (Key) keyIn.readObject();
			
			keyIn.close();
			
			InputStream in = new FileInputStream(input);
			OutputStream out = new FileOutputStream(output);
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(mode, key, ivspec);
			
			execute(in, out, cipher);
			out.close();
			
			
			
		} catch (IOException | ClassNotFoundException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | 
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

 
    
}