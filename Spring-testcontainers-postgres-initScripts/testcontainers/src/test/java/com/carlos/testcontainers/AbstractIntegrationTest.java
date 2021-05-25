package com.carlos.testcontainers;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfiguracionTest.class })
@ActiveProfiles("integrationTest")
public class AbstractIntegrationTest {

}
