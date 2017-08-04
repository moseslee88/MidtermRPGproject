package data;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationDao {
	public Player register(Player p);
	public Player login(Player p);
	public boolean isAdmin(Player p);
	public List<Player> indexPlayers();
	String findUserPasswordByEmail(String email);
}
