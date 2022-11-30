package com.job_tracking_system.tests;

import com.job_tracking_system.App;
import com.job_tracking_system.entity.Person;
import com.job_tracking_system.repository.PersonJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = App.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class JDBSTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private PersonJpaRepository personJpaRepository;

    @Test
    public void savePersonTest(){
        Person person = new Person("login", "password", "position");
        Person savedPerson = personJpaRepository.save(person);
        assertThat(savedPerson).usingRecursiveComparison().ignoringFields("personId").isEqualTo(person);
    }

}
