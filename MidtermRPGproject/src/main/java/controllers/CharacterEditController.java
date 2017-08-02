package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	

}

