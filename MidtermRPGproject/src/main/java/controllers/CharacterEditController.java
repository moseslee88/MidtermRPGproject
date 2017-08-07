package controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import data.CharacterEditDao;
import data.GameCharacter;
import data.Inventory;
import data.Player;

@Controller
public class CharacterEditController {
	@Autowired
	private CharacterEditDao dao;
	
	// takes in a Player command object and updates the character's name
		@RequestMapping(path = "CharacterRoute.do", method=RequestMethod.GET)
	public ModelAndView showCharacters(ModelAndView mv, HttpSession session) {
		Player p = (Player) session.getAttribute("player");
		session.setAttribute("characters", dao.getPlayersGameCharacters(p));
		mv.setViewName("/WEB-INF/views/character/characterInfo.jsp");
		return mv;
	}
	@RequestMapping(path = "PlayerChangeName.do")
	public ModelAndView updateCharacterName(@RequestParam("newCharName") String name,
			@RequestParam("oldCharName") String oldName, Player player, ModelAndView mv, HttpSession session) {
		GameCharacter updatedChar = new GameCharacter();
		updatedChar.setName(name);
		dao.update(updatedChar, dao.getCharByName(oldName).getId());
		session.setAttribute("characters", dao.getPlayersGameCharacters(player));
		mv.setViewName("/WEB-INF/views/character/characterInfo.jsp");
		return mv;
	}

	// takes in a charId as an integer
	@RequestMapping(path = "PlayerKillCharacter.do")
	public ModelAndView killCharacter(@RequestParam("charName") String charName, HttpSession session, ModelAndView mv) {
		dao.killChar(dao.getCharIdByName(charName), (Player) session.getAttribute("player")); // takes in int charId,
																								// Player p
		Player p = null;
		if (session.getAttribute("player") != null) {
			p = (Player) session.getAttribute("player");
		}
		session.setAttribute("characters", dao.getPlayersGameCharacters(p));
		mv.setViewName("/WEB-INF/views/character/characterinfo.jsp");
		return mv;
	}

	@RequestMapping(path = "PlayerCreateForm.do", method = RequestMethod.GET)
	 public ModelAndView getPlayerToCreateCharacterForm(ModelAndView mv) {
		mv.setViewName("/WEB-INF/views/player/createCharacter.jsp");
		return mv;
	}

	@RequestMapping(path = "PlayerCreatesChar.do" /* method = RequestMethod.POS */ )
	public ModelAndView createGameCharacter(GameCharacter newChar, HttpSession session, RedirectAttributes redir,
			ModelAndView mv) {
	//	System.out.println("New Game Character: " + newChar.getName()); // a test I made AARON style to see if New Game
																		// Character object is actually being created

		newChar.setPlayer((Player) session.getAttribute("player"));
		Player p = (Player) session.getAttribute("player");
        newChar.setLevel(1);
        Inventory i = new Inventory();
        newChar.setInventory(i);
		dao.create(newChar);
		session.setAttribute("newcharacter", newChar);
		session.setAttribute("characters", dao.getPlayersGameCharacters(p));
		mv.setViewName("redirect:NewGameCharacterAdded.do");
		return mv;
	}


	// here is the mapping to handle POST-redirect Get
	@RequestMapping(path = "NewGameCharacterAdded.do", method = RequestMethod.GET)
	public ModelAndView show(GameCharacter newChar, Model model, ModelAndView mv, HttpSession session) {
		mv.setViewName("WEB-INF/views/character/characterInfo.jsp");
		mv.addObject("NewCharacter", newChar);
		mv.addObject("characters", dao.getPlayersGameCharacters((Player)session.getAttribute("player")));
		//mv.addObject("NewCharacter", dao.create(newChar));
		// model.addAttribute("char", newChar);
		return mv;
	}

}
