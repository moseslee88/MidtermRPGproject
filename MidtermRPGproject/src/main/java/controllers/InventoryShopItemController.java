package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.AdminDao;
import data.CharacterEditDao;
import data.GameCharacter;
import data.InventoryShopItemDao;
import data.Item;

@Controller
public class InventoryShopItemController {

	@Autowired
	private InventoryShopItemDao dao;

	@Autowired
	private AdminDao dao2;

	@RequestMapping(path = "BattleGear.do")
	public ModelAndView battleGearRoute(ModelAndView mv, HttpSession session) {

		List<GameCharacter> gameCharacters = dao2.indexGameCharacters();
		mv.addObject("gameCharacters", gameCharacters);

		mv.setViewName("WEB-INF/views/gameplay/viewInventoryInQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "ViewBattleGear.do")
	public ModelAndView viewBattleGear(ModelAndView mv, HttpSession session, Integer id) {
		GameCharacter character = dao2.showGameCharacter(id);

		System.out.println("in viewBattleGear() in controller");

		if (dao.checkForInventory(character) == true) {
			System.out.println("inventory check = true");
			List<Item> inventory = dao.inventory(character);
			mv.addObject("inventory", inventory);

			if (dao.checkForWeapons(character) == true) {
				List<Item> weapons = dao.weapons(character);
				mv.addObject("weapons", weapons);
				System.out.println("weapon check = true");

			} else {
				String unarmedWarning = "How do you expect to fight the hordes of darkness?";
				System.out.println("weapon check = false");
				mv.addObject("unarmedWarning", unarmedWarning);
			}

			if (dao.checkForArmor(character) == true) {
				List<Item> armor = dao.armor(character);
				mv.addObject("armor", armor);
			} else {
				String noArmorWarning = "Not even undergarments?";
				mv.addObject("noArmorWarning", noArmorWarning);
			}

			if (dao.checkForEdibles(character) == true) {
				List<Item> edibles = dao.edibles(character);
				mv.addObject("edibles", edibles);
			} else {
				String noEdibles = "Who needs buffs anyway?";
				mv.addObject("noEdibles", noEdibles);
			}

		} else {
			System.out.println("inventory check = false");
			String noItems = "You go forth into the world cold and alone";
			mv.addObject("noItems", noItems);
		}

		mv.setViewName("WEB-INF/views/gameplay/viewInventoryInQuest.jsp");
		return mv;
	}

}
