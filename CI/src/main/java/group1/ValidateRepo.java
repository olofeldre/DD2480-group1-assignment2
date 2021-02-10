package group1;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.util.*;

import java.io.File;
import java.util.ArrayList;

class ValidateRepo {

    public ValidateRepo() {}

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

    public static boolean Validate(String repoURI, String branch) {
        boolean check = CloneRepo(repoURI, branch);
        // TODO: call other functions to compile, test and cleanup
        return check;
    }
}
