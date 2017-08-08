package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import data.AdminDao;
import data.GameCharacter;
import data.GameplayDao;
import data.InventoryShopItemDao;
import data.Item;

@Controller
public class InventoryShopItemController {

	@Autowired
	private InventoryShopItemDao dao;

	@Autowired
	private GameplayDao dao2;

	@RequestMapping(path = "BattleGear.do")
	public ModelAndView battleGearRoute(ModelAndView mv, HttpSession session) {


		GameCharacter currentCharacter = (GameCharacter) session.getAttribute("currentCharacter");

		mv.addObject("currentCharacter", currentCharacter);
		mv.setViewName("ViewBattleGear.do");
		return mv;
	}

	@RequestMapping(path = "ViewBattleGear.do")
	public ModelAndView viewBattleGear(ModelAndView mv, HttpSession session) {
		GameCharacter currentCharacter = (GameCharacter) session.getAttribute("currentCharacter");

		mv = addInventoryToModelAndView(mv, currentCharacter);

		System.out.println("in viewBattleGear() in controller");

		mv.setViewName("WEB-INF/views/gameplay/viewInventoryInQuest.jsp");
		return mv;

	}

	@RequestMapping(path = "SetBattleGear.do")
	public ModelAndView setBattleGear(ModelAndView mv, HttpSession session, Integer weaponId) {
		GameCharacter currentCharacter = (GameCharacter) session.getAttribute("currentCharacter");

		mv = addInventoryToModelAndView(mv, currentCharacter);

		List<Item> battleGear = new ArrayList<>();
		battleGear.add(dao.getItemFromGameCharacter(currentCharacter, weaponId));

		for (Item item : battleGear) {
			currentCharacter.useItem(item);

		}

		mv.addObject("afterStats", currentCharacter);
		mv.addObject("gameCharacterId", currentCharacter.getId());
		mv.setViewName("WEB-INF/views/gameplay/viewInventoryInQuest.jsp");

		return mv;
	}

	private ModelAndView addInventoryToModelAndView(ModelAndView mv, GameCharacter gameCharacter) {
		if (dao.checkForInventory(gameCharacter) == true) {
			List<Item> inventory = dao.inventory(gameCharacter);
			mv.addObject("inventory", inventory);

			if (dao.checkForWeapons(gameCharacter) == true) {
				List<Item> weapons = dao.weapons(gameCharacter);
				mv.addObject("weapons", weapons);
				System.out.println("weapon check = true");

			} else {
				String unarmedWarning = "How do you expect to fight the hordes of darkness?";
				System.out.println("weapon check = false");
				mv.addObject("unarmedWarning", unarmedWarning);
			}

			if (dao.checkForArmor(gameCharacter) == true) {
				System.out.println("armor check = true");
				List<Item> armor = dao.armor(gameCharacter);
				mv.addObject("armor", armor);
			} else {
				System.out.println("armor check = false");
				String noArmorWarning = "Not even undergarments?";
				mv.addObject("noArmorWarning", noArmorWarning);
			}

			if (dao.checkForEdibles(gameCharacter) == true) {
				System.out.println("edibles check = true");
				List<Item> edibles = dao.edibles(gameCharacter);
				mv.addObject("edibles", edibles);
			} else {
				System.out.println("edibles check = false");
				String noEdibles = "Who needs buffs anyway?";
				mv.addObject("noEdibles", noEdibles);
			}

		} else {
			System.out.println("inventory check = false");
			String noItems = "You go forth into the world cold and alone";
			mv.addObject("noItems", noItems);
		}
		return mv;
	}

}
