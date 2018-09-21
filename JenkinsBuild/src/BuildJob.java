import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

       public class BuildJob {
          private static String ENDPOINT ="http://34.217.136.243:8080/job/Test/build";
          public static void main(String[] args) throws IOException 
          { 
              String url = ENDPOINT;
              String charset = "UTF-8";
              String param1 = "mykey";

              String query = String.format("token=1234", 
                   URLEncoder.encode(param1, charset));
              java.net.URLConnection connection = new URL(url + "?" + query).openConnection();
              connection.setRequestProperty("Accept-Charset", charset);
              if ( connection instanceof HttpURLConnection)
              {
                 HttpURLConnection httpConnection = (HttpURLConnection) connection;
                 System.out.println(httpConnection.getResponseCode());
                 System.out.println(httpConnection.getResponseMessage());
              }
              else
              {
                 System.err.println ("error!");
              }
          }
   }