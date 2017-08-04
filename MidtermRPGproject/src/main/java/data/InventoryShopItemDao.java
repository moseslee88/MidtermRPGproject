package data;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InventoryShopItemDao {
	public List<Item> inventory(GameCharacter gameCharacter);
	public Item weapon(Item weapon);
	public List<Item> armor(Item armor);
	public List<Item> edible(Item edible);

}
