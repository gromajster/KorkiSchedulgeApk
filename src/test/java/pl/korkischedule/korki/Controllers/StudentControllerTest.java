package pl.korkischedule.korki.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.korkischedule.korki.Repository.AddRepo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AddRepo addRepo;
    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:templates");
        viewResolver.setSuffix(".html");
        StudentController studentController = new StudentController(addRepo);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/student"))
                .andExpect(view().name("uczeń"));
    }

    @Test
    public void givenStudent_whenMockMVC_thenVerifyStatus() throws Exception {
        mockMvc.perform(get("/student"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenCategoryIsEmpty_ShouldReturnView() throws Exception {
        String category = null;
        mockMvc.perform(get("/student").param("category", category))
                .andExpect(view().name("uczeń"));
    }
}