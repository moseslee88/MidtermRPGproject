package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.*;

@Controller
public class CharacterEditController {
	@Autowired
	private CharacterEditDao dao;


	//takes in a Player command object and updates the character's name
	@RequestMapping(path = "ChangeName.do")
	public ModelAndView updateCharacterName (@RequestParam("newCharName") String name, @RequestParam("oldCharName") String oldName, Player player, ModelAndView mv, HttpSession session) {
		GameCharacter updatedChar = new GameCharacter();
		updatedChar.setName(name);
		dao.update(updatedChar, dao.getCharByName(oldName).getId());
		session.setAttribute("characters", dao.getPlayersGameCharacters(player));
		mv.setViewName("/WEB-INF/views/character/characterinfo.jsp");
		return mv;
	}
	
	//takes in a charId as an integer 
	@RequestMapping(path="KillCharacter.do")
    public ModelAndView killCharacter (@RequestParam("charName") String charName, @RequestParam() HttpSession session, ModelAndView mv)	 {
		dao.killChar(dao.getCharIdByName(charName), (Player)session.getAttribute("player"));  //takes in int charId, Player p
		Player p = null;
		if (session.getAttribute("player") != null) {
			p = (Player)session.getAttribute("player");
		}
		session.setAttribute("characters", dao.getPlayersGameCharacters(p));
		mv.setViewName("/WEB-INF/views/character/characterinfo.jsp");
		return mv;
	}
	

//	@RequestMapping(path = "CreateChar.do", method = RequestMethod.POST)
//	  public String show(@RequestParam("character") GameCharacter newChar, Model model) {
//		dao.create(newChar);
//	    model.addAttribute("char", newChar);
//	    return "WEB-INF/views/player/createCharacter.jsp";
//	  }
}

