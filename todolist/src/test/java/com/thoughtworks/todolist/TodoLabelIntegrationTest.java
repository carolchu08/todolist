package com.thoughtworks.todolist;

import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.model.TodoLabel;
import com.thoughtworks.todolist.repository.TodoLabelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoLabelIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    TodoLabelRepository todoLabelRepository;
    @AfterEach
    void tearDown() {
        todoLabelRepository.deleteAll();
    }
    @Test
    public void should_return_all_todoLabel_when_get_ALl_given_all_todoLabel() throws Exception {
        //given

        TodoLabel expected = new TodoLabel("sport","red");
        todoLabelRepository.save(expected);
        //when

        mockMvc.perform(get("/todoLabel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].labelID").isString())
                .andExpect(jsonPath("$[0].labelName").value("sport"))
                .andExpect(jsonPath("$[0].color").value("red"));
        //then

    }
    @Test
    public void should_return_todoLabel_when_create_todo_given_todoLabel() throws Exception {
        //given
        String todoAsJson = " {\n" +
                "        \"labelName\": \"sport\",\n" +
                "        \"color\": \"red\"\n" +
                "    }";

        //when
        //then
        mockMvc.perform(post("/todoLabel")
                .contentType(APPLICATION_JSON)
                .content(todoAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.labelID").isString())
                .andExpect(jsonPath("$.labelName").value("sport"))
                .andExpect(jsonPath("$.color").value("red"));

        List<TodoLabel> todoLabels = todoLabelRepository.findAll();
        assertEquals(1, todoLabels.size());


    }
    @Test
    void should_return_removed_todoLabel_when_deleteTodo_given_valid_ID() throws Exception {
        //given
        TodoLabel expected = new TodoLabel("sport","red");
        todoLabelRepository.save(expected);
        //when
        mockMvc.perform(delete("/todoLabel/" + expected.getLabelID()))
                .andExpect(status().isOk());
        //then

    }

}
