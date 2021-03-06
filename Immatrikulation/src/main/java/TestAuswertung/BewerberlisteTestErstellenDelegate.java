package TestAuswertung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

public class BewerberlisteTestErstellenDelegate implements JavaDelegate {
	
	/*** Alle Bewerber mit Status Test, Nc mit Test auslesen
	 und Name, Vorname, Email,Id als Prozessvariabel abspeichern ***/
	
	public BewerberlisteTestErstellenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Connection zu DB aufbauen; DB-Name: his; user: root; password: root
		Connection connection;

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false");

		Statement stmt = connection.createStatement();

		// hier werden alle Daten aus der Datenbank ausgelesen, die für den
		// Prozess benötigt werden
		String sqlBewerberID = "SELECT BewerberID FROM his.bewerber" + " JOIN his.studiengang ON "
				+ " his.bewerber.StudiengangID = his.studiengang.StudiengangID"
				+ " join his.studiengangszulassung on his.studiengang.ZulassungsID = his.studiengangszulassung.ZulassungsID"
				+ " where his.studiengang.ZulassungsID = 2  or his.studiengang.ZulassungsID = 4"
				+ " and his.bewerber.StatusID = 2  or his.bewerber.StatusID = 4";


		// Ausführen des Selects auf der DB
		ResultSet res = stmt.executeQuery(sqlBewerberID);

		// Wert aus erste Spalte über Methode getDate als Datum auslesen

		ArrayList<String> BewerberIDs = new ArrayList<String>();

		while (res.next()) {
			BewerberIDs.add(res.getString(1));
		}
		String sqlBewerberVorname = "SELECT  BewerberVorname FROM his.bewerber" + " JOIN his.studiengang ON "
				+ " his.bewerber.StudiengangID = his.studiengang.StudiengangID"
				+ " join his.studiengangszulassung on his.studiengang.ZulassungsID = his.studiengangszulassung.ZulassungsID"
				+ " where his.studiengang.ZulassungsID = 2  or his.studiengang.ZulassungsID = 4"
				+ " and his.bewerber.StatusID = 2  or his.bewerber.StatusID = 4";

		
		ArrayList<String> BewerberVorname = new ArrayList<String>();
		res = stmt.executeQuery(sqlBewerberVorname);
		while (res.next()) {
			BewerberVorname.add(res.getString(1));
		}
		String sqlBewerberNachname = "SELECT  BewerberNachname FROM his.bewerber" + " JOIN his.studiengang ON "
				+ " his.bewerber.StudiengangID = his.studiengang.StudiengangID"
				+ " join his.studiengangszulassung on his.studiengang.ZulassungsID = his.studiengangszulassung.ZulassungsID"
				+ " where his.studiengang.ZulassungsID = 2  or his.studiengang.ZulassungsID = 4"
		+ " and his.bewerber.StatusID = 2  or his.bewerber.StatusID = 4";

		ArrayList<String> BewerberNachname = new ArrayList<String>();
		res = stmt.executeQuery(sqlBewerberNachname);
		while (res.next()) {
			BewerberNachname.add(res.getString(1));
		}

		String sqlBewerberEmail = "SELECT  BewerberEmail FROM his.bewerber" + " JOIN his.studiengang ON "
				+ " his.bewerber.StudiengangID = his.studiengang.StudiengangID"
				+ " join his.studiengangszulassung on his.studiengang.ZulassungsID = his.studiengangszulassung.ZulassungsID"
				+ " where his.studiengang.ZulassungsID = 2  or his.studiengang.ZulassungsID = 4"
				+ " and his.bewerber.StatusID = 2  or his.bewerber.StatusID = 4";

		ArrayList<String> BewerberEmail = new ArrayList<String>();
		res = stmt.executeQuery(sqlBewerberEmail);
		while (res.next()) {
			BewerberEmail.add("camundaproject12341234@gmail.com");
		}

		execution.setVariable("BewerberIDs",
				Variables.objectValue(BewerberIDs).serializationDataFormat(SerializationDataFormats.JSON).create());
		execution.setVariable("BewerberVorname",
				Variables.objectValue(BewerberVorname).serializationDataFormat(SerializationDataFormats.JSON).create());
		execution.setVariable("BewerberNachname", Variables.objectValue(BewerberNachname)
				.serializationDataFormat(SerializationDataFormats.JSON).create());
		execution.setVariable("BewerberEmail", BewerberEmail);

	}

}
