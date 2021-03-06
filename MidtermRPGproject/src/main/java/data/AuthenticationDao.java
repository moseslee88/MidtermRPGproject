package data;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationDao {
	public Player register(Player p);
	public Player login(String email, String password);
	public boolean isAdmin(Player p);
	public List<Player> indexPlayers();
	String findUserPasswordByEmail(String email);
	public Boolean validEmail(String email);
	public Boolean validPassword(String password) throws NoSuchAlgorithmException;
}
