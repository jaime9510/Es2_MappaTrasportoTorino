package it.polito.ai.es2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import it.polito.ai.es2.model.Linea;
import it.polito.ai.es2.model.LineeFermate;
import it.polito.ai.es2.model.Stop;

public class JDBC {

	public static void main(String args[]) throws SQLException {
		
		ReadJson readJson = new ReadJson();
		LineeFermate lineeFermate = readJson.read();
		
		Connection c = null;
		CallableStatement callableStatement = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/trasporti", "postgres", "ai-user-password");
			c.setAutoCommit(true);
			
			// Insert Bus Lines
			StringBuffer sql1 = new StringBuffer();
			sql1.append("INSERT INTO BusLine (line,description) VALUES (?,?)");
			
			callableStatement = c.prepareCall(sql1.toString());
			for(Linea linea : lineeFermate.getLinee()) {
				callableStatement.setString(1, linea.getLine());
				callableStatement.setString(2, linea.getDesc());
				Integer result = callableStatement.executeUpdate();
				System.out.println(linea.getLine() + " - Result = " + result);
			}
			
			// Insert Bus Stops
			StringBuffer sql2 = new StringBuffer();
			sql2.append("INSERT INTO BusStop (id,name,lat,lng) VALUES (?,?,?,?)");
			
			callableStatement = c.prepareCall(sql2.toString());
			for(Stop stop : lineeFermate.getStops()) {
				callableStatement.setString(1, stop.getId());
				callableStatement.setString(2, stop.getName());
				callableStatement.setDouble(3, stop.getLatLng().get(0));
				callableStatement.setDouble(4, stop.getLatLng().get(1));
				Integer result = callableStatement.executeUpdate();
				System.out.println(stop.getId() + " - Result = " + result);
			}
			
			// Insert Bus Line Stops
			StringBuffer sql3 = new StringBuffer();
			sql3.append("INSERT INTO BusLineStop (stopId,lineId,seqenceNumber) VALUES (?,?,?)");
			
			callableStatement = c.prepareCall(sql3.toString());
			
			// TODO: DELETE CODE
//			int i = 0;
//			for(Stop stop : lineeFermate.getStops()) {
//				for(String line : stop.getLines()) {
//					i = i + 1;
//					callableStatement.setString(1, stop.getId());
//					callableStatement.setString(2, line);
//					callableStatement.setDouble(3, i);
//					Integer result = callableStatement.executeUpdate();
//					System.out.println(line + " -  " + stop.getId() + " -  " + i);
//				}
//			}
			
			int i = 0;
			for(Linea line : lineeFermate.getLinee()) {
				i = 0;
				// Delete duplicates of the list
				List<String> stops = new ArrayList<String>(new LinkedHashSet<String>(line.getStops()));
				for(String stop : stops) {
					i = i + 1;
					callableStatement.setString(1, stop);
					callableStatement.setString(2, line.getLine());
					callableStatement.setDouble(3, i);
					Integer result = callableStatement.executeUpdate();
					System.out.println(line.getLine() + " -  " + stop + " -  " + i);
				}
			}
			
			
			// Code used to delete table information
			
//			StringBuffer sql3 = new StringBuffer();
//			sql3.append("DELETE FROM BusLineStop");
//			callableStatement = c.prepareCall(sql3.toString());
//			Integer result = callableStatement.executeUpdate();
//			System.out.println("DELETE - Result = " + result);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			if (callableStatement != null) {
				callableStatement.close();
			}

			if (c != null) {
				c.close();
			}
		}
	}
	
}