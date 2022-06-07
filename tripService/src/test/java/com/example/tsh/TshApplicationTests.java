package com.example.tsh;

import com.example.tsh.service.TripServiceTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class TshApplicationTests {

    @Test
    void contextLoads() {

    }

}
