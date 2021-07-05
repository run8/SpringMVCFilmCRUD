package com.skilldistillery.film.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Inventory;

@Repository
public class DatabaseAccessorObject implements DatabaseAccessor {

	private static String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private String user = "student";
	private String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		String query = "SELECT * FROM film WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				String releaseDate = rs.getString("release_year");
				film.setReleaseYear(Integer.valueOf(releaseDate.substring(0, 4)));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				rs.close();
				film.setAllActorsInFilm(findActorsByFilmId(filmId));
				film.setAllCategories(findCategoriesByFilmId(filmId));
				return film;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Actor findActorById(int actorId) {
		String query = "SELECT * FROM actor WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				rs.close();
				return actor;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		String query = "SELECT actor.id, actor.first_name, actor.last_name FROM actor"
				+ " JOIN film_actor ON  actor.id = film_actor.actor_id"
				+ " JOIN film ON film_actor.film_id = film.id WHERE film_id = ?" + " ORDER BY actor.id";

		List<Actor> actors = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actors.add(actor);
			}
			rs.close();
			return actors;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String findLanguageNameByLanguageId(int langId) {
		String query = "SELECT name FROM language WHERE id = ?";
		String result = null;

		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, langId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("name");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Film> findFilmsByKeywordInTitleOrDesc(String keyword) {
		List<Film> films = new ArrayList<>();
		String query = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ? ORDER BY id";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, ("%" + keyword + "%"));
			stmt.setString(2, ("%" + keyword + "%"));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				String releaseDate = rs.getString("release_year");
				film.setReleaseYear(Integer.valueOf(releaseDate.substring(0, 4)));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setAllActorsInFilm(findActorsByFilmId(film.getId()));
				films.add(film);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<String> findCategoriesByFilmId(int filmId) {
		List<String> categories = new ArrayList<>();
		String query = "SELECT category.name FROM category JOIN film_category ON category.id = film_category.category_id JOIN film ON film_category.film_id = film.id WHERE film.id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				categories.add(rs.getString("name"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	@Override
	public int findCategoryIdByCategoryName(String category) {
		String query = "SELECT id FROM category WHERE name = ?";
		int result = 0;
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, category);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("id");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public void addCategoryToFilmCategorySQL(int filmId, int categoryId) {
		String query = "INSERT INTO film_category (film_id, category_id) VALUES (?,?)";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, filmId);
			stmt.setInt(2, categoryId);
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				conn.commit();
			}
			else {
				conn.rollback();
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		List<String> categories = new ArrayList<>();
		categories.addAll(findCategoriesByFilmId(filmId));
		return;
	}

	@Override
	public Inventory findInventoryByFilmId(int filmId) {
		String query = "SELECT DISTINCT inventory_item.media_condition, COUNT(IFNULL(inventory_item.media_condition,1)) FROM inventory_item JOIN film ON inventory_item.film_id = film.id WHERE film.id = ? GROUP BY inventory_item.media_condition";
		Map<String, Integer> inventory = new HashMap<>();
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				inventory.put(rs.getString("media_condition"), rs.getInt(2));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Inventory inventoryForFilm = new Inventory();
		inventoryForFilm.addAllToInventory(inventory);
		inventoryForFilm.setFilmId(filmId);
		return inventoryForFilm;
	}

	@Override
	public Film createFilm(Film filmToAdd, int categoryId) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO film (title, description, release_year, "
					+ "language_id, rental_duration, rental_rate, length, "
					+ "replacement_cost, rating, special_features) " + " VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, filmToAdd.getTitle());
			stmt.setString(2, filmToAdd.getDescription());
			stmt.setInt(3, filmToAdd.getReleaseYear());
			stmt.setInt(4, filmToAdd.getLanguageId());
			stmt.setInt(5, filmToAdd.getRentalDuration());
			stmt.setDouble(6, filmToAdd.getRentalRate());
			stmt.setInt(7, filmToAdd.getLength());
			stmt.setDouble(8, filmToAdd.getReplacementCost());
			stmt.setString(9, filmToAdd.getRating());
			stmt.setString(10, filmToAdd.getSpecialFeatures());

			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					filmToAdd.setId(newFilmId);
				}
			} else {
				filmToAdd = null;
			}
			conn.commit(); // COMMIT TRANSACTION
			addCategoryToFilmCategorySQL(filmToAdd.getId(), categoryId);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting film " + filmToAdd);
		}
		return filmToAdd;
	}

	@Override
	public boolean deleteFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql1 = "DELETE FROM film_category WHERE film_id = ?";
			PreparedStatement stmt1 = conn.prepareStatement(sql1);
			stmt1.setInt(1, film.getId());
			int updateCount = stmt1.executeUpdate();
			String sql2 = "DELETE FROM film WHERE id = ?";
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, film.getId());
			updateCount = stmt2.executeUpdate();
			
			if (updateCount == 1) {
				conn.commit(); // COMMIT TRANSACTION
			} else {
				throw new SQLException("Could not find film to delete");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;

	}

	@Override
	public boolean editFilm(Film filmToEdit) {
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection(URL, user, pass);
		    conn.setAutoCommit(false); // START TRANSACTION
		    
		    String sql = "UPDATE film SET title=?, description=?, release_year=?, language_id=?, "
		    		+ "rental_duration=?, rental_rate=?, length=?, replacement_cost=?,"
		    		+ " rating=?, special_features=? WHERE id=?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, filmToEdit.getTitle());
			stmt.setString(2, filmToEdit.getDescription());
			stmt.setInt(3, filmToEdit.getReleaseYear());
			stmt.setInt(4, filmToEdit.getLanguageId());
			stmt.setInt(5, filmToEdit.getRentalDuration());
			stmt.setDouble(6, filmToEdit.getRentalRate());
			stmt.setInt(7, filmToEdit.getLength());
			stmt.setDouble(8, filmToEdit.getReplacementCost());
			stmt.setString(9, filmToEdit.getRating());
			stmt.setString(10, filmToEdit.getSpecialFeatures());
			stmt.setInt(11, filmToEdit.getId()); 
		   
			int updateCount = stmt.executeUpdate();  // Returns 1 if successful
			// I don't believe we have to make this automic b/c ther's no sub-entities
		    
		    if (updateCount == 1) {
		      conn.commit();           // COMMIT TRANSACTION
		    }
		  } catch (SQLException sqle) {
		    sqle.printStackTrace();
		    if (conn != null) {
		      try { conn.rollback(); } // ROLLBACK TRANSACTION ON ERROR
		      catch (SQLException sqle2) {
		        System.err.println("Error trying to rollback");
		      }
		    }
		    return false;
		  }
		  return true;
	}
}
