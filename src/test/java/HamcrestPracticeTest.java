import model.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HamcrestPracticeTest {
    private Platform platform;

    @BeforeEach
    void setUp() {
        platform = Platform.createPlatform();
    }

    @Test
    void testEquals(){
        HamcrestPractice hamcrestPracticeFirst = new HamcrestPractice();
        HamcrestPractice hamcrestPracticeSecond = new HamcrestPractice();

//        assertThat(hamcrestPracticeFirst, equalTo(hamcrestPracticeSecond));

    }

    @Test
    void testThatOnlyOnePlatformExists(){
        Platform platform2 = Platform.createPlatform();

        assertThat(platform,equalTo(platform2) );
    }
}

class HamcrestPractice{

}
