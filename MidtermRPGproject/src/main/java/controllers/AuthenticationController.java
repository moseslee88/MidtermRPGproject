package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import data.AuthenticationDao;

@Controller
public class AuthenticationController {
	
	
	@Autowired
	private AuthenticationDao dao;
	
	//login method must add player to session so it can be used for admin check in admin controller. thanks brian! -love Mo

}
