package Controller;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class Encryption {
	public static String encrytp(String str)
	{
		try {
			FileInputStream fis = new FileInputStream("publicKey.rsa");
			byte[] b = new byte[fis.available()];
			fis.read(b);
			fis.close();
			
			X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = factory.generatePublic(spec);
			
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte enCryptOut[] = cipher.doFinal(str.getBytes());
			String out = Base64.getEncoder().encodeToString(enCryptOut);
			return out;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Khong ma hoa duoc";
	}
}
