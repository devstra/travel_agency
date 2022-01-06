package fr.lernejo.travelsite;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LauncherTest {
    @Test
    public void main() {
        Launcher.main(new String[]{});
    }
}
