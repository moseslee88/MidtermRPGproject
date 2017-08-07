package data;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class EncryptionDAOImpl implements EncryptionDAO {
	public String encrypt(String plainText) throws NoSuchAlgorithmException {
		MessageDigest digest;
		digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder()
						.encodeToString(hash)
						.replaceAll("[^A-Za-z0-9]", "");
	}
	
	public boolean matches(String plainText, String sha) {
		try {
			return encrypt(plainText).equals(sha);
		} catch (NoSuchAlgorithmException e) {
			return false;
		}
	} 
}
