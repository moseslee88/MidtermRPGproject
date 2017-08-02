package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class CharacterEditDaoImpl implements CharacterEditDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public GameCharacter create(GameCharacter newChar) {
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
	public boolean killChar(int charId) {
		GameCharacter managedChar = em.find(GameCharacter.class, charId);
		if (managedChar.getId() == charId) {
			Player p = em.find(Player.class, 2);
			managedChar.setPlayer(p);
			return true;
		} else {
			return false;
		}
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
			if (Character.getId()==id) {
				return Character;
			}
		}
		return null;
	}


}
