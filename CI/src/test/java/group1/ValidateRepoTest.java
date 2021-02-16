package group1;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import org.apache.commons.io.FileUtils;

public class ValidateRepoTest {
    
    // Verifies that a repo can be cloned when both the repo link and branch name are valid
    @Test
    public void cloneRepo_Valid()
    {
        assertTrue(ValidateRepo.CloneRepo("https://github.com/olofeldre/DD2480-group1-assignment2", "main"));
        try {
            FileUtils.deleteDirectory(new File("temp"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Verifies that a repo cannot be cloned when the repo link is invalid
    @Test
    public void cloneRepo_InvalidRepoLink()
    {
        assertFalse(ValidateRepo.CloneRepo("https://github.com/olofeldre/DD2480-group1-assignment2222", "main"));
    }

    // Verifies that a repo cannot be cloned when the repo link is invalid
    @Test
    public void cloneRepo_InvalidBranchName()
    {
        assertFalse(ValidateRepo.CloneRepo("https://github.com/olofeldre/DD2480-group1-assignment2", "invalidBranchName"));
    }
}
