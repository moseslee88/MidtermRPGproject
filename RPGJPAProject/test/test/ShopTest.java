package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.GameCharacter;
import data.Player;

public class ShopTest {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	GameCharacter ch = null;

	@Before
	public void setup() throws Exception {
		emf = Persistence.createEntityManagerFactory("Midterm");
		em = emf.createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		em.close();
		emf.close();

	}
	
	
    @Test
    public void test() {
        boolean pass = true;
        assertEquals(pass, true);
    }
    
    @Test
    public void test_game_character_association (){
    	GameCharacter gc = em.find(GameCharacter.class, 5);  //test for id#5 in GameCharacter class
    	assertNotNull(gc);
    	assertEquals("Bogle", gc.getName());   //the fifth name in the database is Bogle, not Banshee
    	assertEquals(36, gc.getExperienceGiven());     //should be 36
    	
    }
    @Test
    public void test_player_gamecharacter_mappings() {
        Player p =em.find(Player.class, 1);   //'admin' in ENUM TypeOfUser
         List<GameCharacter> chs = p.getGameCharacters();
        assertNotNull(p);
        assertEquals("admin@admin.com", p.getEmail());
        assertEquals(55, chs.size());  //tests list of game characters for player 1
        //assertEquals(p.getPhone(), “3333333”);
    }
    
    @Test
    public void test_player_friend_association (){
    	Player p =em.find(Player.class, 2);   //'player' in ENUM TypeOfUser
    	
    }
    
    @Test
    public void test_player_quest_association (){
    	GameCharacter g
    }
    
    @Test
    public void test_player_usertype_association (){
    	GameCharacter g
    } 
	
}
