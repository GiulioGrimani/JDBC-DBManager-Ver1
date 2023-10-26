import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main2 {

	public static void main(String[] args) {

		Connection connection = null;
		String endpoint = "localhost";
		String port = "3306";
		String schema = "sakila";
		String user = "root";
		String password = "Password01!";
		try { // jdbc:mysql://localhost:3306/sakila
			connection = DriverManager.getConnection("jdbc:mysql://" + endpoint + ":" + port + "/" + schema, user,
					password);
		} catch (Exception e) {

		}

		String query = "Select * from actor;";
		List<Actor> actors = new ArrayList<>();

		try {

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				int actorId = rs.getInt("actor_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Timestamp lastUpdate = rs.getTimestamp("last_update");

				Actor a = new Actor();
				a.setId(actorId);
				a.setFirstName(firstName);
				a.setLastName(lastName);
				a.setLastUpdate(lastUpdate);

				actors.add(a);
			}

		} catch (Exception e) {

		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}

		for (Actor a : actors) {
			System.out.println(a + "\n");
		}

	}

}
