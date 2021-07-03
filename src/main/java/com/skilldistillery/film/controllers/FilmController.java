package com.skilldistillery.film.controllers;

import java.util.ArrayList;
import java.util.List;

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
		List<Film> filmList = new ArrayList<>();
		ModelAndView mv = new ModelAndView();
		filmList.add(dao.findFilmById(filmId));
		mv.addObject("films", filmList);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}
	
	@RequestMapping(path = "searchByKeywordInput.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView searchByKeyword(String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> filmsByKeword = dao.findFilmsByKeywordInTitleOrDesc(keyword);
		mv.addObject("films", filmsByKeword);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}
	
	@RequestMapping(path = "showDetail.do", params = "filmId", method = RequestMethod.GET)
	public ModelAndView showDetails(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film filmWithDetails = dao.findFilmById(filmId);
		String language = dao.findLanguageNameByLanguageId(filmWithDetails.getLanguageId());
	
		
		mv.addObject("language", language);
		mv.addObject("film", filmWithDetails);
		mv.setViewName("WEB-INF/detailedResults.jsp");
		return mv;
	}
	
}
