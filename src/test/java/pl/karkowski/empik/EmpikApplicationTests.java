package pl.karkowski.empik;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "example.firstProperty=annotation" })
public class EmpikApplicationTests {

    @Test
    public void contextLoads() {
    }

}
