package data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(mappedBy="inventory")
	private GameCharacter gameCharacter;

	@OneToMany(mappedBy = "inventory")
	private List<InventoryItem> inventoryItems;
	
	@OneToOne(mappedBy="inventory")
	private Shop shop;
	
	
	@ManyToMany
	@JoinTable(name = "inventory_item", joinColumns = @JoinColumn(name = "inventory_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<Item> items;
	
	//gets and sets  

	public void setId(Integer id) {
		this.id = id;
	}

	public GameCharacter getGameCharacter() {
		return gameCharacter;
	}

	public void setGameCharacter(GameCharacter gameCharacter) {
		this.gameCharacter = gameCharacter;
	}

	public List<InventoryItem> getInventory() {
		return inventoryItems;
	}

	public void setInventory(List<InventoryItem> inventory) {
		this.inventoryItems = inventory;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	
	

	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Inventory id"  +  id;
	}
}
