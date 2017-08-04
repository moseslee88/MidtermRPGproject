package data;

import java.util.List;
import java.util.Random;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import enums.Element;

@Entity
@Table(name = "game_character")
public class GameCharacter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@OneToOne
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;
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
	@Column
	private int level;
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	@Column(name="image")
	private String image;

	@Transient
	private int hp;
	@Transient
	private int stamina;
	@ManyToMany
	@JoinTable(name = "character_ability", joinColumns = @JoinColumn(name = "character_id"), inverseJoinColumns = @JoinColumn(name = "ability_id"))
	private List<Ability> abilities;
	@OneToMany(mappedBy = "gameCharacter")
	private List<Stage> stages;


	
	
	private Double critDamage(double attackPower, GameCharacter enemy) {
		Random rand = new Random();
		double modifier = .2;
		double critModifier = 1.6;
		double damage = attackPower + enemy.getPower();
		int critChance = (int) (enemy.getCritical() * modifier);
		// 100 is the maximum and the 1 is our minimum
		int n = rand.nextInt(100) + 1;

		if (critChance > n) {
			return damage * critModifier;
		}

		if (critChance == n) {
			return damage * 2;
		}
		if (critChance < n) {
			return damage;
		}
		if (critChance == 1) {
			return 0.0;
		}
		return damage;
	}
	private Double modifyResisitedDamage(double resist, double attackPower, GameCharacter enemy) {
		if (resist > 100.0) {
			resist = 100.0;
		}
		Double damageDone = (critDamage(attackPower, enemy)) * ((100 - resist) * .01);
		return damageDone;
	}
	private int calculateDamage(GameCharacter enemy, Ability attack) {
		Double modifyedDamage = 0.0;
		Double modifyer = .80;
		double attackPower = attack.getPower();
		if (attack.getElement().equals(Element.physical)) {
			double percentResisted = this.getPhysicalR() * modifyer;
			modifyedDamage += modifyResisitedDamage(percentResisted, attackPower, enemy);
		}
		if (attack.getElement().equals(Element.fire)) {
			double percentResisted = this.getFireR() * modifyer;
			modifyedDamage += modifyResisitedDamage(percentResisted, attackPower, enemy);
		}
		if (attack.getElement().equals(Element.frost)) {
			double percentResisted = this.getFrostR() * modifyer;
			modifyedDamage += modifyResisitedDamage(percentResisted, attackPower, enemy);
		}
		if (attack.getElement().equals(Element.lightning)) {
			double percentResisted = this.getLightningR() * modifyer;
			modifyedDamage += modifyResisitedDamage(percentResisted, attackPower, enemy);
		}
		if (attack.getElement().equals(Element.blood)) {
			double percentResisted = this.getBloodR() * modifyer;
			modifyedDamage += modifyResisitedDamage(percentResisted, attackPower, enemy);
		}
		if (attack.getElement().equals(Element.dark)) {
			double percentResisted = ((this.getFireR() + this.getBloodR()) / 2) * modifyer;
			modifyedDamage += modifyResisitedDamage(percentResisted, attackPower, enemy);
		}

		double temp = modifyedDamage.doubleValue();
		return (int) temp;
	}
	private void setInitialstats() {
		this.power = 5;
		this.energy = 5;
		this.health = 100;
		this.critical = 4;

		this.physicalR = 10;
		this.bloodR = 10;
		this.fireR = 10;
		this.frostR = 10;
		this.lightningR = 10;

	}
	private void resetHP() {
		this.hp = this.health;
	}
	private void resetStamina() {
		this.stamina = this.energy;
	}
	
	
	// Starts a fight with full energy and health
	public void startFight() {
		resetHP();
		resetStamina();
	}
	public void takeDamage(GameCharacter enemy, Ability attack) {
		this.hp = this.hp - calculateDamage(enemy, attack);
	}
	public void addStamina(int staminaAmount) {
		this.stamina = stamina + staminaAmount;
		if (this.stamina > this.energy) {
			this.stamina = energy;
		}
	}
	public void addHp(int healedAmount) {
		this.hp = hp + healedAmount;
		if (this.hp > this.health) {
			this.hp = health;
		}
	}
	public boolean checkIfDead() {
		if (this.hp <= 0) {
			return true;
		}
		if (this.hp > 0) {
			return false;
		}
		return true;

	}
	public void lvlUp() {
		int lvl = this.level + 1;
		setCharacterLvl(lvl);
	}
	public void setCharacterLvl(int lvl) {
		if (this.level == 0) {
			setLevel(1);
			setInitialstats();
			return;
		}
		int lvlDiff = lvl - this.level;
		double lvlModifier = lvlDiff * 2;

		this.power = (int) (power + lvlModifier);
		this.energy = (int) (energy + lvlModifier);
		this.health = (int) (health + lvlModifier);
		this.critical = (int) (critical + lvlModifier);

		this.physicalR = (int) (physicalR + lvlModifier);
		this.bloodR = (int) (bloodR + lvlModifier);
		this.fireR = (int) (fireR + lvlModifier);
		this.frostR = (int) (frostR + lvlModifier);
		this.lightningR = (int) (lightningR + lvlModifier);

		setLevel(lvl);

	}
	public void endFight() {
		resetHP();
		resetStamina();
	}
	
	
	
	
	// gets and sets
	public GameCharacter() { // NO args constructor

	}
	
	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
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

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
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
	
	public List<Stage> getStages() {
		return stages;
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
	}

	@Override
	public String toString() {

		return "GameCharacter [id=" + id + ", name=" + name + "]";
	}

}
