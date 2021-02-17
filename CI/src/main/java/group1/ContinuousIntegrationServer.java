package group1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.BufferedReader;

import java.util.stream.*;
import java.util.Date;
import java.util.HashMap;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;

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
                                    + "Passed tests: " + (passedTests ? "YES" : "NO") + "\n\n"
                                    + "See all build results at https://ci-server-assignment2.web.app/ \n\n";
            for (String email : EMAIL_ADDRESSES) {
                Email.sendEmailAndReportSuccess(email, "Build results from " + REPO_URI, messageContents);
            }

            // Update database
            try {
                Firestore db = FirestoreClient.getFirestore();

                // Data to be added to database
                HashMap<String, Object> data = new HashMap<>();
                data.put("commit_id", commitID);
                data.put("branch", branch);
                data.put("passed_tests", passedTests ? "YES" : "NO");
                data.put("timestamp", (new Date()).toString());

                ApiFuture<WriteResult> future = db.collection("test").document().set(data);

                // future.get() blocks on response
                System.out.println("Updated database at time: " + future.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.getWriter().println("CI job done");
    }

}
