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

		List<GameCharacter> gameCharacter = dao2.indexGameCharacters();

		mv.addObject("gameCharacters", gameCharacter);
		mv.setViewName("WEB-INF/views/gameplay/viewInventoryInQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "ViewBattleGear.do")
	public ModelAndView viewBattleGear(ModelAndView mv, HttpSession session, Integer gameCharacterId) {
		GameCharacter gameCharacter = dao2.showGameCharacter(gameCharacterId);
		session.setAttribute("gameCharacter", gameCharacter);

		GameCharacter beforeGameCharacter = dao2.showGameCharacter(gameCharacterId);
		session.setAttribute("beforeStats", beforeGameCharacter);
//		session.getAttribute("currentCharacter");
		
		mv = addInventoryToModelAndView(mv, gameCharacter);
		
		mv.addObject("beforeStats", gameCharacter);

		System.out.println("in viewBattleGear() in controller");

		mv.setViewName("WEB-INF/views/gameplay/viewInventoryInQuest.jsp");
		return mv;

	}

	@RequestMapping(path = "SetBattleGear.do")
	public ModelAndView setBattleGear(ModelAndView mv, HttpSession session, Integer weaponId) {
		GameCharacter gameCharacter = (GameCharacter) session.getAttribute("gameCharacter");
		
		mv = addInventoryToModelAndView(mv, gameCharacter);

		System.out.println("GameCharacter in Controller: " + gameCharacter.getName() + " has a power of "
				+ gameCharacter.getPower() + "and a physical resistance of " + gameCharacter.getPhysicalR()
				+ " before equipment");

		List<Item> battleGear = new ArrayList<>();
		battleGear.add(dao.getItemFromGameCharacter(gameCharacter, weaponId));
		System.out.println("Item Name:" + dao.getItemFromGameCharacter(gameCharacter, weaponId).getName());

			for (Item item : battleGear) {
				System.out.println("in for loop, battleGear size is " + battleGear.size());
				gameCharacter.useItem(battleGear.get(0));
			}
			
		System.out.println("After for loop, battleGear size: " + battleGear.size());
		System.out.println("GameCharacter in Controller: " + gameCharacter.getName() + " has a power of "
				+ gameCharacter.getPower() + " physical resistance of " + gameCharacter.getPhysicalR()
				+ " after equipment");

		mv.addObject("afterStats", gameCharacter);
		mv.addObject("gameCharacterId", gameCharacter.getId());
		mv.setViewName("WEB-INF/views/gameplay/viewInventoryInQuest.jsp");

		return mv;
	}
	
	private ModelAndView addInventoryToModelAndView(ModelAndView mv, GameCharacter gameCharacter) {
		if (dao.checkForInventory(gameCharacter) == true) {
			System.out.println("inventory check = true");
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
