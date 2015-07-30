package com.onmyway;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class FindWayPointsWithGoogleAPI {
	private static final String LOWER_MANHATTAN_LAT = "40.722543";
	private static final String LOWER_MANHATTAN_LNG = "-73.998585";
	private static final String BROOKLYN_LAT = "40.7057";
	private static final String BROOKLYN_LNG = "-73.9964";
	
	private static final String KEY= "AIzaSyDhBcX5rEjiT1lYvn5e3w6CsbPws9rofOc";
//	private static final String WALL_STREET = new LatLng(40.7064, -74.0094);

	private static final String URL = "http://maps.google.com/maps/geo?output=json";
//    private static final String DEFAULT_KEY = "ABQIAAAAxPbpriJFATP1kV4Jfg7FrhTHx8S3jtCwO1hw0XE3N9WOac4cqRQ8_vMKB22No44yFau5GRY7TcCHZA";

    private static String calculateRoute(String mapsApiDirectionsUrl) throws Exception{
    	System.out.println(mapsApiDirectionsUrl);
    	String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(mapsApiDirectionsUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.connect();
			iStream = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
			}
			data = sb.toString();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
    }
    
    private static String getMapsApiDirectionsUrl() {
		String params = "origin="
				+ LOWER_MANHATTAN_LAT + "," + LOWER_MANHATTAN_LNG
				+ "&destination=" + BROOKLYN_LAT + ","
				+ BROOKLYN_LNG;

		String sensor = "sensor=false";
//		String params = waypoints + "&" + sensor;
//		String params = "origin=1918+Eighth+Avenue+,seattle&destination=440+terry+ave+, seattle";
		String output = "json";
		String key="&key="+KEY;
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + params+key;
		return url;
	}

    
    public static void main(String[] args) throws Exception {

        String url = getMapsApiDirectionsUrl();
        calculateRoute(url);
//        System.out.println(calculateRoute(url));
    }


}
