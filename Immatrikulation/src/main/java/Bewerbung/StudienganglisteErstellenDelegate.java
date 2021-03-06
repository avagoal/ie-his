package Bewerbung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

public class StudienganglisteErstellenDelegate implements JavaDelegate {

	public StudienganglisteErstellenDelegate() {
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Map<String, String> studiengangliste = new HashMap<String, String>();

		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/his?user=root&password=root&useSSL=false&useSSL=false");

		String sql = "Select studiengangbezeichnung from his.Studiengang ";

		Statement stmt = connection.createStatement();

		ResultSet res = stmt.executeQuery(sql);

//		int size = 0;
//		if (res != null) {
//			res.last();
//			size = res.getRow();
//		}
//		res.first();
		String studiengang ;//=(String) res.getObject(1);
//		studiengangliste.put(studiengang, studiengang);
		
		
		
		while (res.next()) {
			studiengang = (String) res.getObject(1);
			studiengangliste.put(studiengang, studiengang);
		}

		execution.setVariable("ALLE_STUDIENGAENGE", Variables.objectValue(studiengangliste)
				.serializationDataFormat(SerializationDataFormats.JSON).create());
		;

	}

}
