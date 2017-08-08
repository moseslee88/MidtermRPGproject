package test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Inventory;
import data.Player;


public class ShopTest {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;

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
	
	/** @Test
	public void test_shop_for_inventory_id() {
		Shop shop = em.find(Shop.class, 1);
		Inventory inventory = shop.getInventory();
		assertEquals("dog", inventory.getShops());
	}
	
    @Test
    public void test() {
        boolean pass = true;
        assertEquals(pass, true);
    }
    @Test
    public void test_player_character_mappings() {
        Player p =em.find(Player.class, 1);
        assertNotNull(p);
        assertEquals(p.getEmail(),"user@user.com");
        //assertEquals(p.getPhone(), “3333333”);   **/
    }
	