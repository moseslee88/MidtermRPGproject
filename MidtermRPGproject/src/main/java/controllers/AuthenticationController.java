package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.AuthenticationDao;
import data.GameCharacter;
import data.Player;

@Controller
public class AuthenticationController {
	
	
	@Autowired
	private AuthenticationDao dao;
	
	//login method must add player to session so it can be used for admin check in admin controller. thanks brian! -love Mo
	@RequestMapping(path = "Login.do")
	public ModelAndView checkfor (@RequestParam("newCharName") String name, @RequestParam("oldCharName") String oldName, Player player, ModelAndView mv, HttpSession session) {
		GameCharacter updatedChar = new GameCharacter();
		updatedChar.setName(name);
		dao.update(updatedChar, dao.getCharByName(oldName).getId());
		session.setAttribute("characters", dao.getPlayersGameCharacters(player));
		mv.setViewName("/WEB-INF/views/character/characterinfo.jsp");
		return mv;
	}
	
	public 
}
