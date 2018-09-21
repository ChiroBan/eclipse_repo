import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;




       public class GetCrumb {
          private static String ENDPOINT ="http://54.202.108.0:8080/crumbIssuer/api/json";
          public static void main(String[] args) throws IOException 
          { 
              String url = ENDPOINT;
              String charset = "UTF-8";
          
              java.net.URLConnection connection = new URL(url).openConnection();
              connection.setRequestProperty("Accept-Charset", charset);
              
              if ( connection instanceof HttpURLConnection)
              {
                 HttpURLConnection httpConnection = (HttpURLConnection) connection;
                 httpConnection.setRequestMethod("GET");
                 
                 BufferedReader in = new BufferedReader
                		 (new InputStreamReader(connection.getInputStream()));
                 String inputLine;
                 StringBuffer response = new StringBuffer();
                 while((inputLine = in.readLine()) != null) {
                	 response.append(inputLine);
                 }
                 in.close();
                 
                 System.out.println(httpConnection.getResponseCode());
                 System.out.println(httpConnection.getResponseMessage());
                 System.out.println(response.toString());
                 
                 JSONObject jsonObj;
 				try {
 					jsonObj = new JSONObject(response.toString());
 					
 					System.out.println("Jenkins Crumb: "+jsonObj.get("crumb"));
 				} catch (JSONException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
              }
              else
              {
                 System.err.println ("error!");
              }
          }
   }
	
