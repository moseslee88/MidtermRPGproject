package controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import data.CharacterEditDao;
import data.GameCharacter;
import data.Player;

@Controller
public class CharacterEditController {
	@Autowired
	private CharacterEditDao dao;


	//takes in a Player command object and updates the character's name
	@RequestMapping(path = "PlayerChangeName.do")
	public ModelAndView updateCharacterName (@RequestParam("newCharName") String name, @RequestParam("oldCharName") String oldName, Player player, ModelAndView mv, HttpSession session) {
		GameCharacter updatedChar = new GameCharacter();
		updatedChar.setName(name);
		dao.update(updatedChar, dao.getCharByName(oldName).getId());
		session.setAttribute("characters", dao.getPlayersGameCharacters(player));
		mv.setViewName("/WEB-INF/views/character/characterinfo.jsp");
		return mv;
	}
	
	//takes in a charId as an integer 
	@RequestMapping(path="PlayerKillCharacter.do")
    public ModelAndView killCharacter (@RequestParam("charName") String charName, HttpSession session, ModelAndView mv)	 {
		dao.killChar(dao.getCharIdByName(charName), (Player)session.getAttribute("player"));  //takes in int charId, Player p
		Player p = null;
		if (session.getAttribute("player") != null) {
			p = (Player)session.getAttribute("player");
		}
		session.setAttribute("characters", dao.getPlayersGameCharacters(p));
		mv.setViewName("/WEB-INF/views/character/characterinfo.jsp");
		return mv;
	}
	
	@RequestMapping(path="PlayerCreatesChar.do" /* method = RequestMethod.POS  */ )
	public ModelAndView createGameCharacter(GameCharacter newChar, HttpSession session, RedirectAttributes redir, ModelAndView mv)  {
		System.out.println("New Game Character: " + newChar.getName());   //a test I made AARON style to see if New Game Character object is actually being created
		Player p = (Player) session.getAttribute("player");
		
		newChar.setPlayer(p);		
		dao.create(newChar);
		session.setAttribute("newcharacter", newChar);
		session.setAttribute("characters", dao.getPlayersGameCharacters(p));
		mv.setViewName("redirect:NewGameCharacterAdded.do");
		return mv;
	}
	
	

//	@RequestMapping(path = "CreateChar.do", method = RequestMethod.POST)
//	  public String show(@RequestParam("character") GameCharacter newChar, Model model) {
//		dao.create(newChar);
//	    model.addAttribute("char", newChar);
//	    return "WEB-INF/views/player/createCharacter.jsp";
//	  }
}

