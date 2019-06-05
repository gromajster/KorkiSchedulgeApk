package pl.korkischedule.korki.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.korkischedule.korki.Repository.AddRepo;
import pl.korkischedule.korki.Repository.UserRepo;
import pl.korkischedule.korki.Repository.UserRoleRepo;
import pl.korkischedule.korki.Security.SecurityUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DataJpaTest
public class RegisterControllerTest {
    private MockMvc mockMvc;
    @Mock
    private AddRepo addRepo;
    @Mock
    @Autowired
    private UserRepo userRepo;
    @Mock
    private UserRoleRepo userRoleRepo;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:templates");
        viewResolver.setSuffix(".html");
        //TODO SHOULD MOCK ALL OF REPOS FOR REGISTER CONTROLLER
        RegisterController registerController = new RegisterController(userRepo, userRoleRepo, passwordEncoder);
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void register() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(view().name("register"));
    }


    @Test
    public void givenRegister_whenMockMVC_thenVerifyStatus() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkAttribute() throws Exception {
        if (SecurityUtils.isLoggedIn()) {
            mockMvc.perform(get("/register"))
                    .andExpect(view().name("redirect"));
        } else {
            mockMvc.perform(get("/register"))
                    .andExpect(view().name("register"))
                    .andExpect(model().attributeExists("user"));

        }
    }

    @Test
    public void register1() {
    }
}