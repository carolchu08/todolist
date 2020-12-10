package com.thoughtworks.todolist;

import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.model.TodoLabel;
import com.thoughtworks.todolist.repository.TodoRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    TodoRepository todoRepository;
    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    public void should_return_all_todo_when_get_ALl_given_all_todo() throws Exception {
        //given
        List<TodoLabel> labelList = new ArrayList<>();
        Todo expected = new Todo("playing sport",false,labelList);
        todoRepository.save(expected);
        //when

        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].text").value("playing sport"))
                .andExpect(jsonPath("$[0].done").value(false));
        //then

    }
    @Test
    public void should_return_todo_when_create_todo_given_todo() throws Exception {
        //given
        String todoAsJson = " {\n" +
                "        \"text\": \"playing sport\",\n" +
                "        \"done\": false,\n" +
                "        \"todoLabels\": null\n" +
                "    }";

        //when
        //then
        mockMvc.perform(post("/todos")
                .contentType(APPLICATION_JSON)
                .content(todoAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.text").value("playing sport"))
                .andExpect(jsonPath("$.done").value(false));

        List<Todo> todoList = todoRepository.findAll();
        assertEquals(1, todoList.size());


    }
    @Test
    public void should_return_exception_when_getOneTodo_given_a_invalid_todo_id() throws Exception {
        //given
        List<TodoLabel> labelList = new ArrayList<>();
        Todo expected = new Todo("playing sport",false,labelList);
        Todo addedTodo = this.todoRepository.save(expected);
        this.todoRepository.deleteAll();

        //when
        //then
        this.mockMvc.perform(get("/todos/" + addedTodo.getId()))
                .andExpect(status().isNotFound());

    }

    @Test
    void should_return_removed_todo_when_deleteTodo_given_valid_ID() throws Exception {
        //given
        List<TodoLabel> labelList = new ArrayList<>();
        Todo expected = new Todo("playing sport",false,labelList);
        todoRepository.save(expected);
        //when
        mockMvc.perform(delete("/todos/" + expected.getId()))
                .andExpect(status().isOk());
        //then

    }
    @Test
    public void should_return_update_todo_when_updateTodo_given_valid_id() throws Exception {
        //given
        List<TodoLabel> labelList = new ArrayList<>();
        Todo todo = new Todo("playing sport", false,labelList);
        Todo original = todoRepository.save(todo);

        String updateAsJson = " {\n" +
                "        \"text\": \"swimming\",\n" +
                "        \"done\": false,\n" +
                "        \"todoLabels\": null\n" +
                "    }";
        Todo expected= new Todo("swimming",false,labelList);
        //when


        //then
        mockMvc
                .perform(
                        put("/todos/" + original.getId())
                                .contentType(APPLICATION_JSON)
                                .content(updateAsJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.text").value("swimming"))
                .andExpect(jsonPath("$.done").value(false));

    }

}
