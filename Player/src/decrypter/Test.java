package decrypter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Test {


	public static void main(String[] args) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException{	

		Password.start("Enter KeyStore password");
		
		/*
		
		String ksp = ".keystore";
		String pass = "123";
		
		KeyStore ks = KeyStore.getInstance("JCEKS");
		
		InputStream is = new FileInputStream(ksp);
		OutputStream os = new FileOutputStream(ksp);
		ks.load(null, null);
		
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		SecureRandom random = new SecureRandom();
		keygen.init(random);
		SecretKey key = keygen.generateKey();  
		
	    char[] password = "456".toCharArray();
		
	    KeyStore.ProtectionParameter protectParam = new KeyStore.PasswordProtection(password);
	    
	    KeyStore.SecretKeyEntry ske = new KeyStore.SecretKeyEntry(key);
	    ks.setEntry("mykey", ske, protectParam);
	    
		
		ks.store(os, pass.toCharArray());
		//System.out.println(ks.aliases().nextElement());
		
		testStore();
*/

	}

	private static void testStore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
		String pass = "123";
		
		String ksp = ".keystore";
		
		KeyStore ks = KeyStore.getInstance("JCEKS");
		InputStream is = new FileInputStream(ksp);
		
		ks.load(is, pass.toCharArray());
		
				
		System.out.println(ks.getKey("mykey", "456".toCharArray()));
	}
	
	

}
