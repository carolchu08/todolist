package com.thoughtworks.todolist;

import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.model.TodoLabel;
import com.thoughtworks.todolist.repository.TodoRepository;
import com.thoughtworks.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTests {
    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Test
    public void should_return_all_when_getAll_given_allTodo() {
        //given
        List<TodoLabel> labelList = new ArrayList<>();
        List<Todo> expected= Arrays.asList(new Todo("playing sport",false,labelList));

        //when
        when(todoRepository.findAll()).thenReturn(expected);

        //then

        List <Todo> actual = todoService.getAll();
        assertEquals(1, actual.size());

    }
    @Test
    public void should_return_one_todo_when_getOneTodo_given_todo() {
        //given
        List<TodoLabel> labelList = new ArrayList<>();
        Todo expected = new Todo("1","playing sport", false, labelList);

        //when
        when(todoRepository.findById("1")).thenReturn(Optional.of(expected));

        //then
        Todo actual = todoService.getOneTodo("1");
        assertEquals(expected,actual);

    }


}
