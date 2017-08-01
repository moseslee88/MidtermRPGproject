package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Inventory;
import data.Shop;

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
	
	@Test
	public void test_shop_for_inventory_id() {
		Shop shop = em.find(Shop.class, 1);
		Inventory inventory = shop.getInventory();
		assertEquals("dog", inventory.getShops());
	}
	
}
