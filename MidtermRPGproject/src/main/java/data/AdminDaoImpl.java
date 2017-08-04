package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.LazyInitializationException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class AdminDaoImpl implements AdminDao {
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private EncryptionDAO encryptor;

	public List<GameCharacter> indexGameCharacters() {
		String q = "select g from GameCharacter g";
		try {
			return em.createQuery(q, GameCharacter.class).getResultList();
		} catch (LazyInitializationException e) {
			return new ArrayList<GameCharacter>();
		}
	}

	public GameCharacter showGameCharacter(int id) {
		return em.find(GameCharacter.class, id);
	}

	public GameCharacter createGameCharacter(GameCharacter gameCharacter) {
		// gameCharacter.setActive(true);
		em.persist(gameCharacter);
		em.flush();

		return gameCharacter;
	}

	public GameCharacter updateGameCharacter(int id, GameCharacter gameCharacter) {

		GameCharacter managedChar = em.find(GameCharacter.class, id);
		if (gameCharacter.getName() != null) {
			managedChar.setName(gameCharacter.getName());
		}
		// managedChar.setCritical(gameCharacter.getCritical());
		// managedChar.setEnergy(gameCharacter.getEnergy());
		// managedChar.setExperienceGiven(gameCharacter.getExperienceGiven());
		// managedChar.setExperienceTotal(gameCharacter.getExperienceTotal());
		// managedChar.setBloodR(gameCharacter.getBloodR());
		// managedChar.setFireR(gameCharacter.getFireR());
		// managedChar.setFrostR(gameCharacter.getFrostR());
		// managedChar.setPhysicalR(gameCharacter.getPhysicalR());
		// managedChar.setLightningR(gameCharacter.getLightningR());
		// managedChar.setHealth(gameCharacter.getHealth());
		// managedChar.setInventory(gameCharacter.getInventory());
		// managedChar.setLevel(gameCharacter.getLevel());
		// managedChar.setPower(gameCharacter.getPower());
		// managedChar.setActive(gameCharacter.getActive());
		// managedChar.setAbilities(gameCharacter.getAbilities());
		// managedChar.setInventory(gameCharacter.getInventory());
		// managedChar.setStages(gameCharacter.getStages());
		// managedChar.setAbilityPoints(gameCharacter.getAbilityPoints());
		// managedChar.setStatPoints(gameCharacter.getStatPoints());
		// if (gameCharacter.getActive() != null) {
		// managedChar.setActive(gameCharacter.getActive());
		// }
		return gameCharacter;
	}

	public boolean destroyGameCharacter(int id) {

		GameCharacter gameCharacter = em.find(GameCharacter.class, id);

		if (gameCharacter == null) {
			return false;
		} else {
			em.remove(gameCharacter);

			return true;
		}
	}

	public List<Item> indexItems() {
		String q = "select i from Item i";
		return em.createQuery(q, Item.class).getResultList();
	}

	public Item showItem(int id) {
		return em.find(Item.class, id);
	}

	public Item createItem(Item item) {

		em.persist(item);
		em.flush();

		return item;
	}

	public Item updateItem(int id, Item item) {

		Item managed = em.find(Item.class, id);
		if (item.getName() != null) {
			managed.setName(item.getName());
		}
		if (item.getItemLevel() != null) {
			managed.setItemLevel(item.getItemLevel());
		}
		if (item.getValue() != null) {
			managed.setValue(item.getValue());
		}
		if (item.getElement() != null) {
			managed.setElement(item.getElement());
		}
		// if (item.getInventory() != null) {
		// managed.setInventory(item.getInventory());
		// }
		if (item.getTypeOfItem() != null) {
			managed.setTypeOfItem(item.getTypeOfItem());
		}
		return item;
	}

	public boolean destroyItem(int id) {

		Item item = em.find(Item.class, id);

		if (item == null) {
			return false;
		} else {
			em.remove(item);

			return true;
		}
	}

	public List<Player> indexPlayers() {
		String q = "select i from Player i";
		return em.createQuery(q, Player.class).getResultList();
	}

	public Player showPlayer(int id) {
		return em.find(Player.class, id);
	}

	public Player createPlayer(Player player) {

		em.persist(player);
		em.flush();

		return player;
	}

	public Player updatePlayer(int id, Player player) {

		Player managedPlayer = em.find(Player.class, id);

		if (player.getUserType() <= 2) {
			managedPlayer.setUserType(player.getUserType());
		}
		if (player.getDisplayName() != null) {
				managedPlayer.setDisplayName(player.getDisplayName());
			
		}
		if (player.getEmail() != null) {
			managedPlayer.setEmail(player.getEmail());
		}

		if (player.getPassword() != null)

			try {
				player.setPassword(encryptor.encrypt(player.getPassword()));
				managedPlayer.setPassword(player.getPassword());
			} catch (Exception e) {
				e.printStackTrace();

			}

		//
		// if (player.getPassword() != null) {
		// // managedPlayer.setFriends(player.getFriends());
		// managedPlayer.setPassword(player.getPassword());
		// }
		if (player.getQuests() != null) {
			managedPlayer.setQuests(player.getQuests());
		}
		// managedPlayer.setGameCharacters(player.getGameCharacters());
		return player;
	}

	public boolean destroyPlayer(int id) {

		Player player = em.find(Player.class, id);

		if (player == null) {
			return false;
		} else {
			em.remove(player);

			return true;
		}
	}

	public List<Quest> indexQuests() {
		String q = "select q from Quest q";
		return em.createQuery(q, Quest.class).getResultList();
	}

	public Quest showQuest(int id) {
		return em.find(Quest.class, id);
	}

	public Quest createQuest(Quest quest) {

		em.persist(quest);
		em.flush();

		return quest;
	}

	public Quest updateQuest(int id, Quest quest) {

		Quest managed = em.find(Quest.class, id);
		if (quest.getName() != null) {
			managed.setName(quest.getName());
		}
		if (quest.getConclusion() != null) {
			// managed.setCompleted(quest.getCompleted());
			managed.setConclusion(quest.getConclusion());
		}
		if (quest.getDescription() != null) {
			managed.setDescription(quest.getDescription());
		}
		if (quest.getIntro() != null) {
			managed.setIntro(quest.getIntro());
		}
		// managed.setLevelMax(quest.getLevelMax());
		// managed.setLevelMin(quest.getLevelMin());
		// managed.setStages(quest.getStages());
		// managed.setPlayers(quest.getPlayers());
		return quest;
	}

	public boolean destroyQuest(int id) {

		Quest quest = em.find(Quest.class, id);

		if (quest == null) {
			return false;
		} else {
			em.remove(quest);

			return true;
		}
	}

	public Stage createStage(Stage stage) {

		em.persist(stage);
		em.flush();

		return stage;
	}

	public Stage updateStage(int id, Stage stage) {

		Stage managed = em.find(Stage.class, id);
		if (stage.getName() != null) {
			managed.setName(stage.getName());
		}
		if (stage.getIntro() != null) {
			managed.setIntro(stage.getIntro());
		}
		if (stage.getConclusion() != null) {
			managed.setConclusion(stage.getConclusion());
		}
		if (stage.getGameCharacter() != null) {
			managed.setGameCharacter(stage.getGameCharacter());
		}

		return stage;
	}

	public boolean destroyStage(int id) {

		Stage stage = em.find(Stage.class, id);

		if (stage == null) {
			return false;
		} else {
			em.remove(stage);

			return true;
		}
	}

	public boolean checkEmail(Player player) {
		
		String query = "SELECT p FROM Player p WHERE p.email = :email";
		
		List<Player> emails = em.createQuery(query, Player.class).setParameter("email", player.getEmail()).getResultList();
		
		if(emails.size() > 0) {
			return true;
		} else {
			return false;
		}
	
	}

	public boolean checkDisplayName(Player player) {
		
		String query = "SELECT p FROM Player p WHERE p.displayName = :displayName";
		List<Player> displayNames = em.createQuery(query, Player.class).setParameter("displayName", player.getDisplayName()).getResultList();
		
		if(displayNames.size() > 0) {
			return true;
		} else {
			return false;
		}
		
	}

}
