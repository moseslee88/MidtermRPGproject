package data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Character {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToMany(mappedBy="characters")
	private Inventory inventory;
	@Column
	private String name;
	@Column
	private int health;
	@Column
	private int energy;
	@Column
	private int power;
	@Column
	private int critical;
	@Column(name="physical_r")
	private int physicalR;
	@Column(name="fire_r")
	private int fireR;
	@Column(name="frost_r")
	private int frostR;
	@Column(name="lightning_r")
	private int lightningR;
	@Column(name="blood_r")
	private int bloodR;
	@Column(name="experience_given")
	private int experienceGiven;
	@Column(name="experience_total")
	private int experienceTotal;
	@Column
	private int level;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getCritical() {
		return critical;
	}
	public void setCritical(int critical) {
		this.critical = critical;
	}
	public int getPhysicalR() {
		return physicalR;
	}
	public void setPhysicalR(int physicalR) {
		this.physicalR = physicalR;
	}
	public int getFireR() {
		return fireR;
	}
	public void setFireR(int fireR) {
		this.fireR = fireR;
	}
	public int getFrostR() {
		return frostR;
	}
	public void setFrostR(int frostR) {
		this.frostR = frostR;
	}
	public int getLightningR() {
		return lightningR;
	}
	public void setLightningR(int lightningR) {
		this.lightningR = lightningR;
	}
	public int getBloodR() {
		return bloodR;
	}
	public void setBloodR(int bloodR) {
		this.bloodR = bloodR;
	}
	public int getExperienceGiven() {
		return experienceGiven;
	}
	public void setExperienceGiven(int experienceGiven) {
		this.experienceGiven = experienceGiven;
	}
	public int getExperienceTotal() {
		return experienceTotal;
	}
	public void setExperienceTotal(int experienceTotal) {
		this.experienceTotal = experienceTotal;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", health=" + health + ", power=" + power + ", level=" + level
				+ "]";
	}
}
