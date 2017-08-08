package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
		
		List<Ability> abilityList1 = new ArrayList<>();
		for (int i = 1; i < 31; i++) {
			abilityList1.add(em.find(Ability.class, i));			
		}
		
		List<Ability> abilityList = new ArrayList<>();
		// this is broken so that all users can get all abilities
		Collections.shuffle(abilityList1);
		for (int i = 0; i < 4; i++) {
			newChar.setAbilities(abilityList);			
		}
		
		em.flush();
		em.persist(newChar);
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
	public boolean killChar(GameCharacter gameCharacter) {
		try {
			gameCharacter.setPlayer(em.find(Player.class, 1));
			return true;
		} catch (Exception e) {
			return false;
		}
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
		List<GameCharacter> playersCharacters = new ArrayList<>();
		
		for (GameCharacter gameCharacter : getAllGameCharacters()) {
			if (gameCharacter.getPlayer().getId() == player.getId()) {
				playersCharacters.add(gameCharacter);
			}
		}

		return playersCharacters;
	}

	@Override
	public int getCharIdByName(String charName) {
		return getCharByName(charName).getId();
	}

}
