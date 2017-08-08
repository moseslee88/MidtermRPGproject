package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import enums.TypeOfItem;

@Transactional
@Repository
public class InventoryShopItemDaoImpl implements InventoryShopItemDao {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> inventory(GameCharacter gameCharacter) {
		//
		// String id = Integer.toString(gameCharacter.getInventory().getId());
		//
		// String query = "SELECT * FROM item i JOIN inventory_item j ON i.id =
		// j.item_id JOIN inventory k ON j.inventory_id = k.id WHERE k.id ="
		// + id;
		//
		// List<Item> items = em.createNativeQuery(query, Item.class).getResultList();
		//
		// System.out.println("items size " + items.size());
		// System.out.println("name " + gameCharacter.getName());
		// System.out.println("id " + gameCharacter.getId());
		//
		// return items;
		return gameCharacter.getInventory().getItems();

	}

	@SuppressWarnings("unchecked")
	public List<Item> weapons(GameCharacter gameCharacter) {
		// String id = Integer.toString(gameCharacter.getInventory().getId());
		//
		// String query = "SELECT * FROM item i JOIN inventory_item j ON i.id =
		// j.item_id JOIN inventory k ON j.inventory_id = k.id WHERE k.id ="
		// + id + " AND i.type = 'weapon'";
		//
		// List<Item> weapons = em.createNativeQuery(query, Item.class).getResultList();
		// System.out.println("weapons: " + weapons.size());
		// return weapons;
		return gameCharacter.getInventory().getItems(TypeOfItem.weapon);
	}

	@SuppressWarnings("unchecked")
	public List<Item> armor(GameCharacter gameCharacter) {
		return gameCharacter.getInventory().getItems(TypeOfItem.armor);
	}

	@SuppressWarnings("unchecked")
	public List<Item> edibles(GameCharacter gameCharacter) {
		return gameCharacter.getInventory().getItems(TypeOfItem.edible);
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
			if (gameCharacter.getInventory().getItems(TypeOfItem.weapon).size() > 0) {
				return true;
			}
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
			if (gameCharacter.getInventory().getItems(TypeOfItem.armor).size() > 0) {
				return true;
			}
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
			if (gameCharacter.getInventory().getItems(TypeOfItem.edible).size() > 0) {
				return true;
			}
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public Boolean checkForInventory(GameCharacter gameCharacter) {
		String id = Integer.toString(gameCharacter.getInventory().getId());

		String query = "SELECT * FROM item i JOIN inventory_item j ON i.id = j.item_id JOIN inventory k ON j.inventory_id = k.id WHERE k.id ="
				+ id;

		List<Item> items = em.createNativeQuery(query, Item.class).getResultList();

		if (items.size() > 0) {
			return true;
		} else {
			if (gameCharacter.getInventory().getItems().size() > 0) {
				return true;
			}
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public Item getItemFromGameCharacter(GameCharacter gameCharacter, Integer itemId) {

		Item itemResult = null;

		List<Item> items = gameCharacter.getInventory().getItems();

		for (Item item : items) {
			if (item.getId() == itemId) {
				itemResult = item;
			}

		}

		return itemResult;
	}

	public Boolean removeItemFromInventory(GameCharacter gameCharacter, Integer itemId) {
		String id = itemId.toString();
		String query = "DELETE * FROM item WHERE item.id = " + id;

		em.createNativeQuery(query);
		return true;
	}

}
