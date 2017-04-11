<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="it.polito.ai.es2.orm.mapping.BusLine"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Mappa Trasporto Torino</title>
	<link rel="stylesheet" href="https://unpkg.com/leaflet@1.0.3/dist/leaflet.css" />
	<script src="https://unpkg.com/leaflet@1.0.3/dist/leaflet.js"></script>
	
	<script src='https://api.mapbox.com/mapbox-gl-js/v0.34.0/mapbox-gl.js'></script>
	<link href='https://api.mapbox.com/mapbox-gl-js/v0.34.0/mapbox-gl.css' rel='stylesheet' />
	
	<style>
		#mapid { height: 680px; }
	</style>
</head>

<body>
	<div id="mapid"></div>

	<script>
		var mymap = L.map('mapid').setView([45.06,7.67], 12);

		<%
			String busLinesJson = (String) request.getSession().getAttribute("busLines");
			String busStopsJson = (String) request.getSession().getAttribute("busStops");
		%>
		
		// load a tile layer
		L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/streets-v9/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1IjoiamFpbWU5NTEiLCJhIjoiY2oxY2t0Z3Z1MDAzbDMyb2V3ZHkzbzR3cSJ9.QH2uuZ3vBxjAg-7rdDg0Nw', {
		    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
		    maxZoom: 18,
		    id: 'your.mapbox.project.id',
		    accessToken: 'pk.eyJ1IjoiamFpbWU5NTEiLCJhIjoiY2oxY2t0Z3Z1MDAzbDMyb2V3ZHkzbzR3cSJ9.QH2uuZ3vBxjAg-7rdDg0Nw'
		}).addTo(mymap);

		var busLines = <%=busLinesJson%>;
		var busStops = <%=busStopsJson%>;
		var len = busStops.length;
		var polylinePoints = [];
		
		for (i = 0; i < len; i++) {
			polylinePoints.push(new L.LatLng(busStops[i].lat, busStops[i].lng));
			
			var marker = L.marker([busStops[i].lat, busStops[i].lng]).addTo(mymap);
			marker.bindPopup(busStops[i].name);
		};

        var polylineOptions = {
			color: 'blue',
			weight: 6,
			opacity: 0.9
        };

		var polyline = new L.Polyline(polylinePoints, polylineOptions);
		mymap.addLayer(polyline); 
		
	</script>
</body>
</html>