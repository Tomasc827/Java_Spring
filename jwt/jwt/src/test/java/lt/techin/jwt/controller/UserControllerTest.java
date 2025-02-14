package lt.techin.jwt.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lt.techin.jwt.dto.UserMapper;
import lt.techin.jwt.dto.UserRequestDTO;
import lt.techin.jwt.model.User;
import lt.techin.jwt.security.SecurityConfig;
import lt.techin.jwt.service.RoleService;
import lt.techin.jwt.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@Import(SecurityConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private RoleService roleService;


    @Test
    void addUser_whenUserAdd_thenReturnAnd201() throws Exception {
        UserRequestDTO userRequestDTO = new UserRequestDTO("something","something@email.com","Something9!");
        User user = UserMapper.userToEntity(userRequestDTO);

        BDDMockito.given(userService.addUser(ArgumentMatchers.any(UserRequestDTO.class))).willReturn(user);

        ObjectMapper objectMapper = new ObjectMapper();


        //when

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDTO)))

        //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("usernamo").value("something"));

        BDDMockito.verify(userService, Mockito.times(1)).addUser(ArgumentMatchers.any(UserRequestDTO.class));
    }
}
