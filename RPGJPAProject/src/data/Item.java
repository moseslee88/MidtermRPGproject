package data;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import enums.Element;
import enums.TypeOfItem;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(name="item_level")
	private Integer itemLevel;

	private String value;

	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private TypeOfItem typeOfItem;

	@Enumerated(EnumType.STRING)
	@Column
	private Element element;
	
	 @ManyToMany(mappedBy="items", fetch=FetchType.EAGER)
	 private Set<Inventory> inventory;  
	
	//gets and sets

	public String getName() {
		return name;
	}

	public Item() {
		super();
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(Integer itemLevel) {
		this.itemLevel = itemLevel;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TypeOfItem getTypeOfItem() {
		return typeOfItem;
	}

	public void setTypeOfItem(TypeOfItem typeOfItem) {
		this.typeOfItem = typeOfItem;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Integer getId() {
		return id;
	}
	
	

	public Set<Inventory> getInventory() {
		return inventory;
	}

	public void setInventory(Set<Inventory> inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", itemLevel=" + itemLevel + ", value=" + value + ", typeOfItem="
				+ typeOfItem + ", element=" + element + ", inventorySize=" + this.getInventory().size() + "]";
	}

}
