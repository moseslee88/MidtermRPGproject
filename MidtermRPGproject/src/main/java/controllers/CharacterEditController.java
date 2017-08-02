package controllers;

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
import org.springframework.web.servlet.ModelAndView;

import data.CharacterEditDao;

@Controller
public class CharacterEditController {
	
	@Autowired
	private CharacterEditDao dao;
	
	@RequestMapping(path = "ChangeName.do")
	public ModelAndView updateCharacterName (@Requestparam("charName")) {
		
	}
	
	
>>>>>>> f38479d59fc7ceba476de06f6c9d2ac73c50f38c

	
	@RequestMapping(path = "CreateChar.do", method = RequestMethod.POST)
	  public String show(@RequestParam("character") GameCharacter newChar, Model model) {
		charDao.create(newChar);
	    model.addAttribute("char", newChar);
	    return "WEB-INF/views/player/createCharacter.jsp";
	  }
}

