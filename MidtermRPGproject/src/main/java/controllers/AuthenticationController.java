package controllers;

import java.security.NoSuchAlgorithmException;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.AuthenticationDao;
import data.EncryptionDAO;
import data.GameCharacter;
import data.Player;

import enums.TypeOfUser;

@Controller
public class AuthenticationController {

	@Autowired
	private AuthenticationDao dao;
	@Autowired
	private EncryptionDAO edao;

	// login method must add player to session so it can be used for admin check in
	// admin controller. thanks brian! -love Mo
	@RequestMapping(path = "CreatePlayerForm.do"/* , method=RequestMethod.P... ) */)
	public ModelAndView userRegister(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("displayName") String displayName, @RequestParam("user_type") int usertype, ModelAndView mv,
			Player playa, HttpSession session) throws NoSuchAlgorithmException {
		playa.setEmail(email);
		playa.setPassword(password);
		playa.setUserType(2); // hardcoded to player
		playa.setDisplayName(displayName);
		dao.register(playa); // here Player playa gets persisted
		session.setAttribute("players", dao.indexPlayers());
		session.setAttribute("player", playa);
		mv.setViewName("/WEB-INF/views/authentication/accountCreation.jsp");
		return mv;
	}

	@RequestMapping(path = "AuthenticationRoute.do", method=RequestMethod.POST)
	public String userLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelAndView mv, HttpSession session) {
		// TODO: implement user login here
		// mv.setViewName("/WEB-INF/views/player/playerInfo.jsp");
		// System.out.println(dao.findUserPasswordByEmail(email) + " from database");
		// System.out.println(password + " from user");
		// boolean passWordMatches = edao.matches(password,
		// dao.findUserPasswordByEmail(email));
		// print out true or false for password matching
		// System.out.println(passWordMatches);

		// try{
		// if (email != null && password!= null && passWordMatches) {
		// session.setAttribute("player",dao.login(email, password));
		// mv.addObject("player", dao.login(email, password));
		// session.setAttribute("players", dao.indexPlayers());
		// mv.setViewName("/WEB-INF/views/player/playerInfo.jsp");
		// return mv;
        
		Boolean validE= dao.validEmail(email);
		if(dao.isAdmin(dao.login(email, password))) {
			return "/WEB-INF/views/admin/admin.jsp";
		}
		if (email != "" || password != "") {
			// boolean passWordMatches = edao.matches(password,
			// dao.findUserPasswordByEmail(email));
			// print out true or false for password matching
			// System.out.println(passWordMatches);
			session.setAttribute("player", dao.login(email, password));
			mv.addObject("player", dao.login(email, password));
			session.setAttribute("players", dao.indexPlayers());
			mv.setViewName("/WEB-INF/views/player/playerInfo.jsp");
			return "/WEB-INF/views/player/playerInfo.jsp";
		} 
		if(email=="" || password=="" || !validE) {
			return "/WEB-INF/views/authentication/_500.jsp";
			//return "/WEB-INF/views/player/authentication/login.jsp";
		}
		else {
			
			return "/WEB-INF/views/authentication/_404.jsp";
		}
	}

	@RequestMapping(path = "CreatePlayer.do")
	public ModelAndView gotNewlyCreatedPlayerAndAddedtoList(Player player, ModelAndView mv, HttpSession session) {
		mv.setViewName("/WEB-INF/views/authentication/accountCreation.jsp");
		mv.addObject("players", dao.indexPlayers());
		return mv;
	}

	@RequestMapping(path = "Login.do")
	public ModelAndView goToLoginAfterAccountCreation(ModelAndView mv, HttpSession session) {
		mv.setViewName("/WEB-INF/views/authentication/login.jsp");
		// mv.addObject("players", dao.indexPlayers());
		// if (dao.isAdmin(player)) {
		// mv.setViewName("/WEB-INF/views/admin/admin.jsp");
		// }
		return mv;
	}

	@RequestMapping(path = "SubmitandGoToLogin.do")
	public ModelAndView goSubmitAndLogin(Player player, ModelAndView mv, HttpSession session)
			throws NoSuchAlgorithmException {
		userRegister(player.getEmail(), player.getPassword(), player.getDisplayName(), 2, mv, player, session);
		// userRegister(player.getEmail(), player.getPassword(), 2, mv, player,
		// session);
		mv.setViewName("/WEB-INF/views/authentication/login.jsp");
		// mv.addObject("player", player);
		System.out.println(player.toString());
		return mv;
	}

}
