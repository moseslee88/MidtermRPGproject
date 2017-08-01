package data;
import org.springframework.stereotype.Repository;
import data.*;

@Repository
public interface PlayerEditDao {
	public Player create(Player newPlayer);
}
