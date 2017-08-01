package data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "////")
	private Character character;

	@OneToMany(mappedBy = "inventory")
	private List<InventoryItem> inventory;
	
	@ManyToOne
	@JoinColumn(name="shop_id")
	private Shop shop;

	public Inventory() {
		super();
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public List<InventoryItem> getInventory() {
		return inventory;
	}

	public void setInventory(List<InventoryItem> inventory) {
		this.inventory = inventory;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", character=" + character + ", inventory=" + inventory + "]";
	}

}
