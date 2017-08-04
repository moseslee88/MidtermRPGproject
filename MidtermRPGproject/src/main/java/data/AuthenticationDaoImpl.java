package data;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

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
			// TODO user logs in and we check if password matches SHA
			String q = "SELECT p from Player p where p.email = :p.email and p.password = :p.password";
			String sha =  null;
			try {
				sha = encryptor.encrypt(p.getPassword());
				Boolean result=encryptor.matches(p.getPassword(), em.createQuery(q, Player.class).getSingleResult().getPassword());
			    System.out.println(result);
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("Please enter correct password.");
			}
			em.persist(p);
			em.flush();
			return p;
		}
		
		@Override
		public String findUserPasswordByEmail(String email)  {
			String query = "Select p FROM Player p where p.email = :email";
			Player p = null;
			try {
				p = em.createQuery(query, Player.class).setParameter("email", email).getSingleResult();
				String pw = p.getPassword();
				return pw;
			} catch (Exception e)  {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public boolean isAdmin(Player p) {
			// TODO Auto-generated method stub
			String query = "SELECT p from Player p where p.display_name = 'admin'";
			try {
				Player playerfound = em.createQuery(query, Player.class).getSingleResult();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		
		public List<Player> indexPlayers() {
			String q = "select p from Player p";
			return em.createQuery(q, Player.class).getResultList();
		}


	}
	
	
	
	