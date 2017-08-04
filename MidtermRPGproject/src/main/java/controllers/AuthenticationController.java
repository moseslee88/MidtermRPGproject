package controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView userRegister(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("display_name") String displayname, @RequestParam("user_type") int usertype,
			ModelAndView mv, Player playa, HttpSession session) throws NoSuchAlgorithmException {
		playa.setEmail(email);
		playa.setPassword(password);
		playa.setUserType(2);  //hardcoded to player
		playa.setDisplayName("user");
		dao.register(playa);  //here Player playa gets persisted 
		session.setAttribute("players", dao.indexPlayers());
		session.setAttribute("player", playa);
		mv.setViewName("/WEB-INF/views/authentication/accountCreation.jsp");
		return mv;
	}

	@RequestMapping(path = "AuthenticationRoute.do")
	public ModelAndView userLogin(@RequestParam("email") String email, @RequestParam("password") String password, Player p,
			ModelAndView mv, HttpSession session) {
		// TODO: implement user login here
		mv.setViewName("/WEB-INF/views/player/playerInfo.jsp");
		System.out.println(dao.findUserPasswordByEmail(email) + " from database");
	    System.out.println(password + " from user");
	    dao.login(p);
	    boolean passWordMatches  = edao.matches(password, dao.findUserPasswordByEmail(email));
	        //print out true or false for password matching
	        System.out.println(passWordMatches);         
	       // User u = ud.validate(email, password);
	        if (p != null && passWordMatches) {
                session.setAttribute("player", p);
                session.setAttribute("players", dao.indexPlayers());
	            return mv;

	        } else {
	        	mv.setViewName("/WEB-INF/views/player/authentication/login.jsp");
	            return mv;
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
		//mv.addObject("players", dao.indexPlayers());
		//if (dao.isAdmin(player)) {
		//	mv.setViewName("/WEB-INF/views/admin/admin.jsp");
		//}
		return mv;
	}
	
	@RequestMapping(path = "SubmitandGoToLogin.do")
	public ModelAndView goSubmitAndLogin(Player player, ModelAndView mv, HttpSession session) throws NoSuchAlgorithmException {
		userRegister(player.getEmail(), player.getPassword(), 2, mv, player, session);
		mv.setViewName("/WEB-INF/views/authentication/login.jsp");
		mv.addObject("player", player);
		System.out.println(player.toString());
		return mv;
	}
	


}
