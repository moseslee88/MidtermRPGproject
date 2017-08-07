package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
		mv.setViewName("WEB-INF/views/gameplay/viewInventoryInQuest.jsp");
		return mv;
	}
	
	@RequestMapping(path="setBattleGear.do")
	public ModelAndView setBattleGear(ModelAndView mv, HttpSession session, GameCharacter gameCharacter) {
		
		
		List<Item> inventoryList = dao.inventory(gameCharacter);
		
		
		mv.addObject("inventoryList", inventoryList);
		return mv;
	}
	
	@RequestMapping(path="GetCharacters.do")
	public ModelAndView viewCharacters(ModelAndView mv, HttpSession session) {
		List<GameCharacter> gameCharacters = dao2.indexGameCharacters();

		mv.addObject("gameCharacters", gameCharacters);

		mv.setViewName("/WEB-INF/views/admin/viewInventoryInQuest.jsp");
		return mv;
	}
	
}
