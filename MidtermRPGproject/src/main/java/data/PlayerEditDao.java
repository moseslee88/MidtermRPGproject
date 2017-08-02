package data;

import java.util.List;

import org.springframework.stereotype.Repository;
import data.*;

@Repository
public interface PlayerEditDao {
	public Player create(Player newPlayer);
	public Player update(Player updatedPlayer, int id);
	public boolean findFriend(Friend friend);

}
