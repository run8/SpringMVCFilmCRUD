package com.skilldistillery.film.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class FilmController {


	@RequestMapping(path= {"/", "home.do"})
	public String index() {
		return "WEB-INF/home.jsp";
	}
	
	@RequestMapping(path = "id.do")
	public String searchById() {
		return "WEB-INF/home.jsp";
	}
	
	@RequestMapping(path = "keyword.do")
	public String searchByKeyword() {
		return "WEB-INF/home.jsp";
	}
	
}
