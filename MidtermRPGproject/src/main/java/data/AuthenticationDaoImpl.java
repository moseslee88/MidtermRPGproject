package data;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

	
	@Transactional
	@Repository
	public class AuthenticationDaoImpl implements AuthenticationDao {
		@PersistenceContext
		private EntityManager em;
		
		@Autowired
		private EncryptionDAO encryptor;
		
		public Player register(Player p) {
			try {
				p.setPassword(encryptor.encrypt(p.getPassword()));
			} catch(Exception e) {
				e.printStackTrace();
			}
			em.persist(p);
			em.flush();
			return p;
		}

		@Override
		public Player login(Player p) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isAdmin(Player p) {
			// TODO Auto-generated method stub
			return false;
		}


	}
	
	
	
	