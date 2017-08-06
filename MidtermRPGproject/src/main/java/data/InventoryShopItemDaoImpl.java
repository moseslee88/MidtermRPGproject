package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class InventoryShopItemDaoImpl implements InventoryShopItemDao {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked") // a conversion warning is made from the native query on line 30;
	@Override
	public List<Item> inventory(GameCharacter gameCharacter) {

		String id = Integer.toString(gameCharacter.getInventory().getId());

		String query = "SELECT * FROM item i JOIN inventory_item j ON i.id = j.item_id JOIN inventory k ON j.inventory_id = k.id WHERE k.id ="
				+ id;

		List<Item> items = em.createNativeQuery(query, Item.class).getResultList();

		return items;
	}

	public Item weapon(Item weapon) {
		Item weapon2 = weapon;
		return weapon2;
	}

	public List<Item> armor(Item armor) {
		List<Item> armor2 = new ArrayList<>();
		return armor2;
	}

	public List<Item> edible(Item edible) {
		List<Item> edible2 = new ArrayList<>();
		return edible2;
	}

}
