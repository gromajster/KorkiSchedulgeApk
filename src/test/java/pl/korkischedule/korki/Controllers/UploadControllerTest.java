package pl.korkischedule.korki.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.korkischedule.korki.service.StorageService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UploadControllerTest {

    private MockMvc mockMvc;
    @Mock
    private StorageService storageService;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:templates");
        viewResolver.setSuffix(".html");
        UploadController uploadController = new UploadController(storageService);
        mockMvc = MockMvcBuilders.standaloneSetup(uploadController)
                .setViewResolvers(viewResolver).build();
    }

    @Test
    public void upload() throws Exception {
        mockMvc.perform(post("/doUpload"))
                .andExpect(status().is4xxClientError());
    }

}