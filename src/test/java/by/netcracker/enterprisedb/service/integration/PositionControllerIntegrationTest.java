package by.netcracker.enterprisedb.service.integration;

import by.netcracker.enterprisedb.dto.model.PositionDTO;
import by.netcracker.enterprisedb.service.PositionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

/** @author Andrey Egorov */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PositionControllerIntegrationTest {

  private final String path = "/api/v1/position";

  @Autowired private MockMvc mockMvc;

  @MockBean private PositionService positionService;

  private final PositionDTO positionDTO = new PositionDTO("Java", 1500);

  @BeforeEach
  public void setUp() {
    PositionDTO position = new PositionDTO("Java", 1500);
    position.setId(1L);
    positionDTO.setId(1L);

    when(positionService.save(position)).thenReturn(position);
    when(positionService.update(position)).thenReturn(position);
    when(positionService.findById(1L)).thenReturn(position);
    when(positionService.getAll()).thenReturn(Collections.singletonList(position));
  }

  @WithMockUser(authorities = "ROLE_ADMIN")
  @Test
  public void test_AddPosition() throws Exception {
    mockMvc
        .perform(
            post(path + "/admin/add")
                .content(new ObjectMapper().writeValueAsString(positionDTO))
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.specialty", is("Java")))
        .andExpect(jsonPath("$.minSalary", is(1500)));
  }

  @WithMockUser(authorities = "ROLE_ADMIN")
  @Test
  public void test_UpdatePosition() throws Exception {
    mockMvc
        .perform(
            put(path + "/admin/update")
                .content(new ObjectMapper().writeValueAsString(positionDTO))
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.specialty", is("Java")))
        .andExpect(jsonPath("$.minSalary", is(1500)));
  }

  @Test
  public void test_GetById() throws Exception {
    mockMvc
        .perform(
            get(path + "/all/1")
                .content(new ObjectMapper().writeValueAsString(positionDTO))
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.specialty", is("Java")))
        .andExpect(jsonPath("$.minSalary", is(1500)));
  }

  @Test
  public void test_GetAll() throws Exception {
    mockMvc
        .perform(
            get(path + "/all")
                .content(
                    new ObjectMapper().writeValueAsString(Collections.singletonList(positionDTO)))
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().is(200))
        .andExpect(jsonPath("$", hasSize(1)));
  }

  @WithMockUser(authorities = "ROLE_ADMIN")
  @Test
  public void test_delete() throws Exception {
    mockMvc
        .perform(
            delete(path + "/admin/delete/1")
                .content(new ObjectMapper().writeValueAsString(positionDTO))
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().is(200));
  }
}
