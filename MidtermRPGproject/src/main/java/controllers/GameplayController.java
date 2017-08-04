package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import data.AdminDao;
import data.GameCharacter;
import data.GameplayDao;
import data.PlayerEditDao;
import data.PlayerEditDaoImpl;

@Controller
public class GameplayController {
	@Autowired
	private GameplayDao dao;
	
	

	@RequestMapping(path = "GameplayRoute.do" /* , method = RequestMethod.GET */)
	public ModelAndView adminRoute(ModelAndView mv, HttpSession session) {
		PlayerEditDao ped = new PlayerEditDaoImpl();
		//temp fixes!

		mv.setViewName("WEB-INF/views/gameplay/battle.jsp");
		return mv;
	}

	@RequestMapping(path = "GameplayGetGameCharacters.do" /* , method = RequestMethod.GET */)
	public ModelAndView showGameCharacters(ModelAndView mv, HttpSession session) {

		List<GameCharacter> gameCharacters = dao.indexGameCharacters();

		mv.addObject("gameCharacters", gameCharacters);

		mv.setViewName("WEB-INF/views/admin/adminGameCharacter.jsp");
		return mv;
	}
}
