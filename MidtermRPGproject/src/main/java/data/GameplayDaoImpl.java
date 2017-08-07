package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GameplayDaoImpl implements GameplayDao {
	@PersistenceContext
	private EntityManager em;

	public Ability useAbilityByName(String abilityName) {
		String qString = "select a from Ability a";
			List<Ability> abilities = em.createQuery(qString, Ability.class).getResultList();

			for (Ability ability : abilities) {
				if (ability.getName().equals(abilityName)) {
					return ability;
				}
			}
			return null;
	}

	public Ability useDefaultAbility() {
		return em.find(Ability.class, 11);
	}

	public Stage getDefaultStage() {
		return em.find(Stage.class, 1);
	}

	public GameCharacter getDefaultGameCharacter() {
		return em.find(GameCharacter.class, 14);
	}

	@Override
	public GameCharacter indexGameCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameCharacter showGameCharacter(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameCharacter createGameCharacter(GameCharacter GameCharacter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameCharacter updateGameCharacter(int id, GameCharacter GameCharacter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroyGameCharacter(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
