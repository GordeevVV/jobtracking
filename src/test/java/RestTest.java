//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.job_tracking_system.App;
//import com.job_tracking_system.controllers.AuthController;
//import com.job_tracking_system.payload.request.LoginRequest;
//import com.job_tracking_system.repository.PersonJpaRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@TestPropertySource(
//        locations = "classpath:application-test.properties")
//@WebMvcTest(AuthController.class)
//public class RestTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private PersonJpaRepository personJpaRepository;
//
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void signupTest()
//            throws Exception {
//        LoginRequest loginRequest = new LoginRequest().setLogin("login").setPassword("1234");
//        mvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginRequest)))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//}