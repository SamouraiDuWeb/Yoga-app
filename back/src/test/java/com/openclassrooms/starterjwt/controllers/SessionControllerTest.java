package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.mapper.SessionMapper;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.services.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class SessionControllerTest {
    @Mock
    private SessionMapper sessionMapper ;
    @Mock
    private SessionService sessionService;
    private Session session;
    private SessionDto sessionDto;

    @BeforeEach
    void init(){
        User user = new User(10l,"yoga@yoga.com","username","username","yoga!123",false, LocalDateTime.now(),LocalDateTime.now());
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Teacher teacher = new Teacher(1l,"teachername","teachername", LocalDateTime.now(),LocalDateTime.now());
        session = new Session(1l,"morning",new Date(23,07,05),"cours yoga",teacher,userList,LocalDateTime.now(),LocalDateTime.now());
        List<Long> usersIdList = new ArrayList<>();
        usersIdList.add(10l);
        sessionDto = new SessionDto(1l,"morning",new Date(23,07,05),1L,"cours yoga", usersIdList,LocalDateTime.now(),LocalDateTime.now());
    }


    @Test
    void findById() throws Exception {
        when(sessionService.getById(any(Long.class))).thenReturn(session);
        when(sessionMapper.toDto(any(Session.class))).thenReturn(sessionDto);

        SessionController sessionController = new SessionController(sessionService, sessionMapper);
        ResponseEntity<?> response = sessionController.findById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sessionDto, response.getBody());

        verify(sessionService).getById(any(Long.class));
        verify(sessionMapper).toDto(any(Session.class));
    }

    @Test
    void findAll() {
        List<Session> sessions = Collections.singletonList(session);
        List<SessionDto> sessionDtos = Collections.singletonList(sessionDto);
        when(sessionService.findAll()).thenReturn(sessions);
        when(sessionMapper.toDto(sessions)).thenReturn(sessionDtos);

        SessionController sessionController = new SessionController(sessionService, sessionMapper);
        ResponseEntity<?> response = sessionController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sessionDtos, response.getBody());
        verify(sessionService).findAll();
        verify(sessionMapper).toDto(sessions);
    }

    @Test
    void create() {
        when(sessionMapper.toEntity(sessionDto)).thenReturn(session);
        when(sessionService.create(any(Session.class))).thenReturn(session);
        when(sessionMapper.toDto(any(Session.class))).thenReturn(sessionDto);

        SessionController controller = new SessionController(sessionService, sessionMapper);
        assertEquals(sessionDto, controller.create(sessionDto).getBody());
        assertEquals(HttpStatus.OK, controller.create(sessionDto).getStatusCode());

        verify(sessionMapper, times(2)).toEntity(any(SessionDto.class));
        verify(sessionService ,times(2)).create(any(Session.class));
        verify(sessionMapper, times(2)).toDto(any(Session.class));
    }
    @Test
    void update() {
        when(sessionMapper.toEntity(sessionDto)).thenReturn(session);
        when(sessionService.update(any(Long.class), any(Session.class))).thenReturn(session);
        when(sessionMapper.toDto(any(Session.class))).thenReturn(sessionDto);

        SessionController controller = new SessionController(sessionService, sessionMapper);
        ResponseEntity<?> response = controller.update("1", sessionDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sessionDto, response.getBody());

        verify(sessionMapper).toEntity(any(SessionDto.class));
        verify(sessionService).update(any(Long.class), any(Session.class));
        verify(sessionMapper).toDto(any(Session.class));
    }

    @Test
    void save() {
        when(sessionService.getById(any(Long.class))).thenReturn(session);

        SessionController controller = new SessionController(sessionService, sessionMapper);
        ResponseEntity<?> response = controller.save("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(sessionService).getById(any(Long.class));
        verify(sessionService).delete(any(Long.class));
    }

    @Test
    void participate() {
        SessionController controller = new SessionController(sessionService, sessionMapper);
        ResponseEntity<?> response = controller.participate("1", "10");

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(sessionService).participate(any(Long.class), any(Long.class));
    }

    @Test
    void noLongerParticipate() {
        SessionController controller = new SessionController(sessionService, sessionMapper);
        ResponseEntity<?> response = controller.noLongerParticipate("1", "10");

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(sessionService).noLongerParticipate(any(Long.class), any(Long.class));
    }

}