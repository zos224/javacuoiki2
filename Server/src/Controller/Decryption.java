package Controller;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class Decryption {
	public static String decryption(String str)
	{
		try {
			FileInputStream fis = new FileInputStream("privateKey.rsa");
			byte[] b = new byte[fis.available()];
			fis.read(b);
			fis.close();
			
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = factory.generatePrivate(spec);
			
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte decryptout[] = cipher.doFinal(Base64.getDecoder().decode(str));
			str = new String(decryptout);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Khong the giai ma";
	}
}
