package lt.techin.media_site.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lt.techin.media_site.dto.user.UserMapper;
import lt.techin.media_site.dto.user.UserPostDTO;
import lt.techin.media_site.model.User;
import lt.techin.media_site.repository.RoleRepository;
import lt.techin.media_site.repository.UserRepository;
import lt.techin.media_site.security.SecurityConfig;
import lt.techin.media_site.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@Import(SecurityConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private RoleRepository roleRepository;

    @Test
    void createUser_whenUserCreated_thenReturnAnd201() throws Exception {
        UserPostDTO dto1 = new UserPostDTO("something",
                "something@email.com",
                "Something9!",
                null,
                LocalDate.of(1995,12,12));
        User user = UserMapper.toEntity(dto1);

        BDDMockito.given(userService.createUser(ArgumentMatchers.any(UserPostDTO.class))).willReturn(user);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        //when

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto1)))

        //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").value("something"))
                .andExpect(jsonPath("$.email").value("something@email.com"))
                // Don't test the encoded password as it will be different each time
                .andExpect(jsonPath("$.dob").value("1995-12-12"));

        BDDMockito.verify(userService).createUser(ArgumentMatchers.any(UserPostDTO.class));
    }
}
