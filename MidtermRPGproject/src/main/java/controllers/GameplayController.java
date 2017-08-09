package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import data.Ability;
import data.CharacterEditDao;
import data.GameCharacter;
import data.GameplayDao;
import data.Item;
import data.Player;
import data.Quest;
import data.RandNumGen;
import data.Stage;

@Controller
public class GameplayController {
	@Autowired
	private GameplayDao dao;

	@Autowired
	private CharacterEditDao cDao;

	private RandNumGen rng = new RandNumGen();
	
	private int stageCounter = 1;

	@RequestMapping(path = "GameplayRoute.do" /* , method = RequestMethod.GET */)
	public ModelAndView adminRoute(ModelAndView mv, HttpSession session) {
		// temp!
		// GameCharacter currentCharacter = dao.getDefaultGameCharacter();
		// session.setAttribute("currentCharacter", currentCharacter);
		// session.setAttribute("currentQuest", dao.getDefaultQuest());
		// temp!

		session.setAttribute("currentStage", ((Quest) session.getAttribute("currentQuest")).getStages().get(0));
		mv.addObject("currentQuest", (Quest) session.getAttribute("currentQuest"));
		stageCounter = 1;
		mv.addObject("currentStage", (Stage) session.getAttribute("currentStage"));
		mv.setViewName("WEB-INF/views/gameplay/questStart.jsp");
		return mv;
	}

	@RequestMapping(path = "GameplayStageStart.do" /* , method = RequestMethod.GET */)
	public ModelAndView gameplayStageStart(ModelAndView mv, HttpSession session) {
		mv.addObject("currentQuest", (Quest) session.getAttribute("currentQuest"));
		mv.addObject("currentStage", (Stage) session.getAttribute("currentStage"));
		stageCounter++;
		mv.setViewName("WEB-INF/views/gameplay/stageStart.jsp");
		return mv;
	}

	@RequestMapping(path = "GameplayStartBattle.do" /* , method = RequestMethod.GET */)
	public ModelAndView gameplayStartBattle(ModelAndView mv, HttpSession session) {

		GameCharacter currentCharacter = (GameCharacter) session.getAttribute("currentCharacter");
		GameCharacter enemyCharacter = ((Stage) session.getAttribute("currentStage")).getGameCharacter();
		enemyCharacter.setPower(currentCharacter.getPower()+(rng.getRNG(-currentCharacter.getPower()/20, stageCounter*currentCharacter.getPower()/10)));
		enemyCharacter.setHealth(currentCharacter.getHealth()+(rng.getRNG(-currentCharacter.getHealth()/20, (currentCharacter.getPower() + stageCounter*currentCharacter.getHealth()/10))));
		enemyCharacter.setEnergy(currentCharacter.getEnergy()+(rng.getRNG(-currentCharacter.getEnergy()/20, stageCounter*currentCharacter.getEnergy()/10)));
		
		
		currentCharacter.startFight();
		mv.addObject("newHealthCurrent", 100);
		mv.addObject("newEnergyCurrent", 100);
		enemyCharacter.startFight();
		mv.addObject("newHealthEnemy", 100);
		mv.addObject("newEnergyEnemy", 100);
		mv.addObject("currentCharacter", currentCharacter);
		mv.addObject("enemyCharacter", enemyCharacter);
		System.out.println(currentCharacter);
		System.out.println(enemyCharacter);

		List<Ability> abilities0 = new ArrayList<>();
		List<Ability> abilities1 = new ArrayList<>();
		abilities0.addAll(currentCharacter.getAbilities());
		for (Ability ability : abilities0) {
			if (ability.getEnergyCost() <= currentCharacter.getStamina()) {
				abilities1.add(ability);
			}
		}
		mv.addObject("currentAbilities", abilities1);

		session.setAttribute("currentCharacter", currentCharacter);
		session.setAttribute("enemyCharacter", enemyCharacter);

		mv.setViewName("WEB-INF/views/gameplay/battle.jsp");
		return mv;
	}

