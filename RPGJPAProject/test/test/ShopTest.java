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
    	GameCharacter gc = em.find(GameCharacter.class, 5);
    	assertNotNull(gc);
    	assertEquals("Banshee", gc.getName());
    	
    }
    @Test
    public void test_player_gamecharacter_mappings() {
        Player p =em.find(Player.class, 1);
         List<GameCharacter> chs = p.getGameCharacters();
        assertNotNull(p);
        assertEquals("user@user.com", p.getEmail());
        assertEquals(3, chs.size());  //tests list of game characters for player 1
        //assertEquals(p.getPhone(), “3333333”);
    }
	
}
