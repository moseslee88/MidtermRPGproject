package data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import enums.Element;
import enums.TypeOfItem;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@OneToMany(mappedBy = "items")
	private List<Integer> itemLevel;

	private String value;

	private TypeOfItem typeOfItem;

	private Element element;

	public String getName() {
		return name;
	}

	public Item() {
		super();
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(List<Integer> itemLevel) {
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

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", itemLevel=" + itemLevel + ", value=" + value + ", typeOfItem="
				+ typeOfItem + ", element=" + element + "]";
	}

}
