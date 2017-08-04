package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class GameplayDaoImpl implements GameplayDao {
	@PersistenceContext
	private EntityManager em;
	
	public Ability useDefaultAbility() {
		return em.find(Ability.class, 11);
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
