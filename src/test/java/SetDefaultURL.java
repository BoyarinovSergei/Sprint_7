import io.restassured.RestAssured;
import org.junit.BeforeClass;

import static dataForTests.URLsAndAPIs.MAIN_HOST;

public abstract class SetDefaultURL {
    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = MAIN_HOST;
    }
}
