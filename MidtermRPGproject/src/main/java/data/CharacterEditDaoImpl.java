package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import data.*;

@Transactional
@Repository
public class CharacterEditDaoImpl implements CharacterEditDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public GameCharacter create(GameCharacter newChar) {
		
		// this is broken so that all users can get all abilities
		List<Ability> abilityList = new ArrayList<>();
		for (int i = 1; i < 31; i++) {
			abilityList.add(em.find(Ability.class, i));			
		}
		newChar.setAbilities(abilityList);
		
		em.persist(newChar);
		em.flush();
		return newChar;
	}

	@Override
	public GameCharacter update(GameCharacter updatedChar, int id) {

		GameCharacter managedChar = em.find(GameCharacter.class, id);

		
		if (updatedChar.getName() != null) {
			managedChar.setName(updatedChar.getName());
		}
		if (updatedChar.getCritical() != 0) {
			managedChar.setCritical(updatedChar.getCritical());
		}
		if (updatedChar.getEnergy()!=0) {
			managedChar.setEnergy(updatedChar.getEnergy());
		}
		if (updatedChar.getBloodR()!=0) {
			managedChar.setBloodR(updatedChar.getBloodR());
		}
		if (updatedChar.getFireR()!=0) {
			managedChar.setFireR(updatedChar.getFireR());
		}
		if (updatedChar.getFrostR()!=0) {
			managedChar.setFrostR(updatedChar.getFrostR());
		}
		if (updatedChar.getPhysicalR()!=0) {
			managedChar.setPhysicalR(updatedChar.getPhysicalR());
		}
		if (updatedChar.getLightningR()!=0) {
			managedChar.setLightningR(updatedChar.getLightningR());
		}
		if (updatedChar.getHealth()!=0) {
			managedChar.setHealth(updatedChar.getHealth());
		}
		if (updatedChar.getInventory()!=null) {
			managedChar.setInventory(updatedChar.getInventory());
		}
		if (updatedChar.getImage()!=null) {
			managedChar.setImage(updatedChar.getImage());
		}
		if (updatedChar.getLevel()!=0) {
			managedChar.setLevel(updatedChar.getLevel());
		}
		if (updatedChar.getPower()!=0) {
			managedChar.setPower(updatedChar.getPower());
		}
		return managedChar;
	}

	@Override
	public boolean killChar(int charId, Player p) {
		for (GameCharacter managedChar : getPlayersGameCharacters(p)) {
			if (managedChar.getId() == charId) {
				Player p1 = em.find(Player.class, charId);
				managedChar.setPlayer(p1);
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GameCharacter> getAllGameCharacters() {
		List<GameCharacter> charList = em.createQuery("SELECT g from GameCharacter g").getResultList();
		return charList;
	}

	@Override
	public GameCharacter getCharByName(String name) {
		for (GameCharacter Character : getAllGameCharacters()) {
			if (Character.getName().equalsIgnoreCase(name)) {
				return Character;
			}
		}
		return null;
	}

	@Override
	public GameCharacter getCharById(int id) {
		for (GameCharacter Character : getAllGameCharacters()) {
			if (Character.getId() == id) {
				return Character;
			}
		}
		return null;
	}

	@Override
	public List<GameCharacter> getPlayersGameCharacters(Player player) {
		System.out.println(player);
		List<GameCharacter> playersCharacters = new ArrayList<>();
		for (GameCharacter gameChar : getAllGameCharacters()) {
			if (gameChar.getPlayer().getId()==player.getId()) {
				playersCharacters.add(gameChar);
				System.out.println(gameChar);
			}
		}
		return playersCharacters;
	}

	@Override
	public int getCharIdByName(String charName) {
		return getCharByName(charName).getId();
	}

}
