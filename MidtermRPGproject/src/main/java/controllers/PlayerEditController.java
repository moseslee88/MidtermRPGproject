package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.crypto.*;

import data.Player;
import data.PlayerEditDao;

@Controller
public class PlayerEditController {

	@Autowired
	private PlayerEditDao playerDao;

	//
	// @RequestMapping(path = "CreatePlayer.do", method = RequestMethod.POST)
	// public String show(@RequestParam("player")Player
	// newPlayer,@RequestParam("password") String pass1,@RequestParam("conPassword")
	// String pass2, Model model) {
	//
	// if (pass1.equals(pass2)) {
	// playerDao.create(newPlayer);
	// model.addAttribute("player", newPlayer);
	// return "WEB-INF/views/authentication/login.jsp";
	// }
	// else {
	// return "WEB-INF/views/authentication/accountCreation.jsp";
	// }
	//
	// }

}
