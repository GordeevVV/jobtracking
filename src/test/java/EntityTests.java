import com.job_tracking_system.App;
import com.job_tracking_system.entity.EStatus;
import com.job_tracking_system.entity.Person;
import com.job_tracking_system.entity.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class EntityTests {
    @Test
    public void PersonTest() {
        Person person = new Person();
        Person person1 = new Person("login", "pass");
    }

    @Test
    public void TaskTest() {
        Task task = new Task();
        Task task1 = new Task("name", EStatus.STATUS_ASSIGNED, 1.0, "desc");
    }

    @Test
    public void setPersonTest(){
        Person person = new Person();
        person.setLogin("log");
        person.setPassword("pass");
    }
    @Test
    public void setTaskTest(){
        Task task = new Task();
        task.setName("name");
        task.setStatus(EStatus.STATUS_CREATED);
        task.setDescription("desc");
        task.setDifficulty(1.0);
    }
    @Test
    public void getPersonTest(){
        Person person = new Person();
        person.setLogin("log");
        person.setPassword("pass");
        String one = person.getLogin();
        String two = person.getPassword();
        Long four = person.getId();
    }

    @Test
    public void getTaskTest(){
        Task task = new Task();
        task.setName("name");
        task.setStatus(EStatus.STATUS_CREATED);
        task.setDescription("desc");
        task.setDifficulty(1.0);
        String one = task.getName();
        EStatus two = task.getStatus();
        String three = task.getDescription();
        Double four = task.getDifficulty();
        Long five = task.getId();
    }
}
