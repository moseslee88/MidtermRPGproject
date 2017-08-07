package data;


import java.util.List;

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
		@Autowired
		private AuthenticationDao adao;
		
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
		
		

		//user logs in and we check if password matches SHA
		//Boolean result=encryptor.matches(sha, em.createQuery(q, Player.class).setParameter("password", password).getSingleResult();
		// 1st arg: plain text password, 2nd arg: encrypted password for user from db (em.find(User.class,1).getPassword();)
		@Override
		public Player login(String email, String password) {
			Player p=null;
			//String q = "SELECT p from Player p where p.email = :email and p.password = :password";
			String query = "SELECT p FROM Player p WHERE p.email = :email";
			try {
				p = em.createQuery(query, Player.class)
							.setParameter("email", email)
							.getResultList()
							.get(0);
				Boolean result = encryptor.matches(password, p.getPassword());
			    System.out.println("result: " + result);
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("Please enter correct password.");
				return null;
			}

			em.flush();  //Only need a flush, not persisting any managed entity
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
				System.out.println("No such user exists.");
			}
			return null;
		}

		@Override
		public boolean isAdmin(Player p) {
			String query = "SELECT p from Player p where p.displayName = 'admin'";
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
		
		@Override
		public Boolean validEmail(String email){
			if(email == null || email.length() < 5) 
				return null;
			else for(Player p : indexPlayers()) 
				if (p.getEmail().equals(email))  
				    return true;
			
			return false;
		}
		
		@Override
		public Boolean validPassword(String password){
			if(password == null || password.length() < 5 || password.length() > 30)
				return null;
			else for(Player p: indexPlayers()) 
				if (p.getPassword().equals(password)) 
					return true;
			
			return false;
		}


	}
	
	
	
	