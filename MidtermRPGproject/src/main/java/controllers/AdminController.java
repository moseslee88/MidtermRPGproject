package controllers;

import java.util.ArrayList;
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
import data.Stage;


@Controller
public class AdminController {

	@Autowired
	private AdminDao dao;

	@RequestMapping(path = "AdminRoute.do" /* , method = RequestMethod.GET */)
	public ModelAndView adminRoute(ModelAndView mv, HttpSession session) {
		PlayerEditDao ped = new PlayerEditDaoImpl();
		// implement admin check!

		mv.setViewName("WEB-INF/views/admin/admin.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetGameCharacters.do" /* , method = RequestMethod.GET */)
	public ModelAndView showGameCharacters(ModelAndView mv, HttpSession session) {

		List<GameCharacter> gameCharacters = dao.indexGameCharacters();

		mv.addObject("gameCharacters", gameCharacters);

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
		List<GameCharacter> gameCharacters = dao.indexGameCharacters();

		mv.addObject("gameCharacters", gameCharacters);

		mv.setViewName("WEB-INF/views/admin/adminGameCharacter.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminEditGameCharacter.do" /* , method = RequestMethod.POST */)
	public ModelAndView editGameCharacter(ModelAndView mv, Integer id, GameCharacter gameCharacter,
			HttpSession session) {

		dao.updateGameCharacter(id, gameCharacter);

		List<GameCharacter> gameCharacters = dao.indexGameCharacters();

		mv.addObject("gameCharacters", gameCharacters);

		mv.setViewName("WEB-INF/views/admin/adminGameCharacter.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminDeleteGameCharacter.do" /* , method = RequestMethod.GET */)
	public ModelAndView deleteGameCharacter(ModelAndView mv, Integer id, HttpSession session) {

		Boolean successBool = dao.destroyGameCharacter(id);
		mv.addObject("successBool", successBool);

		List<GameCharacter> gameCharacters = dao.indexGameCharacters();

		mv.addObject("gameCharacters", gameCharacters);

		mv.setViewName("WEB-INF/views/admin/adminGameCharacter.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetItems.do" /* , method = RequestMethod.GET */)
	public ModelAndView showItems(ModelAndView mv, HttpSession session) {

		List<Item> items = dao.indexItems();

		mv.addObject("items", items);

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
		List<Item> items = dao.indexItems();

		mv.addObject("items", items);

		mv.setViewName("WEB-INF/views/admin/adminItem.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminEditItem.do" /* , method = RequestMethod.POST */)
	public ModelAndView editItem(ModelAndView mv, Integer id, Item item, HttpSession session) {

		dao.updateItem(id, item);

		List<Item> items = dao.indexItems();

		mv.addObject("items", items);

		mv.setViewName("WEB-INF/views/admin/adminItem.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminDeleteItem.do" /* , method = RequestMethod.GET */)
	public ModelAndView deleteItem(ModelAndView mv, Integer id, HttpSession session) {

		Boolean successBool = dao.destroyItem(id);
		mv.addObject("successBool", successBool);

		List<Item> items = dao.indexItems();

		mv.addObject("items", items);

		mv.setViewName("WEB-INF/views/admin/adminItem.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetPlayers.do" /* , method = RequestMethod.GET */)
	public ModelAndView showPlayers(ModelAndView mv, HttpSession session) {

		List<Player> players = dao.indexPlayers();

		mv.addObject("players", players);

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
	public ModelAndView newPlayer( ModelAndView mv, Player player, Integer integerUserTypeId, HttpSession session) {
//		player.setUserType(new UserType(integerUserTypeId));
		
		String emailError = "This Email Already Exists!";
		String displayNameError = "This Display Name Already Exists!";
		
		if(dao.checkEmail(player) == true){
			mv.addObject("emailError", emailError);
		}	
		
		if(dao.checkDisplayName(player) == true) {
			mv.addObject("displayError", displayNameError);
		}
		dao.createPlayer(player);
		List<Player> players = dao.indexPlayers();

		mv.addObject("players", players);

		mv.setViewName("WEB-INF/views/admin/adminPlayer.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminEditPlayer.do" /* , method = RequestMethod.POST */)
	public ModelAndView editPlayer(ModelAndView mv, Integer id, Player player, HttpSession session) {

		dao.updatePlayer(id, player);

		List<Player> players = dao.indexPlayers();

		mv.addObject("players", players);

		mv.setViewName("WEB-INF/views/admin/adminPlayer.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminDeletePlayer.do" /* , method = RequestMethod.GET */)
	public ModelAndView deletePlayer(ModelAndView mv, Integer id, HttpSession session) {

		Boolean successBool = dao.destroyPlayer(id);
		mv.addObject("successBool", successBool);

		List<Player> players = dao.indexPlayers();

		mv.addObject("players", players);

		mv.setViewName("WEB-INF/views/admin/adminPlayer.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetQuests.do" /* , method = RequestMethod.GET */)
	public ModelAndView showQuests(ModelAndView mv, HttpSession session) {

		List<Quest> quests = dao.indexQuests();

		mv.addObject("quests", quests);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminGetQuest.do" /* , method = RequestMethod.GET */)
	public ModelAndView showQuest(@RequestParam("id") Integer id, ModelAndView mv, HttpSession session) {

		List<GameCharacter> gameCharacters = dao.indexGameCharacters();

		mv.addObject("gameCharacters", gameCharacters);
		
		Quest quest = dao.showQuest(id);

		mv.addObject("quest", quest);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminNewQuest.do" /* , method = RequestMethod.POST */)
	public ModelAndView newQuest(ModelAndView mv, Quest quest, HttpSession session) {

		dao.createQuest(quest);
		List<Quest> quests = dao.indexQuests();

		mv.addObject("quests", quests);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminEditQuest.do" /* , method = RequestMethod.POST */)
	public ModelAndView editQuest(ModelAndView mv, Integer id, Quest quest, HttpSession session) {

		dao.updateQuest(id, quest);

		List<Quest> quests = dao.indexQuests();

		mv.addObject("quests", quests);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminDeleteQuest.do" /* , method = RequestMethod.GET */)
	public ModelAndView deleteQuest(ModelAndView mv, Integer id, HttpSession session) {

		Boolean successBool = dao.destroyQuest(id);
		mv.addObject("successBool", successBool);

		List<Quest> quests = dao.indexQuests();

		mv.addObject("quests", quests);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminNewStage.do" /* , method = RestageMethod.POST */)
	public ModelAndView newStage(ModelAndView mv, Integer questId, Stage stage, Integer gameCharacterId, HttpSession session) {
		
		
		List<GameCharacter> gameCharacters = dao.indexGameCharacters();

		mv.addObject("gameCharacters", gameCharacters);

		Quest quest = dao.showQuest(questId);

		mv.addObject("quest", quest);
		
		stage.setGameCharacter(dao.showGameCharacter(gameCharacterId));
		
		List<Quest> quests = new ArrayList<Quest>();
		quests.add(quest);
		stage.setQuests(quests);
		dao.createStage(stage);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminEditStage.do" /* , method = RestageMethod.POST */)
	public ModelAndView editStage(ModelAndView mv, Integer questId, Integer id, Stage stage, HttpSession session) {

		dao.updateStage(id, stage);

		Quest quest = dao.showQuest(questId);
		
		List<GameCharacter> gameCharacters = dao.indexGameCharacters();

		mv.addObject("gameCharacters", gameCharacters);

		mv.addObject("quest", quest);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}

	@RequestMapping(path = "AdminDeleteStage.do" /* , method = RestageMethod.GET */)
	public ModelAndView deleteStage(ModelAndView mv, Integer questId, Integer id, HttpSession session) {

		
		List<GameCharacter> gameCharacters = dao.indexGameCharacters();

		mv.addObject("gameCharacters", gameCharacters);
		
		Boolean successBool = dao.destroyStage(id);
		mv.addObject("successBool", successBool);

		Quest quest = dao.showQuest(questId);

		mv.addObject("quest", quest);

		mv.setViewName("WEB-INF/views/admin/adminQuest.jsp");
		return mv;
	}

}
