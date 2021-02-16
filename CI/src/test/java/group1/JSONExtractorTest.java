package group1;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONExtractorTest
{
    private JSONExtractor je;
    @Before
    public void setup()
    {
        try{
            String json = new String(Files.readAllBytes(Paths.get("src/test/resources/github.json")));
            je = new JSONExtractor(json);
        }
        catch (Exception e)
        {
            System.err.println(e);
            assertTrue(false);
        }
    }

    @Test
    public void testReadingCommitID()
    {
        assertEquals("c7e1aff68dae9b954bd267ed9173b33d87a97ac4", je.getCommit());
    }
    @Test
    public void testReadingBranchName()
    {
        assertEquals("issue#18-HTMLPageUniqueURL", je.getBranch());
    }
    @Test
    public void negativeTest()
    {
        assertNotEquals("", je.getBranch());
    }
}