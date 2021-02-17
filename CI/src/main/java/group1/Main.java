package group1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
 
import java.io.IOException;
 
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
class Main
{
    // used to start the CI server in command line
    public static void main(String[] args) throws Exception
    {
        int port = 8080; // default port
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        Server server = new Server(port);
        server.setHandler(new ContinuousIntegrationServer()); 
        server.start();
        System.out.println("Listening on port " + port);
        server.join();
    }
}