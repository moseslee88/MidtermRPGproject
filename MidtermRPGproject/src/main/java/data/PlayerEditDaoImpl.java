package data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class PlayerEditDaoImpl implements PlayerEditDao {
	
	@Override
	public Player create(Player newPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

}
