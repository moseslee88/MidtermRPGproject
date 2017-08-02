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
import javax.persistence.Table;

@Entity
@Table(name = "game_character")
public class GameCharacter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@OneToMany(mappedBy = "gameCharacter")
	private List<Inventory> inventory;
	@Column
	private int health;
	@Column
	private int energy;
	@Column
	private int power;
	@Column
	private int critical;
	@Column(name = "physical_r")
	private int physicalR;
	@Column(name = "fire_r")
	private int fireR;
	@Column(name = "frost_r")
	private int frostR;
	@Column(name = "lightning_r")
	private int lightningR;
	@Column(name = "blood_r")
	private int bloodR;
	@Column(name = "experience_given")
	private int experienceGiven;
	@Column(name = "experience_total")
	private int experienceTotal;
	@Column
	private int level;
	@ManyToOne
	@JoinColumn(name = "player_id")
	// private int playerId;
	private Player player;
	
	@ManyToMany
	@JoinTable(name = "character_ability", joinColumns = @JoinColumn(name = "character_id"), inverseJoinColumns = @JoinColumn(name = "ability_id"))
	private List<Ability> abilities;

	@OneToMany(mappedBy="gameCharacter")
	 private List<Stage> stages;
	@Column
	private Boolean active;

	@Column(name = "ability_points")
	private int abilityPoints;

	@Column(name = "stat_points")
	private int statPoints;

	// gets and sets

	public GameCharacter() { // NO args constructor

	}

	public int getId() {
		return id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Inventory> getInventory() {
		return inventory;
	}

	public void setInventory(List<Inventory> inventory) {
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

	public List<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}

	public void setAbilityPoints(int abilityPoints) {
		this.abilityPoints = abilityPoints;
	}

	public int getStatPoints() {
		return statPoints;
	}

	public void setStatPoints(int statPoints) {
		this.statPoints = statPoints;	
	}

	public List<Stage> getStages() {
		return stages;
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
	}

	@Override
	public String toString() {
		return "GameCharacter [id=" + id + ", name=" + name + ", inventorySize=" + this.getInventory().size()
				+ ", health=" + health + ", energy=" + energy + ", power=" + power + ", critical=" + critical
				+ ", physicalR=" + physicalR + ", fireR=" + fireR + ", frostR=" + frostR + ", lightningR=" + lightningR
				+ ", bloodR=" + bloodR + ", experienceGiven=" + experienceGiven + ", experienceTotal=" + experienceTotal
				+ ", level=" + level + ", playerName=" + this.getPlayer().getId() + ", statPoints= " + statPoints + 
				", abilityPoints= " + abilityPoints  + " ]";
	}

}
