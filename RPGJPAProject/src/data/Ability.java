package data;

import java.util.List;

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
	
	@ManyToMany(mappedBy = "abilities", fetch=FetchType.EAGER)
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
		return "Ability [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ability other = (Ability) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}	
	
	

}
