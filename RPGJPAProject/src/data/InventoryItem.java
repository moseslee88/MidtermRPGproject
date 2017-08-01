package data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_item")
public class InventoryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private List<Inventory> inventory;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private List<Item> items;

	public InventoryItem() {
		super();
	}

	public List<Inventory> getInventory() {
		return inventory;
	}

	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "InventoryItem [id=" + id + ", inventory=" + inventory + ", items=" + items + "]";
	}

}
