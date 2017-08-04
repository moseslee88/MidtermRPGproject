package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import data.Ability;
import data.GameCharacter;
import data.GameplayDao;
import data.Stage;

@Controller
public class GameplayController {
	@Autowired
	private GameplayDao dao;

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

		GameCharacter enemyCharacter = currentStage.getGameCharacter();
//		GameCharacter currentCharacter = (GameCharacter) session.getAttribute("currentCharacter");
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
		if (winner != null) {
			// player one turn
			GameCharacter currentCharacter = participants.get(0);
			GameCharacter enemyCharacter = participants.get(1);

			List<Ability> abilities1 = currentCharacter.getAbilities();
			Ability attack1 = null;

			do {
				attack1 = abilities1.remove(new Random().nextInt(abilities1.size()));
				if (abilities1.isEmpty()) {
					attack1 = dao.useDefaultAbility();
					break;
				}
			} while (attack1 == null || attack1.getEnergyCost() >= currentCharacter.getEnergy());
			mv.addObject("attackCurrent", attack1.getName());
			System.out.println(attack1);

			int oldHealthEnemy = (int) (100 * (enemyCharacter.getHp() / enemyCharacter.getHealth()));
			mv.addObject("oldHealthEnemy", "width: " + oldHealthEnemy + "%");
			System.out.println("old enemy " + oldHealthEnemy);

			enemyCharacter.takeDamage(currentCharacter, attack1);
			currentCharacter.addStamina(-attack1.getEnergyCost());
			currentCharacter.addStamina(5);

			int newEnergyCurrent = (int) (100 * (currentCharacter.getStamina() / currentCharacter.getEnergy()));
			mv.addObject("newEnergyCurrent", "width: " + newEnergyCurrent + "%");
			System.out.println("energy current " + newEnergyCurrent);

			int newHealthEnemy = (int) (100 * (enemyCharacter.getHp() / enemyCharacter.getHealth()));
			mv.addObject("newHealthEnemy", "width: " + (oldHealthEnemy - newHealthEnemy) + "%");
			System.out.println("new enemy " + newHealthEnemy);

			winner = gameplayWinnerCheck(mv, session);
			System.out.println("win1 " + winner);

			if (winner != null) {
				// player two turn

				List<Ability> abilities2 = enemyCharacter.getAbilities();
				Ability attack2 = null;

				do {
					attack2 = abilities2.remove(new Random().nextInt(abilities2.size()));
					if (abilities2.isEmpty()) {
						attack2 = dao.useDefaultAbility();
						break;
					}
				} while (attack2 == null || attack2.getEnergyCost() >= enemyCharacter.getEnergy());
				mv.addObject("attackEnemy", attack2.getName());
				System.out.println("attack enemy " + attack2);

				int oldHealthCurrent = (int) (100 * (currentCharacter.getHp() / currentCharacter.getHealth()));
				mv.addObject("oldHealthCurrent", "width: " + oldHealthCurrent + "%");
				System.out.println("old current " + oldHealthCurrent);

				enemyCharacter.takeDamage(currentCharacter, attack2);
				enemyCharacter.addStamina(5);

				int newEnergyEnemy = (int) (100 * (enemyCharacter.getStamina() / enemyCharacter.getEnergy()));
				mv.addObject("newEnergyEnemy", "width: " + newEnergyEnemy + "%");
				System.out.println("energy enemy " + newEnergyEnemy);

				int newHealthCurrent = (int) (100 * (currentCharacter.getHp() / currentCharacter.getHealth()));
				mv.addObject("newHealthCurrent", "width: " + (oldHealthCurrent - newHealthCurrent) + "%");
				System.out.println("new current " + newHealthCurrent);

				winner = gameplayWinnerCheck(mv, session);
				System.out.println("win2 " + winner);
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
				return participants.get(1);
			} else if (participants.get(1).checkIfDead()) {
				return participants.get(0);
			}
		}
		return null;
	}

	public void gameplayMonsterTurn(ModelAndView mv, HttpSession session) {

	}

}
