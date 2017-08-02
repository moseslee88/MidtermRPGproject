package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import data.CharacterEditDao;
import data.*;

@Controller
public class CharacterEditController {
	@Autowired
	private CharacterEditDao charDao;
=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.CharacterEditDao;
import data.GameCharacter;
import data.Player;

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
    public ModelAndView killCharacter (@RequestParam("charName") String charName, HttpSession session, ModelAndView mv)	 {
		dao.killChar(dao.getCharIdByName(charName));
		Player p = null;
		if (session.getAttribute("player") != null) {
			p = (Player)session.getAttribute("player");
		}
		session.setAttribute("characters", dao.getPlayersGameCharacters(p));
		
		return mv;
	}
	
	
<<<<<<< HEAD
>>>>>>> f38479d59fc7ceba476de06f6c9d2ac73c50f38c
=======
	
>>>>>>> 3e732a2a4640a27cc44ca5b03e63328ffd78ba80

	
	@RequestMapping(path = "CreateChar.do", method = RequestMethod.POST)
	  public String show(@RequestParam("character") GameCharacter newChar, Model model) {
		charDao.create(newChar);
	    model.addAttribute("char", newChar);
	    return "WEB-INF/views/player/createCharacter.jsp";
	  }
}

