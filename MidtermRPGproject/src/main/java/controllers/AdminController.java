package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import data.AdminDao;
import data.GameCharacter;

@Controller
public class AdminController {
	
	
	@Autowired
	private AdminDao dao;
	
	@RequestMapping(path = "GetGameCharacters.do", method = RequestMethod.GET)
	public String show(Model model) {

		List<GameCharacter> characters = dao.indexGameCharacters();

		model.addAttribute("characters", characters);

		return "WEB-INF/views/admin/adminCharacter.jsp";
	}

	

}
