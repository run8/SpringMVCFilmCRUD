package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.database.DatabaseAccessor;
import com.skilldistillery.film.entities.Film;

@Controller

public class FilmController {
	@Autowired
	private DatabaseAccessor dao;

	@RequestMapping(path= {"/", "home.do"})
	public String index() {
		return "WEB-INF/home.jsp";
	}
	
	@RequestMapping(path = "searchByIdInput.do", params = "filmId", method = RequestMethod.GET)
	public ModelAndView searchById(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film filmResult = dao.findFilmById(filmId);
		mv.addObject("film", filmResult);
		mv.setViewName("WEB-INF/home.jsp");
		return mv;
	}
	
	@RequestMapping(path = "searchByKeywordInput.do")
	public ModelAndView searchByKeyword() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/home.jsp");
		return mv;
	}
	
}
