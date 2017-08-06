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

	@RequestMapping(path="BattleGear.do")
	public ModelAndView battleGearRoute(ModelAndView mv, HttpSession session) {
		
		System.out.println("battleGearRoute() in controller");
		List<GameCharacter> gameCharacters = dao2.indexGameCharacters();
		mv.addObject("gameCharacters", gameCharacters);
		
		mv.setViewName("WEB-INF/views/gameplay/viewInventoryInQuest.jsp");
		return mv;
	}
	
	@RequestMapping(path="SetBattleGear.do")
	public ModelAndView setBattleGear(ModelAndView mv, HttpSession session, Integer id) {
		System.out.println("setBattleGear() in controller");
		GameCharacter character = dao2.showGameCharacter(id);
		List<Item> inventoryList = dao.inventory(character);
		
		Boolean empty = inventoryList.isEmpty();
		System.out.println("List is emtpy " + empty);
		
		
		mv.addObject("inventoryList", inventoryList);
		mv.setViewName("WEB-INF/views/gameplay/viewInventoryInQuest.jsp");	
		return mv;
	}
	
	
}
