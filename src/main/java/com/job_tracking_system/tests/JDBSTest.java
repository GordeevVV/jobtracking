package com.job_tracking_system.tests;

import com.job_tracking_system.App;
import com.job_tracking_system.entity.Person;
import com.job_tracking_system.entity.Task;
import com.job_tracking_system.repository.PersonJpaRepository;
import com.job_tracking_system.repository.TaskJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private TaskJpaRepository taskJpaRepository;

    //TEST NAMING STRUCTURE *method name* + *RepositoryName* + Test
    @Test
    public void savePersonTest() {
        Person person = new Person("login", "password", "position");
        Person savedPerson = personJpaRepository.save(person);
        assertThat(savedPerson).usingRecursiveComparison().ignoringFields("id").isEqualTo(person);
        personJpaRepository.deleteAll();
    }

    @Test
    public void saveTaskTest() {
        Task task = new Task("name", "status", 1.0, "desc");
        Task savedTask = taskJpaRepository.save(task);
        assertThat(savedTask).usingRecursiveComparison().ignoringFields("id").isEqualTo(task);
        taskJpaRepository.deleteAll();
    }

    @Test
    public void findByIdPersonTest() {
        Person person = new Person("login", "password", "position");
        Person savedPerson = personJpaRepository.save(person);
        assertThat(savedPerson).usingRecursiveComparison().isEqualTo(personJpaRepository.findById(savedPerson.getId()).get());
        personJpaRepository.deleteAll();
    }

    @Test
    public void findByIdTaskTest() {
        Task task = new Task("name", "status", 1.0, "desc");
        Task savedTask = taskJpaRepository.save(task);
        assertThat(savedTask).usingRecursiveComparison().isEqualTo(taskJpaRepository.findById(savedTask.getId()).get());
        taskJpaRepository.deleteAll();
    }

    @Test
    public void findAllPersonTest() {
        Person person1 = new Person("login1", "password1", "position1");
        Person person2 = new Person("login2", "password2", "position2");
        personJpaRepository.save(person1);
        personJpaRepository.save(person2);
        assertThat(personJpaRepository.findAll()).asList().size().isEqualTo(2);
        personJpaRepository.deleteAll();
    }

    @Test
    public void findAllTaskTest() {
        Task task1 = new Task("name1", "status1", 1.0, "desc1");
        Task task2 = new Task("name2", "status2", 1.0, "desc2");
        taskJpaRepository.save(task1);
        taskJpaRepository.save(task2);
        assertThat(taskJpaRepository.findAll()).asList().size().isEqualTo(2);
        taskJpaRepository.deleteAll();
    }

    @Test
    public void findByNameTaskTest() {
        Task task = new Task("name", "status", 1.0, "desc");
        Task savedTask = taskJpaRepository.save(task);
        assertThat(taskJpaRepository.findByName(savedTask.getName())).usingRecursiveComparison().isEqualTo(savedTask);
        taskJpaRepository.deleteAll();
    }

    @Test
    public void findByStatusTaskTest() {
        Task task1 = new Task("name1", "status1", 1.0, "desc1");
        Task task2 = new Task("name2", "status2", 2.0, "desc2");
        Task task3 = new Task("name3", "status1", 3.0, "desc3");
        taskJpaRepository.save(task1);
        taskJpaRepository.save(task2);
        taskJpaRepository.save(task3);
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task3);
        assertThat(taskJpaRepository.findByStatus(task1.getStatus())).usingRecursiveComparison().isEqualTo(list);
        taskJpaRepository.deleteAll();
    }

    @Test
    public void findByDifficultyTaskTest() {
        Task task1 = new Task("name1", "status1", 1.0, "desc1");
        Task task2 = new Task("name2", "status2", 2.0, "desc2");
        Task task3 = new Task("name3", "status3", 1.0, "desc3");
        taskJpaRepository.save(task1);
        taskJpaRepository.save(task2);
        taskJpaRepository.save(task3);
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task3);
        assertThat(taskJpaRepository.findByDifficulty(task1.getDifficulty())).usingRecursiveComparison().isEqualTo(list);
        taskJpaRepository.deleteAll();
    }

    @Test
    public void findByImplementerIdTaskTest() {
        Task task1 = new Task("name1", "status1", 1.0, "desc1");
        Task task2 = new Task("name2", "status2", 2.0, "desc2");
        Task task3 = new Task("name3", "status3", 1.0, "desc3");
        Person person = new Person("login", "password", "position");
        person.setPosition("implementer");
        Person savedPerson = personJpaRepository.save(person);
        task1.setImplementerId(savedPerson.getId());
        task3.setImplementerId(savedPerson.getId());
        taskJpaRepository.save(task1);
        taskJpaRepository.save(task2);
        taskJpaRepository.save(task3);
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task3);
        assertThat(taskJpaRepository.findByImplementerId(task1.getImplementerId())).usingRecursiveComparison().isEqualTo(list);
        taskJpaRepository.deleteAll();
    }

    @Test
    public void findByLoginPersonTest() {
        Person person = new Person("login1", "password1", "position1");
        Person savedPerson = personJpaRepository.save(person);
        assertThat(personJpaRepository.findByLogin(savedPerson.getLogin())).usingRecursiveComparison().isEqualTo(savedPerson);
        personJpaRepository.deleteAll();
    }

    @Test
    public void findByPositionPersonTest() {
        Person person1 = new Person("login1", "password1", "position1");
        Person person2 = new Person("login2", "password2", "position1");
        Person person3 = new Person("login3", "password3", "position3");
        Person savedPerson1 = personJpaRepository.save(person1);
        Person savedPerson2 = personJpaRepository.save(person2);
        Person savedPerson3 = personJpaRepository.save(person3);
        List<Person> list = new ArrayList<>();
        list.add(savedPerson1);
        list.add(savedPerson2);
        assertThat(personJpaRepository.findByPosition(savedPerson1.getPosition())).usingRecursiveComparison().isEqualTo(list);
        personJpaRepository.deleteAll();
    }
}
