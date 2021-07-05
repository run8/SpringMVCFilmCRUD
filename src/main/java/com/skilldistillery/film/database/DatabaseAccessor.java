package com.skilldistillery.film.database;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Inventory;

public interface DatabaseAccessor {
	public Film findFilmById(int filmId);

	public Actor findActorById(int actorId);

	public List<Actor> findActorsByFilmId(int filmId);

	public String findLanguageNameByLanguageId(int langId);

	public List<Film> findFilmsByKeywordInTitleOrDesc(String keyword);
	
	public List<String> findCategoriesByFilmId(int filmId);
	
	public Inventory findInventoryByFilmId(int filmId);
	
	public Film createFilm(Film film);
	
	public boolean deleteFilm(Film film);
	
	public boolean editFilm(Film film);
}
