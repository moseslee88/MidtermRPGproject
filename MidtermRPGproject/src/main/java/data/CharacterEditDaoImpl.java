package data;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
}
