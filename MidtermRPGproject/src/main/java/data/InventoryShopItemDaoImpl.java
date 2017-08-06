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

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> inventory(GameCharacter gameCharacter) {

		String id = Integer.toString(gameCharacter.getInventory().getId());

		String query = "SELECT * FROM item i JOIN inventory_item j ON i.id = j.item_id JOIN inventory k ON j.inventory_id = k.id WHERE k.id ="
				+ id;

		List<Item> items = em.createNativeQuery(query, Item.class).getResultList();

		return items;
	}

	@SuppressWarnings("unchecked")
	public List<Item> weapons(GameCharacter gameCharacter) {
		String id = Integer.toString(gameCharacter.getInventory().getId());

		String query = "SELECT * FROM item i JOIN inventory_item j ON i.id = j.item_id JOIN inventory k ON j.inventory_id = k.id WHERE k.id ="
				+ id + " AND i.type = 'weapon'";

		List<Item> weapons = em.createNativeQuery(query, Item.class).getResultList();

		return weapons;
	}

	@SuppressWarnings("unchecked")
	public List<Item> armor(GameCharacter gameCharacter) {
		String id = Integer.toString(gameCharacter.getInventory().getId());

		String query = "SELECT * FROM item i JOIN inventory_item j ON i.id = j.item_id JOIN inventory k ON j.inventory_id = k.id WHERE k.id ="
				+ id + " AND i.type = 'armor'";

		List<Item> armor = em.createNativeQuery(query, Item.class).getResultList();

		return armor;
	}

	@SuppressWarnings("unchecked")
	public List<Item> edibles(GameCharacter gameCharacter) {
		String id = Integer.toString(gameCharacter.getInventory().getId());

		String query = "SELECT * FROM item i JOIN inventory_item j ON i.id = j.item_id JOIN inventory k ON j.inventory_id = k.id WHERE k.id ="
				+ id + " AND i.type = 'edible'";

		List<Item> edibles = em.createNativeQuery(query, Item.class).getResultList();

		return edibles;
	}

	@SuppressWarnings("unchecked")
	public Boolean checkForWeapons(GameCharacter gameCharacter) {
		String id = Integer.toString(gameCharacter.getInventory().getId());

		String query = "SELECT * FROM item i JOIN inventory_item j ON i.id = j.item_id JOIN inventory k ON j.inventory_id = k.id WHERE k.id ="
				+ id + " AND i.type = 'weapon' ";

		List<Item> weapons = em.createNativeQuery(query, Item.class).getResultList();

		if (weapons.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public Boolean checkForArmor(GameCharacter gameCharacter) {
		String id = Integer.toString(gameCharacter.getInventory().getId());

		String query = "SELECT * FROM item i JOIN inventory_item j ON i.id = j.item_id JOIN inventory k ON j.inventory_id = k.id WHERE k.id ="
				+ id + " AND i.type = 'armor'";

		List<Item> armor = em.createNativeQuery(query, Item.class).getResultList();

		if (armor.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public Boolean checkForEdibles(GameCharacter gameCharacter) {
		String id = Integer.toString(gameCharacter.getInventory().getId());

		String query = "SELECT * FROM item i JOIN inventory_item j ON i.id = j.item_id JOIN inventory k ON j.inventory_id = k.id WHERE k.id ="
				+ id + " AND i.type = 'edible'";

		List<Item> edibles = em.createNativeQuery(query, Item.class).getResultList();

		if (edibles.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

}
