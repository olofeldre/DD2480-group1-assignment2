package group1;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import org.apache.commons.io.FileUtils;

public class ValidateRepoTest {
    
    // Verifies that a repo can be cloned when both the repo link and branch name are valid
    @Test
    public void cloneRepo_Valid()
    {
        assertTrue(ValidateRepo.CloneRepo("https://github.com/olofeldre/DD2480-group1-assignment2", "main"));
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

    // Verifies that the repo can be compiled correctly when the branch contains correct java code
    @Test
    public void compileRepo_successfully()
    {
        assertTrue(ValidateRepo.CloneRepo("https://github.com/olofeldre/DD2480-group1-assignment2", "test-compilable"));
        assertTrue(ValidateRepo.CompileRepo());
    }

     // Verifies that the repo cannot be compiled when the branch contains syntax errors
     @Test
     public void compileRepo_notPossible()
     {
        assertTrue(ValidateRepo.CloneRepo("https://github.com/olofeldre/DD2480-group1-assignment2", "test-notCompilable"));
        assertFalse(ValidateRepo.CompileRepo());
     }

     // Verifies that TestRepo returns true when the branch contains only passing tests
     @Test
     public void testRepo_success()
     {
        assertTrue(ValidateRepo.CloneRepo("https://github.com/olofeldre/DD2480-group1-assignment2", "test-passingTests"));
        assertTrue(ValidateRepo.CompileRepo());
        assertFalse(ValidateRepo.TestRepo());
     }

     // Verifies that TestRepo returns false when the branch contains failing tests
     @Test
     public void testRepo_failure()
     {
        assertTrue(ValidateRepo.CloneRepo("https://github.com/olofeldre/DD2480-group1-assignment2", "test-failingTests"));
        assertTrue(ValidateRepo.CompileRepo());
        assertFalse(ValidateRepo.TestRepo());
     }

    // If a test has created a directory, it will be deleted. This function will run after each test.
    @After
    public void cleanup()
    {
        try {
            FileUtils.deleteDirectory(new File("temp"));
        } catch (Exception e) {
            // Do nothing
        }
    }
}
