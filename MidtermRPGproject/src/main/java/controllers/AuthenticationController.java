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
	@RequestMapping(path = "CreatePlayer.do"/* , method=RequestMethod.P... )  */)
	public ModelAndView userRegister (@RequestParam("email") String email, @RequestParam("password") String password, ModelAndView mv, HttpSession session) {
		Player playa = new Player();
		playa.setEmail(email);
		playa.setPassword(password);
		dao.register(playa);
		session.setAttribute("players", dao.indexPlayers());
		mv.setViewName("/WEB-INF/views/player/playerInfo.jsp");
		return mv;
	}
	
	public ModelAndView userLogin(@RequestParam("email") String email, @RequestParam("password") String password, ModelAndView mv, HttpSession session)  {
		//TODO: implement user login here
		
		return null;		
	}
}
