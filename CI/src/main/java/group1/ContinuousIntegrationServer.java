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

    private final String REPO_URI = "https://github.com/olofeldre/DD2480-group1-assignment2";
    private final String[] EMAIL_ADDRESSES = new String[]{"anton.lovstrom4@gmail.com", 
                                                          "joakim.skoog.97@gmail.com", 
                                                          "e.caroline.larsen@gmail.com", 
                                                          "olofthegren@gmail.com",
                                                          "robin.wanlund@hotmail.com"};

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

            Object[] lines = linesStream.toArray();

            String lineJson = (String) lines[0];

            JSONExtractor extractor = new JSONExtractor(lineJson);
            
            String branch = extractor.getBranch();
            String commitID = extractor.getCommit();

            boolean passedTests = ValidateRepo.Validate(REPO_URI, branch);

            final String messageContents = "Build results from branch " + branch + " of " + REPO_URI + "\n\n"
                                    + "Commit ID: " + commitID + "\n"
                                    + "Branch: " + branch + "\n"
                                    + "Passed tests: " + (passedTests ? "YES" : "NO") + "\n";
            for (String email : EMAIL_ADDRESSES) {
                Email.sendEmailAndReportSuccess(email, "Build results from " + REPO_URI, messageContents);
            }
        }

        response.getWriter().println("CI job done");
    }

}
