package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import controllers.Ability;

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

		managedChar.setName(updatedChar.getName());
		managedChar.setCritical(updatedChar.getCritical());
		managedChar.setEnergy(updatedChar.getEnergy());
		managedChar.setExperienceGiven(updatedChar.getExperienceGiven());
		managedChar.setExperienceTotal(updatedChar.getExperienceTotal());
		managedChar.setBloodR(updatedChar.getBloodR());
		managedChar.setFireR(updatedChar.getFireR());
		managedChar.setFrostR(updatedChar.getFrostR());
		managedChar.setPhysicalR(updatedChar.getPhysicalR());
		managedChar.setLightningR(updatedChar.getLightningR());
		managedChar.setHealth(updatedChar.getHealth());
		managedChar.setInventory(updatedChar.getInventory());
		managedChar.setLevel(updatedChar.getLevel());
		managedChar.setPower(updatedChar.getPower());

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
		return em.createQuery("SELECT g from game_character").getResultList();
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
		for (GameCharacter gameChar : getAllGameCharacters()) {
			if (gameChar.getPlayer().equals(player)) {
				playersCharacters.add(gameChar);
			}
		}
		return playersCharacters;
	}

	@Override
	public int getCharIdByName(String charName) {
		return getCharByName(charName).getId();
	}

}
