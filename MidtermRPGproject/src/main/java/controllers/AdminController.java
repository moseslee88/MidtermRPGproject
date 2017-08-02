package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.AdminDao;
import data.GameCharacter;
import data.Item;
import data.Player;
import data.Quest;

@Controller
public class AdminController {

	@Autowired
	private AdminDao dao;

	@RequestMapping(path = "GetGameCharacters.do" /* , method = RequestMethod.GET */)
	public String showGameCharacters(Model model) {

		List<GameCharacter> characters = dao.indexGameCharacters();

		model.addAttribute("characters", characters);

		return "WEB-INF/views/admin/adminGameCharacter.jsp";
	}

	@RequestMapping(path = "GetGameCharacter.do" /* , method = RequestMethod.GET */)
	public String showGameCharacter(@RequestParam("id") Integer id, Model model) {

		GameCharacter gameCharacter = dao.showGameCharacter(id);

		model.addAttribute("gameCharacter", gameCharacter);

		return "WEB-INF/views/admin/adminGameCharacter.jsp";
	}

	@RequestMapping(path = "NewGameCharacter.do" /* , method = RequestMethod.POST */)
	public String newGameCharacter(Model model, GameCharacter gameCharacter) {

		dao.createGameCharacter(gameCharacter);
		model.addAttribute("gameCharacter", gameCharacter);

		return "WEB-INF/views/admin/adminGameCharacter.jsp";
	}

	@RequestMapping(path = "EditGameCharacter.do" /* , method = RequestMethod.POST */)
	public String editGameCharacter(Model model, Integer id, GameCharacter gameCharacter) {

		GameCharacter gameCharacter2 = dao.updateGameCharacter(id, gameCharacter);
		model.addAttribute("gameCharacter", gameCharacter2);

		return "WEB-INF/views/admin/adminGameCharacter.jsp";
	}

	@RequestMapping(path = "DeleteGameCharacter.do" /* , method = RequestMethod.GET */)
	public String deleteGameCharacter(Model model, Integer id) {

		Boolean successBool = dao.destroyGameCharacter(id);
		model.addAttribute("successBool", successBool);

		return "WEB-INF/views/admin/adminGameCharacter.jsp";
	}

	@RequestMapping(path = "GetItems.do" /* , method = RequestMethod.GET */)
	public String showItems(Model model) {

		List<Item> characters = dao.indexItems();

		model.addAttribute("characters", characters);

		return "WEB-INF/views/admin/adminItem.jsp";
	}

	@RequestMapping(path = "GetItem.do" /* , method = RequestMethod.GET */)
	public String showItem(@RequestParam("id") Integer id, Model model) {

		Item item = dao.showItem(id);

		model.addAttribute("item", item);

		return "WEB-INF/views/admin/adminItem.jsp";
	}

	@RequestMapping(path = "NewItem.do" /* , method = RequestMethod.POST */)
	public String newItem(Model model, Item item) {

		dao.createItem(item);
		model.addAttribute("item", item);

		return "WEB-INF/views/admin/adminItem.jsp";
	}

	@RequestMapping(path = "EditItem.do" /* , method = RequestMethod.POST */)
	public String editItem(Model model, Integer id, Item item) {

		Item item2 = dao.updateItem(id, item);
		model.addAttribute("item", item2);

		return "WEB-INF/views/admin/adminItem.jsp";
	}

	@RequestMapping(path = "DeleteItem.do" /* , method = RequestMethod.GET */)
	public String deleteItem(Model model, Integer id) {

		Boolean successBool = dao.destroyItem(id);
		model.addAttribute("successBool", successBool);

		return "WEB-INF/views/admin/adminItem.jsp";
	}

	@RequestMapping(path = "GetPlayers.do" /* , method = RequestMethod.GET */)
	public String showPlayers(Model model) {

		List<Player> characters = dao.indexPlayers();

		model.addAttribute("characters", characters);

		return "WEB-INF/views/admin/adminPlayer.jsp";
	}

	@RequestMapping(path = "GetPlayer.do" /* , method = RequestMethod.GET */)
	public String showPlayer(@RequestParam("id") Integer id, Model model) {

		Player player = dao.showPlayer(id);

		model.addAttribute("player", player);

		return "WEB-INF/views/admin/adminPlayer.jsp";
	}

	@RequestMapping(path = "NewPlayer.do" /* , method = RequestMethod.POST */)
	public String newPlayer(Model model, Player player) {

		dao.createPlayer(player);
		model.addAttribute("player", player);

		return "WEB-INF/views/admin/adminPlayer.jsp";
	}

	@RequestMapping(path = "EditPlayer.do" /* , method = RequestMethod.POST */)
	public String editPlayer(Model model, Integer id, Player player) {

		Player player2 = dao.updatePlayer(id, player);
		model.addAttribute("player", player2);

		return "WEB-INF/views/admin/adminPlayer.jsp";
	}

	@RequestMapping(path = "DeletePlayer.do" /* , method = RequestMethod.GET */)
	public String deletePlayer(Model model, Integer id) {

		Boolean successBool = dao.destroyPlayer(id);
		model.addAttribute("successBool", successBool);

		return "WEB-INF/views/admin/adminPlayer.jsp";
	}

	@RequestMapping(path = "GetQuests.do" /* , method = RequestMethod.GET */)
	public String showQuests(Model model) {

		List<Quest> characters = dao.indexQuests();

		model.addAttribute("characters", characters);

		return "WEB-INF/views/admin/adminQuest.jsp";
	}

	@RequestMapping(path = "GetQuest.do" /* , method = RequestMethod.GET */)
	public String showQuest(@RequestParam("id") Integer id, Model model) {

		Quest quest = dao.showQuest(id);

		model.addAttribute("quest", quest);

		return "WEB-INF/views/admin/adminQuest.jsp";
	}

	@RequestMapping(path = "NewQuest.do" /* , method = RequestMethod.POST */)
	public String newQuest(Model model, Quest quest) {

		dao.createQuest(quest);
		model.addAttribute("quest", quest);

		return "WEB-INF/views/admin/adminQuest.jsp";
	}

	@RequestMapping(path = "EditQuest.do" /* , method = RequestMethod.POST */)
	public String editQuest(Model model, Integer id, Quest quest) {

		Quest quest2 = dao.updateQuest(id, quest);
		model.addAttribute("quest", quest2);

		return "WEB-INF/views/admin/adminQuest.jsp";
	}

	@RequestMapping(path = "DeleteQuest.do" /* , method = RequestMethod.GET */)
	public String deleteQuest(Model model, Integer id) {

		Boolean successBool = dao.destroyQuest(id);
		model.addAttribute("successBool", successBool);

		return "WEB-INF/views/admin/adminQuest.jsp";
	}

}
