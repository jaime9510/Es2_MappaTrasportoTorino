<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.List"%>
<%@page import="it.polito.ai.es2.orm.mapping.*"%>


<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Biglietti trasporto urbano</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</head>

<body>
	<div class="container">
		<div class="jumbotron text-center">
			<h1>Trasporto Urbano Torino</h1>
			<p></p>
		</div>
		<div class="list-group">
			<a href="#" class="list-group-item active">
				<h2 class="list-group-item-heading text-center">Linee trasporto Torino</h2>
				<p class="list-group-item-text text-center">Scelga la linea che vuole per visualizarla sul mappa</p>
			</a>
			<%
				List<BusLine> busLines = (List<BusLine>) request.getSession().getAttribute("busLines");
				for(BusLine bl : busLines) {
			%>
			<form action="TransportDataServlet" method="post">
				<input type="hidden" class="text" value=<%=bl.getLine()%> name="line" id="line">
				<button type="submit" class="list-group-item">
					<h3 class="list-group-item-heading"><%=bl.getLine()%></h3>
					<p class="list-group-item-text"><%=bl.getDescription()%></p>
				</button>
			</form>
			
			<%
				}
			%>
		</div>

	</div>
</body>
</html>