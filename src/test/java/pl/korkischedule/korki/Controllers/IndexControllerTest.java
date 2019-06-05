package pl.korkischedule.korki.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.korkischedule.korki.Entity.Add;
import pl.korkischedule.korki.Entity.Category;
import pl.korkischedule.korki.Repository.AddRepo;

import java.util.Collections;
import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class IndexControllerTest {

    private MockMvc mockMvc;
    @Mock
    private AddRepo addRepo;


    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:templates");
        viewResolver.setSuffix(".html");
        IndexController indexController = new IndexController(addRepo);
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void shouldReturnIndex() throws Exception {
        //given

        //when
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
        //then
    }

    @Test
    public void givenURI_whenMockMVC_thenVerifyStatus() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
    @Test
    public void whenCategoryIsSet_ShouldReturnIndexWithAttribute() throws Exception {
        //given
        String category = "MATEMATYKA";
        Mockito.when(addRepo.findAllByCategory(Category.valueOf(category))).thenReturn(new HashSet<>(Collections.singletonList(new Add())));
        //when
        mockMvc.perform(get("/").param("category", category))
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("adds"));
    }

}