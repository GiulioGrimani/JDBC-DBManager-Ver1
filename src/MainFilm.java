import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/*
 * Primo approccio: facciamo tutto dentro il main.
 * 0. Aggiungiamo il connettore jdbc al classpath del progetto
 * 1. Mappiamo le entità del DB Film e Actor
 * 2. Creiamo una connessione
 * 3. Facciamo una query: prendiamo tutti i film e stampiamoli 
 */

public class MainFilm {

	public static void main(String[] args) {
		/*
		 * Creiamo una connessione sul DB: creiamo delle variabili che saranno dei
		 * parametri per impostare la connessione.
		 */
		Connection connection = null;
		String endpoint = "localhost";
		String port = "3306";
		String schema = "sakila";
		String user = "root";
		String password = "Password01!";
		try {									//    jdbc:mysql://localhost:3306/sakila
			connection = DriverManager.getConnection("jdbc:mysql://" + endpoint + ":" + port + "/" + schema, user,
					password);
			System.out.println("Connection opened!");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			System.out.println(e.getMessage());
		}

		/*
		 * Facciamo la query sul DB:
		 * 1. dopo aver salvato la query su una stringa per
		 * comodità e aver creato una lista di film dove salvare il risultato della
		 * query
		 * 2. creiamo uno Statement sulla connessione, ovvero un oggetto usato per
		 * interrogare il DB
		 * 3. creiamo un ResultSet, ovvero un oggetto dove memorizzare
		 * il risultato della query
		 * 4. cicliamo sul ResultSet per estrapolare le informazioni:
		 * ogni iterazione costituisce un record (una row) dell'output
		 * della nostra query. Salviamo un oggetto di tipo Film ad ogni
		 * iterazione e lo inseriamo nella lista di film creata in precedenza
		 * 5. chiudiamo la connessione
		 * 6. stampiamo la lista di film
		 */

		String query = "Select * from film;";
		List<Film> films = new ArrayList<>();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				int filmId = rs.getInt("film_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int releaseYear = rs.getInt("release_year");
				int languageId = rs.getInt("language_id");
				int originalLanguageId = rs.getInt("original_language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				Film.Rating rating = Film.Rating.getRatingByDescription(rs.getString("rating"));
				Set<String> specialFeatures = new HashSet<>(Arrays.asList(rs.getString("special_features").split(",")));
				Timestamp lastUpdate = rs.getTimestamp("last_update");
				
				Film film = new Film();
				film.setFilmId(filmId);
				film.setTitle(title);
				film.setDescription(description);
				film.setReleaseYear(releaseYear);
				film.setLanguageId(languageId);
				film.setOriginalLanguageId(originalLanguageId);
				film.setRentalRate(rentalRate);
				film.setRentalDuration(rentalDuration);
				film.setLength(length);
				film.setReplacementCost(replacementCost);
				film.setRating(rating);
				film.setSpecialFeatures(specialFeatures);
				film.setLastUpdate(lastUpdate);
				
				films.add(film);

			}
		} catch (SQLException e) {
			System.out.println("Query doesn't work!");
			System.out.println(e.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
					System.out.println("Connection closed!");
				} else {
					System.out.println("Connection was null!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		for (Film f : films) {
			System.out.println(f + "\n");
		}
	}

}
