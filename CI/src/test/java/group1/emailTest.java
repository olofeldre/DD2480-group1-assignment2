package group1;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;



/**
 * Test that reading password from file works.
 */
public class emailTest
{
    // Verify that password in file is exactly the same as the returned string from readPasswordFile()
    @Test
    public void passwordIsCorrect()
    {
        assertEquals("hejpadig", email.readPasswordFile("src/test/resources/t1.txt"));
        return;
    }

    @Test
    public void passwordIsNotCorrect()
    {
        assertNotEquals("Hejpadig", email.readPasswordFile("src/test/resources/t1.txt"));
        return;
    }
}
