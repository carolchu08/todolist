package com.thoughtworks.todolist;

import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.model.TodoLabel;
import com.thoughtworks.todolist.repository.TodoRepository;
import com.thoughtworks.todolist.service.TodoService;
import com.thoughtworks.todolist.todoNotFoundException.TodoNotFoundException;
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
    public void should_return_one_todo_when_getOneTodo_given_todo() throws TodoNotFoundException {
        //given
        List<TodoLabel> labelList = new ArrayList<>();
        Todo expected = new Todo("1","playing sport", false, labelList);

        //when
        when(todoRepository.findById("1")).thenReturn(Optional.of(expected));

        //then
        Todo actual = todoService.getOneTodo("1");
        assertEquals(expected,actual);

    }
    @Test
    public void should_return_null_when_getOneTodo_given_invalid_todo_id() throws TodoNotFoundException {
        //given
        List<TodoLabel> labelList = new ArrayList<>();
        Todo expectedTodo = new Todo("1","playing sport", false, labelList);

        //when
        when(todoRepository.findById("2")).thenReturn(Optional.empty());
        Exception exception = assertThrows(TodoNotFoundException.class, () -> todoService.getOneTodo("2"));

        //then
        assertEquals("Todo item is not found",exception.getMessage());

    }
    @Test
    public void should_return_new_todo_when_createTodo_given_new_todo() {
        //given
        List<TodoLabel> labelList = new ArrayList<>();
        Todo expected = new Todo("playing sport",false,labelList);
                
        //when
        when(todoRepository.save(expected)).thenReturn(expected);
        
        //then
        Todo actual= todoService.createTodo(expected);
        assertEquals(expected,actual);
        
    }
    @Test
     public void should_return_removed_Todo_when_deleteTodo_given_valid_TodoID()  {
        //given
        
                
        //when
        
        
        //then
        Exception exception = assertThrows(TodoNotFoundException.class, () -> todoService.deleteTodo("123"));
        assertEquals("Todo item is not found", exception.getMessage());
        
    }
    @Test
    public void should_return_update_todo_when_updateTodo_given_todo_id() throws TodoNotFoundException {
        //given
        List<TodoLabel> labelList = new ArrayList<>();
        Todo originalTodo = new Todo("1","playing sport", false,labelList);
        Todo updateTodo = new Todo("1","playing sport", true,labelList);
        Todo expected = new Todo("1","playing sport", true,labelList);

        //when
        when(todoRepository.findById("1")).thenReturn(Optional.of(originalTodo));
        todoService.updateTodo("1", updateTodo);
        final ArgumentCaptor<Todo> TodoArgumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoRepository, times(1)).save(TodoArgumentCaptor.capture());
        //then
        final Todo actual = TodoArgumentCaptor.getValue();
        assertNotNull(actual);
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getDone(), actual.getDone());

    }

    


}
