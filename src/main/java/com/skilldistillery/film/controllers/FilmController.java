package com.skilldistillery.film.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

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

	@RequestMapping(path = "addFilm.do", params = { "title", "description", "releaseYear", "rentalRate", "languageID",
			"rentalDuration", "filmLength", "replacementCost", "rating",
			"specialFeature", "categoryId" }, method = RequestMethod.POST)
	public ModelAndView addFilm(String title, String description, int releaseYear, int languageID, int rentalDuration,
			double rentalRate, int filmLength, double replacementCost, String rating, String specialFeature, int categoryId, UriComponentsBuilder uriComponentsBuilder) {
		ModelAndView mv = new ModelAndView();
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
		film = dao.createFilm(film, categoryId);
		
		String result = "Unable to add film";
		if (film != null) {
			result = "Successfully added film with ID #: " + film.getId();
		}
		mv.addObject("result", result);
		mv.setViewName("WEB-INF/addEditDeleteResult.jsp");
		URI urlRedirect = uriComponentsBuilder
	            .replacePath(null)
	            .replaceQuery(null)
	            .pathSegment("MVCFilmSite")
	            .build().toUri();
		mv.addObject("urlRedirect", urlRedirect);
		String urlPathSegment = "/showDetail.do?filmId=" + film.getId();
		mv.addObject("urlPathSegment", urlPathSegment);
		return mv;
	}

	@RequestMapping(path = "removeFilm.do", params = "filmId", method = RequestMethod.POST)
	public ModelAndView removeFilm(int filmId, UriComponentsBuilder uriComponentsBuilder) {
		Film filmToDelete = dao.findFilmById(filmId);
		Boolean deleteResult = dao.deleteFilm(filmToDelete);
		String result = "Unable to delete film with ID #: " + filmId;
		String urlPathSegment = "/showDetail.do?filmId=" + filmId;
		if (deleteResult == true) {
			result = "Successfully deleted film with ID #: " + filmId;
			urlPathSegment = "";
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		mv.addObject("filmId", filmId);
		mv.setViewName("WEB-INF/addEditDeleteResult.jsp");
		URI urlRedirect = uriComponentsBuilder
	            .replacePath(null)
	            .replaceQuery(null)
	            .pathSegment("MVCFilmSite")
	            .build().toUri();
		mv.addObject("urlRedirect", urlRedirect);
		mv.addObject("urlPathSegment", urlPathSegment);
		return mv;
	}

	
	@RequestMapping(path = "modifyFilm.do", params = "filmId", method = RequestMethod.GET)
	public ModelAndView editFilmRedirect(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film film = dao.findFilmById(filmId);
		String language = dao.findLanguageNameByLanguageId(film.getLanguageId());
		String categoryName = dao.findCategoriesByFilmId(filmId).get(0);
		int categoryId = dao.findCategoryIdByCategoryName(categoryName);
		mv.setViewName("WEB-INF/editFilm.jsp");
		mv.addObject("film", film);
		mv.addObject("language", language);
		mv.addObject("categoryName", categoryName);
		mv.addObject("categoryId", categoryId);
		return mv;
	}
	
	
	@RequestMapping(path = "modifyFilm.do", params = { "filmId", "title", "description", "releaseYear", "rentalRate",
			"languageID", "rentalDuration", "filmLength", "replacementCost", "rating",
			"specialFeature" }, method = RequestMethod.POST)
	public ModelAndView editFilm(int filmId, String title, String description, int releaseYear, int languageID,
			int rentalDuration, double rentalRate, int filmLength, double replacementCost, String rating,
			String specialFeature, UriComponentsBuilder uriComponentsBuilder) {
		ModelAndView mv = new ModelAndView();
		Film film = dao.findFilmById(filmId);
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
		Boolean editResult = dao.editFilm(film);
		String result = "Unable to modify film with ID #: " + filmId;
		if (editResult == true) {
			result = "Successfully modified film with ID #: " + filmId; 
		}
		mv.addObject("filmId", filmId);
		mv.addObject("result", result);
		mv.setViewName("WEB-INF/addEditDeleteResult.jsp");
		URI urlRedirect = uriComponentsBuilder
				.replacePath(null)
				.replaceQuery(null)
				.pathSegment("MVCFilmSite")
				.build().toUri();
		mv.addObject("urlRedirect", urlRedirect);
		String urlPathSegment = "/showDetail.do?filmId=" + filmId;
		mv.addObject("urlPathSegment", urlPathSegment);
		return mv;
	}

}