	@RequestMapping(path = "GameplayBattleLoop.do" /* , method = RequestMethod.GET */)
	public ModelAndView gameplayBattleLoop(ModelAndView mv, HttpSession session, String characterAbility) {
		GameCharacter winner = gameplayWinnerCheck(mv, session);
		System.out.println("begin");
		if (winner == null) {
			// player one turn
			System.out.println("\n---Player one turn---");
			GameCharacter currentCharacter = (GameCharacter) session.getAttribute("currentCharacter");
			mv.addObject("currentCharacter", currentCharacter);
			GameCharacter enemyCharacter = (GameCharacter) session.getAttribute("enemyCharacter");
			
			mv.addObject("enemyCharacter", enemyCharacter);

			System.out.println("1 health" + currentCharacter.getHp());
			System.out.println("1 energy" + currentCharacter.getStamina());
			System.out.println("2 health" + enemyCharacter.getHp());
			System.out.println("2 energy" + enemyCharacter.getStamina());
			List<Ability> abilities0 = new ArrayList<>();
			List<Ability> abilities1 = new ArrayList<>();
			Ability attack1 = null;
			attack1 = dao.useAbilityByName(characterAbility);
			System.out.println("attack from dao: " + attack1);
			System.out.println("abilities available: " + abilities1);
			int oldHealthEnemy = (int) (100 * ((double) enemyCharacter.getHp() / (double) enemyCharacter.getHealth()));

			System.out.println("old enemy " + oldHealthEnemy);

			abilities0.addAll(currentCharacter.getAbilities());
			for (Ability ability : abilities0) {
				if (ability.getEnergyCost() <= currentCharacter.getStamina()) {
					abilities1.add(ability);
				}
			}
			if (characterAbility.equals("Defend")) {
				System.out.println("defend");
				currentCharacter
						.addHp(rng.getRNG(currentCharacter.getHealth() / 20, currentCharacter.getHealth() / 10));
				currentCharacter
						.addStamina(rng.getRNG(currentCharacter.getEnergy() / 20, currentCharacter.getEnergy() / 10));
			} else if (abilities1.contains(attack1)) {
				System.out.println("attack");
				enemyCharacter.takeDamage(currentCharacter, attack1);
				currentCharacter.addStamina(-attack1.getEnergyCost());
			} else {
				System.out.println("default");
				attack1 = dao.useDefaultAbility();
				enemyCharacter.takeDamage(currentCharacter, attack1);
				currentCharacter.addStamina(-attack1.getEnergyCost());
			}

			if (attack1 != null) {
				mv.addObject("attackCurrent", attack1.getName());
				System.out.println("attack: " + attack1);
			} else {
				mv.addObject("attackCurrent", "Defend");
				System.out.println("attack: " + "Defend");
			}

			currentCharacter.addStamina(rng.getRNG(5, 10));
			System.out.println("stam" + currentCharacter.getStamina());

			int newEnergyCurrent = (int) (100
					* ((double) currentCharacter.getStamina() / (double) currentCharacter.getEnergy()));
			mv.addObject("newEnergyCurrent", newEnergyCurrent);
			System.out.println("energy current " + newEnergyCurrent);

			int newHealthEnemy = (int) (100 * ((double) enemyCharacter.getHp() / (double) enemyCharacter.getHealth()));
			mv.addObject("newHealthEnemy", (newHealthEnemy));
			mv.addObject("oldHealthEnemy", (oldHealthEnemy - newHealthEnemy));
			System.out.println("new enemy " + newHealthEnemy);

			abilities1.clear();
			for (Ability ability : abilities0) {
				if (ability.getEnergyCost() <= currentCharacter.getStamina()) {
					abilities1.add(ability);
				}
			}
			mv.addObject("currentAbilities", abilities1);
			winner = gameplayWinnerCheck(mv, session);
			System.out.println("win1 " + winner);

			if (winner == null) {
				// player two turn
				System.out.println("\n---Player two turn---");
				System.out.println("1 health" + currentCharacter.getHp());
				System.out.println("1 energy" + currentCharacter.getStamina());
				System.out.println("2 health" + enemyCharacter.getHp());
				System.out.println("2 energy" + enemyCharacter.getStamina());

				List<Ability> abilities2 = new ArrayList<>();
				abilities2.addAll(enemyCharacter.getAbilities());
				Ability attack2 = null;

				do {
					System.out.println(abilities2);
					int index = rng.getRNG(0, abilities2.size() - 1);
					attack2 = abilities2.remove(index);
					System.out.println("attack2 " + attack2);
					if (abilities2.isEmpty()) {
						attack2 = dao.useDefaultAbility();
						break;
					}
				} while (attack2 == null || attack2.getEnergyCost() >= enemyCharacter.getStamina());
				mv.addObject("attackEnemy", attack2.getName());
				System.out.println("attack enemy " + attack2);

				int oldHealthCurrent = (int) (100
						* ((double) currentCharacter.getHp() / (double) currentCharacter.getHealth()));

				System.out.println("old current " + oldHealthCurrent);

				currentCharacter.takeDamage(enemyCharacter, attack2);
				enemyCharacter.addStamina(-attack2.getEnergyCost());
				enemyCharacter.addStamina(rng.getRNG(5, 10));

				int newEnergyEnemy = (int) (100
						* ((double) enemyCharacter.getStamina() / (double) enemyCharacter.getEnergy()));
				mv.addObject("newEnergyEnemy", newEnergyEnemy);
				System.out.println("energy enemy " + newEnergyEnemy);

				int newHealthCurrent = (int) (100
						* ((double) currentCharacter.getHp() / (double) currentCharacter.getHealth()));
				mv.addObject("newHealthCurrent", newHealthCurrent);
				mv.addObject("oldHealthCurrent", (oldHealthCurrent - newHealthCurrent));
				System.out.println("new current " + newHealthCurrent);

				winner = gameplayWinnerCheck(mv, session);
				System.out.println("win2 " + winner);
				System.out.println("\n---end---");
				System.out.println("1 health" + currentCharacter.getHp());
				System.out.println("1 energy" + currentCharacter.getStamina());
				System.out.println("2 health" + enemyCharacter.getHp());
				System.out.println("2 energy" + enemyCharacter.getStamina());

				mv.addObject("winner", winner);
				mv.setViewName("WEB-INF/views/gameplay/battle.jsp");
				return mv;
			}
			mv.addObject("winner", winner);
			mv.setViewName("GameplayConcludeBattle.do");
			return mv;
		}
		mv.addObject("winner", winner);
		mv.setViewName("GameplayConcludeBattle.do");
		return mv;
	}

