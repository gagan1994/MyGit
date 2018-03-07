
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
	public static String NOT_FOUND_TEXT="no dates found for given time";
public static void main(String[] args) {
    Scanner scanner = new Scanner( System.in );

    System.out.println(getSearchResult("21-02-1994 00:00:00","01:02:22")+"\n");
    System.out.println(getSearchResult("21-01-2018 00:00:00","01:02:22"));

   
}
public static String getSearchResult(String dateTime,String time) {
	 try{
	     
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc =
			builder.parse("D:/eclipseW/XmlParse/src/DateFile.xml");
	        
	        
	    	doc.getDocumentElement().normalize();
	    
	    	String expression = "//Days/Day";	
	    	XPath xPath = XPathFactory.newInstance().newXPath();

	    	NodeList nodes  = (NodeList)  xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

	    	//NodeList nList = doc.getElementsByTagName("Date");
	    	String  dates="";
	    	for(int i=0; i<nodes.getLength(); i++)
	        {
	         Node the_node = nodes.item(i);

	        if(the_node instanceof Element)
	             {
	             Element the_element=(Element) the_node;
	             String date=the_element.getTextContent();
	             	if(convertStringDatetimeToLong(date,dateTime)) {
	             		dates=getonlyDateString(date);
	             		break;
	             	}
	             }
	         }	
	    	return (dates.trim().length()==0) ?	NOT_FOUND_TEXT:dates+" "+time; 
	    }
	    catch (Exception e){
	      e.getMessage();
	    }
	
	return NOT_FOUND_TEXT;
}
public static String getonlyDateString(String string_date) {
	
	        String[] date=string_date.split(" ");
	        return date[0];
	    
}
public static boolean convertStringDatetimeToLong(String string_date,String ip_date) {
    SimpleDateFormat date_f = new SimpleDateFormat("dd-mm-yyyy' 'HH:mm:ss");
    SimpleDateFormat date_t = new SimpleDateFormat("dd-mm-yyyy' 'HH:mm:ss");
    try {
        Date date = date_f.parse(string_date);
        Date date2=date_t.parse(ip_date);
                
       // if(date.getHours()	== time.getHours() && date.getMinutes()==time.getMinutes()&& date.getSeconds()==time.getSeconds())
        if(date.getDay()==date2.getDay()&&date.getMonth()==date2.getMonth()&&date.getYear()==date.getYear())
        	return true;
        
    } catch (ParseException e) {
        e.printStackTrace();
    }

    return false;
}
}
