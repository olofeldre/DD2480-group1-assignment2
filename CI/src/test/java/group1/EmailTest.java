package group1;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;



/**
 * Test that reading password from file works.
 */
public class EmailTest
{
    // Verify that password in file is exactly the same as the returned string from readPasswordFile()
    @Test
    public void passwordIsCorrect()
    {
        assertEquals("hejpadig", Email.readPasswordFile("src/test/resources/t1.txt"));
        return;
    }

    @Test
    public void passwordIsNotCorrect()
    {
        assertNotEquals("Hejpadig", Email.readPasswordFile("src/test/resources/t1.txt"));
        return;
    }

    // If this test in particular fails, you have most likely not recieved the password, 
    // which is neccessary to run this server.
    @Test
    public void sendAnEmail()
    {
        assertTrue(Email.sendEmailAndReportSuccess("group1SoffaKth@gmail.com", 
            "Unit tests have been run.", "A message body"));
        return;
    }
}