	@RequestMapping(path = "GameplayConcludeBattle.do" /* , method = RequestMethod.GET */)
	public ModelAndView gameplayConcludeBattle(ModelAndView mv, HttpSession session) {
		GameCharacter winner = gameplayWinnerCheck(mv, session);
		System.out.println("win3 " + winner);
		GameCharacter currentCharacter = (GameCharacter) session.getAttribute("currentCharacter");
		currentCharacter.endFight();
		currentCharacter.lvlUp();
		cDao.update(currentCharacter, currentCharacter.getId());

		mv.addObject("currentCharacter", currentCharacter);
		GameCharacter enemyCharacter = (GameCharacter) session.getAttribute("enemyCharacter");
		enemyCharacter.endFight();
		mv.addObject("enemyCharacter", enemyCharacter);
		mv.addObject("winner", winner);
		System.out.println("dead?");

		if (winner.getId() == currentCharacter.getId()) {
			System.out.println("dead? no");
			Item reward = dao.addItemToGameCharacter(currentCharacter);
			mv.addObject("currentCharacter", currentCharacter);
			mv.addObject("reward", reward);
			System.out.println("items: " + currentCharacter.getInventory().getItems());
			mv.setViewName("WEB-INF/views/gameplay/stageConclusion.jsp");
			return mv;
		} else {
			System.out.println("dead? yes");
			cDao.killChar(currentCharacter);
			mv.setViewName("WEB-INF/views/gameplay/battle.jsp");
			return mv;
		}
	}

	@RequestMapping(path = "GameplayEndOfStage.do" /* , method = RequestMethod.GET */)
	public ModelAndView gameplayEndOfStage(ModelAndView mv, HttpSession session) {
		Stage nextStage = dao.getNextStage((Quest) session.getAttribute("currentQuest"),
				(Stage) session.getAttribute("currentStage"));
		if (nextStage == null) {
			mv.setViewName("GameplayEndOfQuest.do");
		} else {
			session.setAttribute("currentStage", nextStage);
			mv.setViewName("WEB-INF/views/gameplay/stageStart.jsp");
		}
		return mv;
	}

	@RequestMapping(path = "GameplayEndOfQuest.do" /* , method = RequestMethod.GET */)
	public ModelAndView gameplayEndOfQuest(ModelAndView mv, HttpSession session) {
		GameCharacter currentCharacter = (GameCharacter) session.getAttribute("currentCharacter");
		currentCharacter.unequipGear();
		cDao.update(currentCharacter, currentCharacter.getId());
		mv.setViewName("WEB-INF/views/gameplay/questConclusion.jsp");
		return mv;
	}

	public GameCharacter gameplayWinnerCheck(ModelAndView mv, HttpSession session) {
		GameCharacter currentCharacter = (GameCharacter) session.getAttribute("currentCharacter");
		GameCharacter enemyCharacter = (GameCharacter) session.getAttribute("enemyCharacter");

		if (currentCharacter.checkIfDead()) {
			System.out.println(currentCharacter.getName());
			return enemyCharacter;
		} else if (enemyCharacter.checkIfDead()) {
			System.out.println(enemyCharacter.getName());
			return currentCharacter;
		}

		return null;
	}

}
