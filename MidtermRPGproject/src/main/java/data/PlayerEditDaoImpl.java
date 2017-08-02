package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PlayerEditDaoImpl implements PlayerEditDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Player create(Player newPlayer) {
		em.persist(newPlayer);
		em.flush();
		return newPlayer;
	}

	@Override
	public Player update(Player updatedPlayer, int id) {
		Player managedPlayer = em.find(Player.class, id);
		managedPlayer.setDisplayName(updatedPlayer.getDisplayName());
		managedPlayer.setEmail(updatedPlayer.getEmail());
		managedPlayer.setFriends(updatedPlayer.getFriends());
		managedPlayer.setPassword(updatedPlayer.getPassword());
		managedPlayer.setQuests(updatedPlayer.getQuests());
		return managedPlayer;
	}

	public boolean findFriend(Friend friend) {
		if(friend.getClass() != null) {
			return true;
		} else {
			return false;
		}
		
	}
}
