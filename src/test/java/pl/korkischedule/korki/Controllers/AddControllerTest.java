package pl.korkischedule.korki.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.korkischedule.korki.Repository.AddRepo;
import pl.korkischedule.korki.Repository.UserRepo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class AddControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserRepo userRepo;
    @Mock
    private AddRepo addRepo;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:templates");
        viewResolver.setSuffix(".html");
        AddController addController = new AddController(userRepo, addRepo);
        mockMvc = MockMvcBuilders.standaloneSetup(addController).setViewResolvers(viewResolver).build();
    }


    @Test
    public void saveAddTest() throws Exception {
        mockMvc.perform(post("/add"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    public void changeUserEnabledFailTest() throws Exception {
        mockMvc.perform(get("/change-status/3"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
        mockMvc.perform(get("/change-status/3/fail"))
                .andExpect(status().isNotFound());
    }


}