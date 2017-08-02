package data;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationDao {
	public Player register(Player p);
	public Player login(Player p);
	public boolean isAdmin(Player p);
}
