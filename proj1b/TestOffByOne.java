import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testOffByOne() {
        OffByOne obo=new OffByOne();
        assertTrue(obo.equalChars('&', '%'));
    }

    // Your tests go here.

}
