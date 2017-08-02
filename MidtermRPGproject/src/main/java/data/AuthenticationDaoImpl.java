package data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class AuthenticationDaoImpl implements QuestStageEditDao {
	@PersistenceContext
	private EntityManager em;
	
	
	import java.nio.charset.StandardCharsets;
	import java.security.MessageDigest;
	import java.security.NoSuchAlgorithmException;
	import java.util.Base64;

	public class CryptoTest {
	    public static void main(String[] args) {
	        String plainTextPW = "banana123";
	        String encryptedPW = null;
	        try {
	            /*
	             * On user creation (registration), hash the user's plain text password
	             * and persist it into the database as an encrypted sha
	             */
	            encryptedPW = encrypt(plainTextPW);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        // User successfully enters plain text password
	        System.out.println(
	                    matches(plainTextPW, encryptedPW)
	                );
	        
	        // User unsuccessfully enters plain text password
	        System.out.println(
	                    matches("notgonnahappen", encryptedPW)
	                );
	        
	    }

	    /*
	     * This method creates a SHA-256 of a plain text password and returns a 
	     * human readable representation of the hashed value (for persistence in db)
	     */
	    private static String encrypt(String plainText) throws NoSuchAlgorithmException {
	        MessageDigest digest;
	        digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));
	        return Base64.getEncoder().encodeToString(hash);
	    }

	    /*
	     * This method compares the persisted password (encrypted) with a plain text value
	     * by encrypting the plain text value the same way and comparing the result.
	     */
	    public static boolean matches(String plainText, String sha) {
	        try {
	            return encrypt(plainText).equals(sha);
	        } catch (NoSuchAlgorithmException e) {
	            return false;
	        }
	    }
	}
}
