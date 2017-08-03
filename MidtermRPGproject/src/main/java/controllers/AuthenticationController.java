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
import data.UserType;

@Controller
public class AuthenticationController {

	@Autowired
	private AuthenticationDao dao;

	// login method must add player to session so it can be used for admin check in
	// admin controller. thanks brian! -love Mo
	@RequestMapping(path = "CreatePlayerForm.do"/* , method=RequestMethod.P... ) */)
	public ModelAndView userRegister(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelAndView mv, HttpSession session) {
		Player playa = new Player();
		playa.setEmail(email);
		playa.setPassword(password);
		dao.register(playa);
		session.setAttribute("players", dao.indexPlayers());
		mv.setViewName("/WEB-INF/views/authentication/accountCreation.jsp");
		return mv;
	}

	@RequestMapping
	public ModelAndView userLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelAndView mv, HttpSession session) {
		// TODO: implement user login here

		return null;
	}

	@RequestMapping(path = "CreatePlayer.do")
	public ModelAndView gotNewlyCreatedPlayerAndAddedtoList(Player player, ModelAndView mv, HttpSession session) {
		mv.setViewName("/WEB-INF/views/authentication/accountCreation.jsp");
		mv.addObject("players", dao.indexPlayers());
		return mv;
	}
	
	@RequestMapping(path = "Login.do")
	public ModelAndView goToLoginAfterAccountCreation(Player player, ModelAndView mv, HttpSession session) {
		mv.setViewName("/WEB-INF/views/authentication/login.jsp");
		mv.addObject("players", dao.indexPlayers());
		//if (dao.isAdmin(player)) {
		//	mv.setViewName("/WEB-INF/views/admin/admin.jsp");
		//}
		//System.out.println(player.toString());  //sysout Test i made to print out created Player
		return mv;
	}
	
	@RequestMapping(path = "SubmitandGoToLogin.do")
	public ModelAndView goSubmitAndLogin(Player player, ModelAndView mv, HttpSession session) {
		userRegister(player.getEmail(), player.getPassword(), mv, session);
		mv.setViewName("/WEB-INF/views/authentication/login.jsp");
		mv.addObject("player", player);
		System.out.println(player.toString());
		return mv;
	}


}
