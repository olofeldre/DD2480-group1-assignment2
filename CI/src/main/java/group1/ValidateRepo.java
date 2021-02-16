package group1;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.util.*;

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.commons.io.FileUtils;

class ValidateRepo {

    public ValidateRepo() {}
    /**
     * Clones the specified branch of the specified repo into temp/repo
     * 
     * @param repoURI   an URL to the repo
     * @param branch    the branch name
     * @return          true if the clone was successful, false otherwise
     */ 
    //repoURI should be "https://github.com/olofeldre/DD2480-group1-assignment2"
    public static boolean CloneRepo(String repoURI, String branch) {
        String branchPath = "refs/heads/" + branch;
        ArrayList<String> branchList = new ArrayList<String>();
        branchList.add(branchPath);

        try {
            Git git = Git.cloneRepository()
            .setURI(repoURI)
            .setDirectory(new File("temp/repo"))
            .setBranchesToClone(branchList)
            .setBranch(branchPath)
            .call();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Compiles code in the repo located at temp/repo by running "mvn clean compile assembly:single"
     * 
     * @return true if the compilation was successful, false otherwise
     */ 
    public static boolean CompileRepo() {
        return runCommandAndLookForKeywordInOutput("mvn clean compile assembly:single", "temp/repo/CI", "ERROR");
    }

    /**
     * Tests code in the repo located temp/repo by running "mvn test" in temp/repo/CI
     * 
     * @return true if the tests were successful, false otherwise
     */ 
    public static boolean TestRepo() {
        return runCommandAndLookForKeywordInOutput("mvn test", "temp/repo/CI", "ERROR");
    }

    private static boolean runCommandAndLookForKeywordInOutput(String command, String directory, String keyword) {
        try {
            String[] envp = {""};
            Process process = Runtime.getRuntime().exec(command, envp, new File(directory));
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            if (res.contains(keyword)){
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void cleanup()
    {
        try {
            FileUtils.deleteDirectory(new File("temp"));
        } catch (Exception e) {
            // Do nothing
        }
    }

    /**
     * Validates the specified repo by cloning, compiling and running tests
     * 
     * @param repoURI   an URL to the repo
     * @param branch    the branch name
     * @return          true if the validation was successful, false otherwise
     */ 
    public static boolean Validate(String repoURI, String branch) {
        boolean check = CloneRepo(repoURI, branch);
        if (check) {
            check = CompileRepo();
        }
        if (check) {
            check = TestRepo();
        }
        cleanup();
        return check;
    }
}
