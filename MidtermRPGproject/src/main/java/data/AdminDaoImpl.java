package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class AdminDaoImpl implements AdminDao {
	@PersistenceContext
	private EntityManager em;

	public List<GameCharacter> indexGameCharacters() {
		String q = "select g from GameCharacter g";
		return em.createQuery(q, GameCharacter.class).getResultList();
	}

	public GameCharacter showGameCharacter(int id) {
		return em.find(GameCharacter.class, id);
	}

	public GameCharacter createGameCharacter(GameCharacter gameCharacter) {

		em.persist(gameCharacter);
		em.flush();

		return gameCharacter;
	}

	public GameCharacter updateGameCharacter(int id, GameCharacter gameCharacter) {

		GameCharacter managedChar = em.find(GameCharacter.class, id);
		managedChar.setName(gameCharacter.getName());
        managedChar.setCritical(gameCharacter.getCritical());
        managedChar.setEnergy(gameCharacter.getEnergy());
        managedChar.setExperienceGiven(gameCharacter.getExperienceGiven());
        managedChar.setExperienceTotal(gameCharacter.getExperienceTotal());
        managedChar.setBloodR(gameCharacter.getBloodR());
        managedChar.setFireR(gameCharacter.getFireR());
        managedChar.setFrostR(gameCharacter.getFrostR());
        managedChar.setPhysicalR(gameCharacter.getPhysicalR());
        managedChar.setLightningR(gameCharacter.getLightningR());
        managedChar.setHealth(gameCharacter.getHealth());
        managedChar.setInventory(gameCharacter.getInventory());
        managedChar.setLevel(gameCharacter.getLevel());
        managedChar.setPower(gameCharacter.getPower());
        managedChar.setActive(gameCharacter.getActive());
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
		managed.setName(item.getName());
		managed.setItemLevel(item.getItemLevel());
		managed.setValue(item.getValue());
		managed.setElement(item.getElement());
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
		managedPlayer.setDisplayName(player.getDisplayName());
        managedPlayer.setEmail(player.getEmail());
        managedPlayer.setFriends(player.getFriends());
        managedPlayer.setPassword(player.getPassword());
        managedPlayer.setQuests(player.getQuests());
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
		managed.setName(quest.getName());
		managed.setCompleted(quest.getCompleted());
		managed.setConclusion(quest.getConclusion());
		managed.setDescription(quest.getDescription());
		managed.setIntro(quest.getIntro());
		managed.setLevelMax(quest.getLevelMax());
		managed.setLevelMin(quest.getLevelMin());
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


}
