package com.thoughtworks.todolist;

import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.model.TodoLabel;
import com.thoughtworks.todolist.repository.TodoLabelRepository;
import com.thoughtworks.todolist.service.TodoLabelService;
import com.thoughtworks.todolist.service.TodoService;
import com.thoughtworks.todolist.todoNotFoundException.TodoLabelNotFoundException;
import com.thoughtworks.todolist.todoNotFoundException.TodoNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoLabelServiceTest {
    @InjectMocks
    private TodoLabelService todoLabelService;
    @Mock
    private TodoLabelRepository todoLabelRepository;

    @Test
    public void should_return_all_when_getAll_given_allTodo_label() {
        //given
        List<TodoLabel> labelList = new ArrayList<>();
        List<TodoLabel> expected= Arrays.asList(new TodoLabel("Sports","red"));

        //when
        when(todoLabelRepository.findAll()).thenReturn(expected);

        //then

        List <TodoLabel> actual = todoLabelService.getAll();
        assertEquals(1, actual.size());

    }
    @Test
    public void should_return_one_todo_label_when_getOneTodoLabel_given_todoLabel_id() throws TodoLabelNotFoundException {
        //given
        TodoLabel expected = new TodoLabel("1","Sports", "red");

        //when
        when(todoLabelRepository.findById("1")).thenReturn(Optional.of(expected));

        //then
        TodoLabel actual = todoLabelService.getOneTodoLabel("1");
        assertEquals(expected,actual);

    }
    @Test
    public void should_return_null_when_getOneTodoLabel_given_invalid_todoLabel_id() throws TodoLabelNotFoundException {
        //given;
        TodoLabel expected = new TodoLabel("1","Sports", "red");

        //when
        when(todoLabelRepository.findById("2")).thenReturn(Optional.empty());
        Exception exception = assertThrows(TodoLabelNotFoundException.class, () -> todoLabelService.getOneTodoLabel("2"));

        //then
        assertEquals("Todo item label is not found",exception.getMessage());

    }
    @Test
    public void should_return_new_todo_label_when_createTodo_given_new_todo_label() {
        //given

        TodoLabel expected = new TodoLabel("1","Sports", "red");

        //when
        when(todoLabelRepository.save(expected)).thenReturn(expected);

        //then
        TodoLabel actual= todoLabelService.createTodoLabel(expected);
        assertEquals(expected,actual);

    }
    @Test
    public void should_return_removed_Todo_Label_when_deleteTodoLabel_given_valid_TodoLabelID()  {
        //given


        //when


        //then
        Exception exception = assertThrows(TodoLabelNotFoundException.class, () -> todoLabelService.deleteTodoLabel("123"));
        assertEquals("Todo item label is not found", exception.getMessage());

    }
}
