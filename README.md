
##MidtermRPGproject##
---
Our group midterm project - *Connor, Jake, Bryan, and Moses*.

The technologies we incorporated into this project are....

-Spring MVC

-JPA

-MySQL

-Bootstrap/HTML/CSS

The methodologies you are using to build the application (group project, agile etc).

The origin of this game idea came from a lab we did earlier in our second week here at Skill Distillery.  For an interactive fiction "lord_of_the_objects" labs, some of us chose to build out elaborate, illustrious user-interactive text word games.  Also, another one of our sources of inspiration was the old fictional text game [Zork](https://en.wikipedia.org/wiki/Zork "Zork"). First, we built out the structure of the wireframe, and then collaborated on making user stories on Trello. One lesson we discovered is that throughout the duration of our project, we made many changes to our schema in the database side. Then started with login and authentication.

Then, we handled the game logic and the plethora of options a player has. Here is a snippet of sample code.


```
	private int calculateDamage(GameCharacter enemy, Ability attack) {
		RandNumGen rng = new RandNumGen();
		Double modifiedDamage = rng.getRNG(-(this.hp / 10.0), (this.hp / 10.0));
		Double modifier = .8;
		double attackPower = attack.getPower();
		if (attack.getElement().equals(Element.physical)) {
			double percentResisted = this.getPhysicalR() * modifier;
			modifiedDamage += modifyResistedDamage(percentResisted, attackPower, enemy);
		}
		if (attack.getElement().equals(Element.fire)) {
			double percentResisted = this.getFireR() * modifier;
			modifiedDamage += modifyResistedDamage(percentResisted, attackPower, enemy);
		}
		if (attack.getElement().equals(Element.frost)) {
			double percentResisted = this.getFrostR() * modifier;
			modifiedDamage += modifyResistedDamage(percentResisted, attackPower, enemy);
		}
		if (attack.getElement().equals(Element.lightning)) {
			double percentResisted = this.getLightningR() * modifier;
			modifiedDamage += modifyResistedDamage(percentResisted, attackPower, enemy);
		}
		if (attack.getElement().equals(Element.blood)) {
			double percentResisted = this.getBloodR() * modifier;
			modifiedDamage += modifyResistedDamage(percentResisted, attackPower, enemy);
		}
		if (attack.getElement().equals(Element.dark)) {
			double percentResisted = ((this.getFireR() + this.getBloodR()) / 2) * modifier;
			modifiedDamage += modifyResistedDamage(percentResisted, attackPower, enemy);
		}

		double temp = modifiedDamage.doubleValue();
		return (int) temp;
	}
```

We are building a RPG game in our project. We built our schema to include a player(user) table and a groups table, so that it would be easier for us to see the Many-to-One relationship.
