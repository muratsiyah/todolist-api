package com.murat.siyah.todolistapi.application.handler;

import com.murat.siyah.todolistapi.infrastructure.repository.TodoRepository;
import org.junit.After;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class TodoQueryHandlerTests {

    @InjectMocks
    private TodoQueryHandler todoQueryHandler;

    @Mock
    private TodoRepository todoRepository;

    @After
    public void after() {
        verifyNoMoreInteractions(todoRepository);
    }

}