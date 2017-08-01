package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	
	@RequestMapping(path = "CreateChar.do", method = RequestMethod.POST)
	  public String show(@RequestParam("character") GameCharacter newChar, Model model) {
		charDao.create(newChar);
	    model.addAttribute("char", newChar);
	    return "WEB-INF/views/player/createCharacter.jsp";
	  }
}
