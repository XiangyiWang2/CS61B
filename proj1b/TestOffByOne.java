import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testOffByOne(){
        assertTrue(offByOne.equalChars('x','x'));
        assertFalse(offByOne.equalChars('y','z'));

    }

    }



