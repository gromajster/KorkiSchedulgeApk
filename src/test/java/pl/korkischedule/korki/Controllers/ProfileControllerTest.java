package pl.korkischedule.korki.Controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.korkischedule.korki.Entity.User;
import pl.korkischedule.korki.Repository.AddRepo;
import pl.korkischedule.korki.Repository.OpinionRepo;
import pl.korkischedule.korki.Repository.UserRepo;
import pl.korkischedule.korki.Repository.YoutubeVideoRepo;
import pl.korkischedule.korki.Security.SecurityUtils;
import pl.korkischedule.korki.service.EmailSender;
import pl.korkischedule.korki.service.StorageService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ProfileControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserRepo userRepo;
    @Mock
    private AddRepo addRepo;
    @Mock
    private OpinionRepo opinionRepo;
    @Mock
    private EmailSender emailSender;

    @Mock
    private StorageService storageService;
    @Mock
    private YoutubeVideoRepo youtubeVideoRepo;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:templates");
        viewResolver.setSuffix(".html");
        ProfileController profileController = new ProfileController(userRepo, addRepo, opinionRepo, emailSender, storageService, youtubeVideoRepo);
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).setViewResolvers(viewResolver).build();
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setEmail("example@example.com");
        user.setSurname("surname");
        user.setTelephone("13456789");
        user.setDescription("default user");
        userRepo.save(user);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getEmail(), null, null));
    }

    @After
    public void removeAuth() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }


    @Test
    public void userProfile() throws Exception {

    }

    @Test
    public void profileTeacher() {
    }


    @Test
    public void userDetails() throws Exception {

        if (SecurityUtils.isLoggedIn()) {
            mockMvc.perform(get("/profile/user-info"))
                    .andExpect(view().name("user-info"));
        }
    }

    @Test
    public void givenUserInfo_whenMockMVC_thenVerifyStatus() throws Exception {
        mockMvc.perform(get("/profile/user-info"))
                .andExpect(status().isOk());
    }

    @Test
    public void changeDetails() throws Exception {
        mockMvc.perform(post("/profile"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void saveOptinion() throws Exception {
    }

    @Test
    public void sendEmail() throws Exception {
        mockMvc.perform(post("/profile/send"))
                .andExpect(status().is3xxRedirection());
    }


    @Test
    public void fileList() throws Exception {
    }

    @Test
    public void getPDF1() {
    }

    @Test
    public void userProfileFiles() {
    }

    @Test
    public void listFilesUsingDirectoryStream() {
    }
}