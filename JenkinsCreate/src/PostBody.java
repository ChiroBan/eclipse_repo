	

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

//import com.sun.xml.internal.ws.handler.HandlerProcessor.RequestOrResponse;

public class PostBody {
	
	 public static void main(String[] args) throws IOException {
		 String argUrl = "http://54.191.165.200:8080/createItem?name=ECLIPSE";
		 // https://s3.ap-south-1.amazonaws.com/eclipse-jenkins/jenkins_config.xml
		 
		 String jenkinsCrumb = "27618ceee78db0ecd55eef7db4a13231";
	//	 String requestXml = "<?xml version='1.1' encoding='UTF-8'?><project>  <actions/>  <description></description>  <keepDependencies>false</keepDependencies>  <properties/>  <scm class=\"hudson.plugins.git.GitSCM\" plugin=\"git@3.9.1\">    <configVersion>2</configVersion>    <userRemoteConfigs>      <hudson.plugins.git.UserRemoteConfig>        <url>https://github.com/Hemalathamaranna/Jenkins-java-app</url>      </hudson.plugins.git.UserRemoteConfig>    </userRemoteConfigs>    <branches>      <hudson.plugins.git.BranchSpec>        <name>*/master</name>      </hudson.plugins.git.BranchSpec>    </branches>    <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>    <submoduleCfg class=\"list\"/>    <extensions/>  </scm>  <canRoam>true</canRoam>  <disabled>false</disabled>  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>  <triggers/>  <concurrentBuild>false</concurrentBuild>  <builders/>  <publishers/>  <buildWrappers/></project>";
		 String requestConfig = requestXml();
		 
	try
	{
	    URL url = new URL( argUrl );
	    URLConnection con = url.openConnection();
	    
	    // specify that we will send output and accept input
	    con.setDoInput(true);
	    con.setDoOutput(true);
	    con.setConnectTimeout( 20000 );  // long timeout, but not infinite
	    con.setReadTimeout( 20000 );
	    con.setUseCaches (false);
	    con.setDefaultUseCaches (false);
	   
	    // sending
	    con.setRequestProperty ( "Content-Type", "text/xml" );
	    con.setRequestProperty ( "Jenkins-Crumb", jenkinsCrumb );
	    OutputStreamWriter writer = new OutputStreamWriter( con.getOutputStream() );
	    writer.write( requestConfig );
	    writer.flush();
	    writer.close();
	    
	    // reading the response
	    InputStreamReader reader = new InputStreamReader( con.getInputStream() );
	    StringBuilder buf = new StringBuilder();
	    char[] cbuf = new char[ 2048 ];
	    int num;
	    while ( -1 != (num=reader.read( cbuf )))
	    {
	        buf.append( cbuf, 0, num );
	    }
	    String result = buf.toString();
	    System.err.println( "\nResponse from server after POST:\n" + result );
	}
	catch( Throwable t )
	{
	    t.printStackTrace( System.out );
	}
	 }
	 
	 public static String requestXml() throws IOException {
		 
		 
         
             String url = "https://s3.ap-south-1.amazonaws.com/eclipse-jenkins/jenkins_config.xml";
             String charset = "UTF-8";
             StringBuffer response = null;
             java.net.URLConnection connection = new URL(url).openConnection();
             connection.setRequestProperty("Accept-Charset", charset);
           
             
             if ( connection instanceof HttpURLConnection)
             {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                
                BufferedReader in = new BufferedReader
               		 (new InputStreamReader(connection.getInputStream()));
                String inputLine;
                response = new StringBuffer();
                while((inputLine = in.readLine()) != null) {
               	 response.append(inputLine);
                }
               
                in.close();
                
                System.out.println(httpConnection.getResponseCode());
                System.out.println(httpConnection.getResponseMessage());
                System.out.println(response.toString());
               }
             
		 
		return response.toString();
		 
	 }
}