import java.sql.Timestamp;

/*
 * Mappiamo l'entità Actor del DB:
 * vediamo quali campi ha tale entità e riproduciamoli
 * in modo possibilmente fedele. Aggiungiamo getters, setters e toString.
 */

public class Actor {

	private int id;
	private String firstName;
	private String lastName;
	private Timestamp lastUpdate;

	public Actor() {
	}

	public Actor(int id, String firstName, String lastName, Timestamp lastUpdate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastUpdate = lastUpdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Actor id: " + id + "\nfirstName = " + firstName + "\nlastName = " + lastName + "\nlastUpdate = "
				+ lastUpdate + "\n\n";
	}

}
