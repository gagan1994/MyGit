
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class HelloWorld {
	public static String NOT_FOUND_TEXT = "no dates found for given time";

	public static void main(String[] args) {
		String ds1, ds2, ds3;
		Map<String,String> dates =new HashMap<>();
		dates.put("21-02-1994 00:00:00", "01:02:22");
		dates.put("21-01-2018 00:00:00", "01:33:22");
		for (Entry<String, String> entry : dates.entrySet()) {
		    String date = entry.getKey();
		    String time = entry.getValue();
		    try {
				Date d1 = convertStringToDate(date, "dd-mm-yyyy' 'HH:mm:ss");
				Date t1 = convertStringToDate(time,"HH:mm:ss");
				String op1 = getSearchResult(d1, t1);
				System.out.println(op1 + "\n");
			} catch (ParseException e) {
				 
				System.out.println("error in parsing date/time "+date+" "+time);
			}
		}
		 
		

	}

	public static String getSearchResult(Date dateTime, Date time) {
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("D:/My Git/MyGit/eclipseW/XmlParse/src/DateFile.xml");

			doc.getDocumentElement().normalize();

			String expression = "//Days/Day";
			XPath xPath = XPathFactory.newInstance().newXPath();

			NodeList nodes = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

			// NodeList nList = doc.getElementsByTagName("Date");
			String dates = "";
			for (int i = 0; i < nodes.getLength(); i++) {
				Node the_node = nodes.item(i);

				if (the_node instanceof Element) {
					Element the_element = (Element) the_node;
					String date = the_element.getTextContent();
					if (convertStringDatetimeToLong(date, dateTime)) {
						dates = getonlyDateString(date);
						break;
					}
				}
			}
			return (dates.trim().length() == 0) ? NOT_FOUND_TEXT : dates+" "+ getvalidTime(time);
		} catch (Exception e) {
			e.getMessage();
		}

		return NOT_FOUND_TEXT;
	}

	public static String getvalidTime(Date time) {
		SimpleDateFormat time_f = new SimpleDateFormat("HH:mm:ss");
		time_f.setLenient(false);

		String date2 = time_f.format(time);
		return date2;

	}

	public static String getonlyDateString(String string_date) {

		String[] date = string_date.split(" ");
		return date[0];

	}

	public static Date convertStringToDate(String ip_date, String pattern) throws ParseException {
		SimpleDateFormat date_f = new SimpleDateFormat(pattern);
		Date date2 = null;
		date_f.setLenient(false);
		date2 = date_f.parse(ip_date);
		return date2;

	}

	public static boolean convertStringDatetimeToLong(String string_date, Date ip_date) {
		SimpleDateFormat date_f = new SimpleDateFormat("dd-mm-yyyy' 'HH:mm:ss");
		try {
			Date date = date_f.parse(string_date);

			// if(date.getHours() == time.getHours() &&
			// date.getMinutes()==time.getMinutes()&& date.getSeconds()==time.getSeconds())
			if (date.getDay() == ip_date.getDay() && ip_date.getMonth() == ip_date.getMonth()
					&& date.getYear() == date.getYear())
				return true;

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;
	}
}
