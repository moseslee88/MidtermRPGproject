package data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import enums.Element;

@Entity
public class Ability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;


	@Enumerated(EnumType.STRING)
	@Column
	private Element element;

	@Column
	private int power;

	@Column(name = "energy_cost")
	private int energyCost;
	
	@ManyToMany(mappedBy = "abilities")
	private List<GameCharacter> gameCharacters;
	
	//sets and gets

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GameCharacter> getGameCharacters() {
		return gameCharacters;
	}

	public void setGameCharacters(List<GameCharacter> gameCharacters) {
		this.gameCharacters = gameCharacters;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(int energyCost) {
		this.energyCost = energyCost;
	}

	public int getId() {
		return id;
	}
	
	

	@Override
	public String toString() {
		return "Ability [id=" + id + ", name=" + name + ", gameCharactersSize=" + this.getGameCharacters().size() + ", element=" + element
				+ ", power=" + power + ", energyCost=" + energyCost + "]";
	}
	
	

}
