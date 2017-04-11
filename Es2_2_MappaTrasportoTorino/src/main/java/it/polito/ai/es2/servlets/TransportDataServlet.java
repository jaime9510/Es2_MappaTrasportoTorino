package it.polito.ai.es2.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import it.polito.ai.es2.dao.BusLineInterface;
import it.polito.ai.es2.dao.BusLineStopInterface;
import it.polito.ai.es2.dao.BusStopInterface;
import it.polito.ai.es2.dao.impl.BusLineImpl;
import it.polito.ai.es2.dao.impl.BusLineStopImpl;
import it.polito.ai.es2.dao.impl.BusStopImpl;

/**
 * Servlet implementation class TransportDataServlet
 */
public class TransportDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BusLineInterface busLine = new BusLineImpl();
	private BusStopInterface busStop = new BusStopImpl();
	private BusLineStopInterface busLineStop = new BusLineStopImpl();
	private String line;
	
    /**
     * Default constructor. 
     */
    public TransportDataServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().setAttribute("busLines", busLine.getAll());
		request.getRequestDispatcher("/home.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		line = request.getParameter("line");
//		System.out.print("\n ----- " + line + " ----- \n");
		
		ObjectMapper mapper = new ObjectMapper();
		
		//Object to JSON in String
		String busLinesJson = mapper.writeValueAsString(busLine.getAll());
		String busStopsJson = mapper.writeValueAsString(busStop.getAll());
		String busLineStopsJson = mapper.writeValueAsString(busLineStop.getBusLineStops(line));
		
		request.getSession().setAttribute("busLines", busLinesJson);
		request.getSession().setAttribute("busStops", busLineStopsJson);
		request.getRequestDispatcher("/map.jsp").forward(request, response);
	}

}
