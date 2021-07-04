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

	@RequestMapping(path = { "/", "home.do" })
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

	@RequestMapping(path = "add.do")
	public String addNewFilmRedirect() {
		return "WEB-INF/addFilm.jsp";
	}

	@RequestMapping(path = "addFilm.do", params = {"title", "description", "releaseYear", "rentalRate", "languageID", "rentalDuration", "filmLength", "replacementCost", "rating", "specialFeature"}, method = RequestMethod.POST)
	public ModelAndView addFilm(String title, String description,  int releaseYear, int languageID, 
			int rentalDuration, double rentalRate, int filmLength, double replacementCost, String rating,  
			String specialFeature ) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/addFilm.jsp");
		Film film = new Film();
		film.setTitle(title);
		film.setDescription(description);
		film.setReleaseYear(releaseYear);
		film.setLanguageId(languageID);
		film.setRentalDuration(rentalDuration);
		film.setLength(filmLength);
		film.setRentalRate(rentalRate);
		film.setReplacementCost(replacementCost);
		film.setRating(rating);
		film.setSpecialFeatures(specialFeature);
		dao.createFilm(film);
		
		return mv;
	}

	
	@RequestMapping(path = "removeFilm.do", params = "filmId", method = RequestMethod.POST)
	public ModelAndView removeFilm(int filmId) {
		Film filmToDelete = dao.findFilmById(filmId);
		Boolean deleteResult = dao.deleteFilm(filmToDelete);
		String result = "Unable to delete film with ID #: " + filmId;
		if (deleteResult == true) {
			result = "Successfully deleted film with ID #: " + filmId;
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("deleteResult", result);
		mv.setViewName("WEB-INF/deleteResult.jsp");
		return mv;
	}
	
	

}
