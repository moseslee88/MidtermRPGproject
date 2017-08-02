package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.AdminDao;
import data.GameCharacter;
import data.Item;
import data.Player;
import data.PlayerEditDao;
import data.PlayerEditDaoImpl;
import data.Quest;

@Controller
public class AdminController {

	@Autowired
	private AdminDao dao;
	
	@RequestMapping(path = "AdminRoute.do" /* , method = RequestMethod.GET */)
	public ModelAndView adminRoute(ModelAndView mv, HttpSession session) {
		PlayerEditDao ped = new PlayerEditDaoImpl();
		//implement admin check!

		mv.setViewName("WEB-INF/views/admin/admin.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetGameCharacters.do" /* , method = RequestMethod.GET */)
	public ModelAndView showGameCharacters(ModelAndView mv, HttpSession session) {

		List<GameCharacter> characters = dao.indexGameCharacters();

		mv.addObject("characters", characters);

		mv.setViewName("WEB-INF/views/admin/adminGameCharacter.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetGameCharacter.do" /* , method = RequestMethod.GET */)
	public ModelAndView showGameCharacter(@RequestParam("id") Integer id, ModelAndView mv, HttpSession session) {

		GameCharacter gameCharacter = dao.showGameCharacter(id);

		mv.addObject("gameCharacter", gameCharacter);

		mv.setViewName("WEB-INF/views/admin/adminGameCharacter.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminNewGameCharacter.do" /* , method = RequestMethod.POST */)
	public ModelAndView newGameCharacter(ModelAndView mv, GameCharacter gameCharacter, HttpSession session) {

		dao.createGameCharacter(gameCharacter);
		mv.addObject("gameCharacter", gameCharacter);

		mv.setViewName("WEB-INF/views/admin/adminGameCharacter.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminEditGameCharacter.do" /* , method = RequestMethod.POST */)
	public ModelAndView editGameCharacter(ModelAndView mv, Integer id, GameCharacter gameCharacter,
			HttpSession session) {

		GameCharacter gameCharacter2 = dao.updateGameCharacter(id, gameCharacter);
		mv.addObject("gameCharacter", gameCharacter2);

		mv.setViewName("WEB-INF/views/admin/adminGameCharacter.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminDeleteGameCharacter.do" /* , method = RequestMethod.GET */)
	public ModelAndView deleteGameCharacter(ModelAndView mv, Integer id, HttpSession session) {

		Boolean successBool = dao.destroyGameCharacter(id);
		mv.addObject("successBool", successBool);

		mv.setViewName("WEB-INF/views/admin/adminGameCharacter.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetItems.do" /* , method = RequestMethod.GET */)
	public ModelAndView showItems(ModelAndView mv, HttpSession session) {

		List<Item> characters = dao.indexItems();

		mv.addObject("characters", characters);

		mv.setViewName("WEB-INF/views/admin/adminItem.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetItem.do" /* , method = RequestMethod.GET */)
	public ModelAndView showItem(@RequestParam("id") Integer id, ModelAndView mv, HttpSession session) {

		Item item = dao.showItem(id);

		mv.addObject("item", item);

		mv.setViewName("WEB-INF/views/admin/adminItem.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminNewItem.do" /* , method = RequestMethod.POST */)
	public ModelAndView newItem(ModelAndView mv, Item item, HttpSession session) {

		dao.createItem(item);
		mv.addObject("item", item);

		mv.setViewName("WEB-INF/views/admin/adminItem.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminEditItem.do" /* , method = RequestMethod.POST */)
	public ModelAndView editItem(ModelAndView mv, Integer id, Item item, HttpSession session) {

		Item item2 = dao.updateItem(id, item);
		mv.addObject("item", item2);

		mv.setViewName("WEB-INF/views/admin/adminItem.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminDeleteItem.do" /* , method = RequestMethod.GET */)
	public ModelAndView deleteItem(ModelAndView mv, Integer id, HttpSession session) {

		Boolean successBool = dao.destroyItem(id);
		mv.addObject("successBool", successBool);

		mv.setViewName("WEB-INF/views/admin/adminItem.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetPlayers.do" /* , method = RequestMethod.GET */)
	public ModelAndView showPlayers(ModelAndView mv, HttpSession session) {

		List<Player> characters = dao.indexPlayers();

		mv.addObject("characters", characters);

		mv.setViewName("WEB-INF/views/admin/adminPlayer.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetPlayer.do" /* , method = RequestMethod.GET */)
	public ModelAndView showPlayer(@RequestParam("id") Integer id, ModelAndView mv, HttpSession session) {

		Player player = dao.showPlayer(id);

		mv.addObject("player", player);

		mv.setViewName("WEB-INF/views/admin/adminPlayer.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminNewPlayer.do" /* , method = RequestMethod.POST */)
	public ModelAndView newPlayer(ModelAndView mv, Player player, HttpSession session) {

		dao.createPlayer(player);
		mv.addObject("player", player);

		mv.setViewName("WEB-INF/views/admin/adminPlayer.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminEditPlayer.do" /* , method = RequestMethod.POST */)
	public ModelAndView editPlayer(ModelAndView mv, Integer id, Player player, HttpSession session) {

		Player player2 = dao.updatePlayer(id, player);
		mv.addObject("player", player2);

		mv.setViewName("WEB-INF/views/admin/adminPlayer.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminDeletePlayer.do" /* , method = RequestMethod.GET */)
	public ModelAndView deletePlayer(ModelAndView mv, Integer id, HttpSession session) {

		Boolean successBool = dao.destroyPlayer(id);
		mv.addObject("successBool", successBool);

		mv.setViewName("WEB-INF/views/admin/adminPlayer.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetQuests.do" /* , method = RequestMethod.GET */)
	public ModelAndView showQuests(ModelAndView mv, HttpSession session) {

		List<Quest> characters = dao.indexQuests();

		mv.addObject("characters", characters);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetQuest.do" /* , method = RequestMethod.GET */)
	public ModelAndView showQuest(@RequestParam("id") Integer id, ModelAndView mv, HttpSession session) {

		Quest quest = dao.showQuest(id);

		mv.addObject("quest", quest);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminNewQuest.do" /* , method = RequestMethod.POST */)
	public ModelAndView newQuest(ModelAndView mv, Quest quest, HttpSession session) {

		dao.createQuest(quest);
		mv.addObject("quest", quest);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminEditQuest.do" /* , method = RequestMethod.POST */)
	public ModelAndView editQuest(ModelAndView mv, Integer id, Quest quest, HttpSession session) {

		Quest quest2 = dao.updateQuest(id, quest);
		mv.addObject("quest", quest2);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminDeleteQuest.do" /* , method = RequestMethod.GET */)
	public ModelAndView deleteQuest(ModelAndView mv, Integer id, HttpSession session) {

		Boolean successBool = dao.destroyQuest(id);
		mv.addObject("successBool", successBool);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}
}
