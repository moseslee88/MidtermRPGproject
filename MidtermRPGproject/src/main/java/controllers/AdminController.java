package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.crypto.*;

import data.AdminDao;

@Controller
public class AdminController {
	
	
	@Autowired
	AdminDao dao;


}
