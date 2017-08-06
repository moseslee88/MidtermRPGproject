package data;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InventoryShopItemDao {
	public List<Item> inventory(GameCharacter gameCharacter);
	public List<Item> weapons(GameCharacter gameCharacter);
	public List<Item> armor(GameCharacter gameCharacter);
	public List<Item> edibles(GameCharacter gameCharacter);
	
	public Boolean checkForWeapons(GameCharacter gameCharacter);
	public Boolean checkForArmor(GameCharacter gameCharacter);
	public Boolean checkForEdibles(GameCharacter gameCharacter);

}
