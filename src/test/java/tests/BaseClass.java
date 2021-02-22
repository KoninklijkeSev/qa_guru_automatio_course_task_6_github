package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseClass {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }
}
