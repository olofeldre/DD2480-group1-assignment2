package group1;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class AddTest
{
    // Check if add is correct
    @Test
    public void testCorrect()
    {
        assertEquals(12, Add.add(5, 7));
        return;
    }

}
