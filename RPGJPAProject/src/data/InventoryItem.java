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
	private Inventory inventory;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	public InventoryItem() {
		super();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Item getItems() {
		return item;
	}

	public void setItems(Item items) {
		this.item = items;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "InventoryItem [id=" + id;
	}

}
