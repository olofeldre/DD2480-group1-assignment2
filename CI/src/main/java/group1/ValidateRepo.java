package group1;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.util.*;

import java.io.File;
import java.util.ArrayList;

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
     * Validates the specified repo by cloning, compiling and running tests
     * 
     * @param repoURI   an URL to the repo
     * @param branch    the branch name
     * @return          true if the validation was successful, false otherwise
     */ 
    public static boolean Validate(String repoURI, String branch) {
        boolean check = CloneRepo(repoURI, branch);
        // TODO: call other functions to compile, test and cleanup
        return check;
    }
}
