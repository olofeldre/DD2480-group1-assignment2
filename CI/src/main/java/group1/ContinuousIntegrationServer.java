package group1;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import java.util.Enumeration;
import java.io.BufferedReader;
import java.util.stream.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 Skeleton of a ContinuousIntegrationServer which acts as webhook
 See the Jetty documentation for API documentation of those classes.
*/
public class ContinuousIntegrationServer extends AbstractHandler
{
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        System.out.println(target);
	if (baseRequest.getMethod().equals("POST")){
		BufferedReader cont = request.getReader();
		Stream<String> linesStream = cont.lines();

		//String[] lines = linesStream.toArray();
		Object[] lines = linesStream.toArray();

	       	String lineJson = (String) lines[0];

		final String pattern ="\\\"after\\\":\\\"([0-9a-f]{0,41})\\\",";
		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(lineJson);
		while(m.find()) {
		    System.out.println("Recognize the ID of commit : " + m.group(1));
		}
		//System.out.println("Found value: " + m.group(0) );
	}

        // here you do all the continuous integration tasks
        // for example
        // 1st clone your repository
        // 2nd compile the code
	// 3nd run tests

        response.getWriter().println("CI job done");
    }

    // used to start the CI server in command line
    public static void main(String[] args) throws Exception
    {
	final int portNr = 8001;
        Server server = new Server(portNr);
        server.setHandler(new ContinuousIntegrationServer());
        server.start();
        server.join();
    }
}
