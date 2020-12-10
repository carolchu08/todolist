package com.thoughtworks.todolist;

import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.model.TodoLabel;
import com.thoughtworks.todolist.repository.TodoLabelRepository;
import com.thoughtworks.todolist.service.TodoLabelService;
import com.thoughtworks.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        List<TodoLabel> expected= Arrays.asList(new TodoLabel("Sports"));

        //when
        when(todoLabelRepository.findAll()).thenReturn(expected);

        //then

        List <TodoLabel> actual = todoLabelService.getAll();
        assertEquals(1, actual.size());

    }
}
