package com.ntatvr.core;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CoreServiceApplication.class)
public abstract class IntegrationTest {

}
