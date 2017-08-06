package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import data.Ability;
import data.GameCharacter;
import data.GameplayDao;
import data.RandNumGen;
import data.Stage;

@Controller
public class GameplayController {
	@Autowired
	private GameplayDao dao;

	private RandNumGen rng = new RandNumGen();

	private Stage currentStage;

	private List<GameCharacter> participants = new ArrayList<>();

	@RequestMapping(path = "GameplayRoute.do" /* , method = RequestMethod.GET */)
	public ModelAndView adminRoute(ModelAndView mv, HttpSession session) {
		currentStage = dao.getDefaultStage();

		mv.setViewName("GameplayStartBattle.do");
		return mv;
	}

	@RequestMapping(path = "GameplayStartBattle.do" /* , method = RequestMethod.GET */)
	public ModelAndView gameplayStartBattle(ModelAndView mv, HttpSession session) {

		GameCharacter enemyCharacter = dao.getDefaultGameCharacter();
		// GameCharacter currentCharacter = (GameCharacter)
		// session.getAttribute("currentCharacter");
		GameCharacter currentCharacter = dao.getDefaultGameCharacter();

		mv.addObject("currentCharacter", currentCharacter);
		mv.addObject("enemyCharacter", enemyCharacter);
		System.out.println(currentCharacter);
		System.out.println(enemyCharacter);

		currentCharacter.startFight();
		enemyCharacter.startFight();

		participants.add(currentCharacter);
		participants.add(enemyCharacter);

		mv.setViewName("WEB-INF/views/gameplay/battle.jsp");
		return mv;
	}

	@RequestMapping(path = "GameplayBattleLoop.do" /* , method = RequestMethod.GET */)
	public ModelAndView gameplayBattleLoop(ModelAndView mv, HttpSession session) {
		GameCharacter winner = gameplayWinnerCheck(mv, session);
		System.out.println("begin");
		if (winner == null) {
			// player one turn
			System.out.println("\n---Player one turn---");
			GameCharacter currentCharacter = participants.get(0);
			GameCharacter enemyCharacter = participants.get(1);

			System.out.println("1 health" + currentCharacter.getHp());
			System.out.println("1 energy" + currentCharacter.getStamina());
			System.out.println("2 health" + enemyCharacter.getHp());
			System.out.println("2 energy" + enemyCharacter.getStamina());
			List<Ability> abilities1 = new ArrayList<>();
			abilities1.addAll(currentCharacter.getAbilities());
			for (Ability ability : abilities1) {
				if (ability.getEnergyCost() > currentCharacter.getStamina()) {
					abilities1.remove(ability);
				}
			}
			Ability attack1 = null;

			do {
				if (abilities1.isEmpty()) {
					attack1 = dao.useDefaultAbility();
					break;
				}
				System.out.println(abilities1);
				attack1 = abilities1.remove(/* new Random().nextInt */(abilities1.size()) - 1);
				System.out.println("attack1 " + attack1);

			} while (attack1 == null || attack1.getEnergyCost() >= currentCharacter.getStamina());
			mv.addObject("attackCurrent", attack1.getName());
			System.out.println(attack1);

			int oldHealthEnemy = (int) (100 * ((double) enemyCharacter.getHp() / (double) enemyCharacter.getHealth()));

			mv.addObject("oldHealthEnemy", "width: " + oldHealthEnemy + "%");
			System.out.println("old enemy " + oldHealthEnemy);

			enemyCharacter.takeDamage(currentCharacter, attack1);
			currentCharacter.addStamina(-attack1.getEnergyCost());
			currentCharacter.addStamina(rng.getRNG(5, 10));
			System.out.println("stam" + currentCharacter.getStamina());

			int newEnergyCurrent = (int) (100
					* ((double) currentCharacter.getStamina() / (double) currentCharacter.getEnergy()));
			mv.addObject("newEnergyCurrent", "width: " + newEnergyCurrent + "%");
			System.out.println("energy current " + newEnergyCurrent);

			int newHealthEnemy = (int) (100 * ((double) enemyCharacter.getHp() / (double) enemyCharacter.getHealth()));
			mv.addObject("newHealthEnemy", "width: " + (oldHealthEnemy - newHealthEnemy) + "%");
			System.out.println("new enemy " + newHealthEnemy);

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
					attack2 = abilities2.remove(/* new Random().nextInt */(abilities2.size()) - 1);
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

				mv.addObject("oldHealthCurrent", "width: " + oldHealthCurrent + "%");
				System.out.println("old current " + oldHealthCurrent);

				currentCharacter.takeDamage(enemyCharacter, attack2);
				enemyCharacter.addStamina(-attack1.getEnergyCost());
				enemyCharacter.addStamina(rng.getRNG(5, 10));

				int newEnergyEnemy = (int) (100
						* ((double) enemyCharacter.getStamina() / (double) enemyCharacter.getEnergy()));
				mv.addObject("newEnergyEnemy", "width: " + newEnergyEnemy + "%");
				System.out.println("energy enemy " + newEnergyEnemy);

				int newHealthCurrent = (int) (100
						* ((double) currentCharacter.getHp() / (double) currentCharacter.getHealth()));
				mv.addObject("newHealthCurrent", "width: " + (oldHealthCurrent - newHealthCurrent) + "%");
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

		mv.addObject("winner", winner);
		mv.setViewName("WEB-INF/views/gameplay/battle.jsp");
		return mv;
	}

	public GameCharacter gameplayWinnerCheck(ModelAndView mv, HttpSession session) {

		if (participants != null) {
			if (participants.get(0).checkIfDead()) {
				System.out.println(participants.get(0).getName());
				return participants.get(1);
			} else if (participants.get(1).checkIfDead()) {
				System.out.println(participants.get(1).getName());
				return participants.get(0);
			}
		}
		return null;
	}

	public void gameplayMonsterTurn(ModelAndView mv, HttpSession session) {

	}

}
