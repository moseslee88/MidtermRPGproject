
## MidtermRPGproject
---
by *[Connor](https://github.com/csgibson4/), [Jake](https://github.com/JakeDaTank), [Bryan](https://github.com/broberson7801), and [Moses](https://github.com/moseslee88)*<br>
Our group midterm project was created by four aspiring software devs with a vision of building a game that was both fun and educational. Play today at [MidtermRPG](http://13.56.109.233:8080/MidtermRPGproject/)

## Tech Stack
---
The technologies we incorporated into this project are....

  *Spring MVC<br>
  *JPA<br>
  *MySQL<br>
  *Bootstrap/HTML/CSS<br>
  *Build with Gradle


The origin of this game idea came from a lab we did earlier in our second week here at Skill Distillery.  For our interactive fiction "lord_of_the_objects" labs, some of us chose to build out elaborate, illustrious user-interactive text word games, using text-parsers.  Also, another one of our sources of inspiration was the old fictional text game [Zork](https://en.wikipedia.org/wiki/Zork "Zork"). First, we built out the structure of the wireframe, and then collaborated on making user stories on Trello. One lesson we discovered is that throughout the duration of our project, we made many changes to our schema in the database side as soon as we encountered issues and bugs. For instance, as we started to implement our methods for creating characters, quests, and accounts, we ran into some issues with mapping relationships. We were not able to load a Shop with an Inventory of Items, so our solution was to load an inventory, every time a character was created. Then, we deleted the table 'Shop' in the MySQL database. In terms of Agile development, we created many user stories by sprints. Then, during our daily Scrum 'standups,' we prioritized the user stories by how absurd we could make a characters' abilities and what could work on the database side.

The beginning of our "happy path" started with login and authentication.  Some of the privileges that a Player has the ability to do are character creation, account creation, and even the ability to select an image of the web as their character image.  An admin has the ability to go in and see all the game Characters, items, player(s), and quests and edit them all individually. Then, we handled the game logic of battling characters and the plethora of options a player has. Methods were created to handle the logic of how much damage was done mathematically per attack. Here is a snippet of sample code.


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
		if (attack.getElement().equals(Element.dark)) {
			double percentResisted = ((this.getFireR() + this.getBloodR()) / 2) * modifier;
			modifiedDamage += modifyResistedDamage(percentResisted, attackPower, enemy);
		}

		double temp = modifiedDamage.doubleValue();
		return (int) temp;
	}
```

We are building a text RPG game with custom images in our project. We built our schema to include a player(user) table and a game_characters table, so that it would be easier for us to see the Many-to-Many relationship. Since we wanted to play each player's list of game characters each time we loaded the webpage, we created the ```private List<GameCharacter> gameCharacters``` field inside of Player entity. Since Spring supports the FetchType.Eager @nnotation for mapping relationships, I eagerly inserted an eager-fetch to load a list of stages each time a quest is started. In the src package for our MVC project, we have individual controllers to handle methods and views for game characters, gameplay, admin usage, characters' inventories, and quests/stages.  

Given more time, we would have liked to implement a "friendList" in which an online player can login and be able to send live-time messages to one another. Ideally, another cool feature users can exchange with friends is the ability to share the illustrious quests that they create. Also, we would have liked to spice up our battles, which would include implementation for multiple players to log on online and vigorously battle and kill each other's character.  
