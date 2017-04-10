package it.polito.ai.es2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import it.polito.ai.es2.model.Linea;
import it.polito.ai.es2.model.LineeFermate;
import it.polito.ai.es2.model.Stop;

public class ReadJson {

	public LineeFermate read() {

		ObjectMapper mapper = new ObjectMapper();
		LineeFermate lineeFermate = new LineeFermate();
		List<Linea> listLine = new ArrayList<Linea>();
		List<Stop> listStop = new ArrayList<Stop>();

		try {
			JsonNode root = mapper.readTree(new File("./src/main/resources/linee.json"));
			JsonNode lines = root.get("lines");
			JsonNode stops = root.get("stops");

			for (JsonNode line : lines) {
			    Linea linea = mapper.treeToValue(line, Linea.class);
			    listLine.add(linea);
			}
			lineeFermate.setLinee(listLine);
			
			for (JsonNode s : stops) {
				Stop stop = mapper.treeToValue(s, Stop.class);
			    listStop.add(stop);
			}
			lineeFermate.setStops(listStop);
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lineeFermate;
	}
}
