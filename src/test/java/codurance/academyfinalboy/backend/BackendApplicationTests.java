package codurance.academyfinalboy.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@SpringBootTest
@Testcontainers
class BackendApplicationTests {

    @Container
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("docker-compose.yaml"))
                    .withExposedService("db", 1433);


    @Test
    void contextLoads() {
    }
}
