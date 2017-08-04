package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class InventoryShopItemDaoImpl implements InventoryShopItemDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Item> inventory(GameCharacter gameCharacter) {
		GameCharacter managedGameCharacter = em.find(GameCharacter.class, gameCharacter.getId());
		
		List<Item> items = managedGameCharacter.getInventory().getItems();
		
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
