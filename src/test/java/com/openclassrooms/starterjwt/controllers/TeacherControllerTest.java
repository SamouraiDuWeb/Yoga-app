package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.dto.TeacherDto;
import com.openclassrooms.starterjwt.mapper.TeacherMapper;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.services.TeacherService;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;
    @Mock
    private TeacherMapper teacherMapper;
    private Teacher teacher;
    private TeacherDto teacherDto;

    @BeforeEach
    void setUp() {
        teacher = new Teacher(1l,"username","username", LocalDateTime.now(),LocalDateTime.now());
        teacherDto = new TeacherDto(1l,"username","username", LocalDateTime.now(),LocalDateTime.now());
    }

    @Test
    void findById() {
        when(teacherService.findById(any(Long.class))).thenReturn(teacher);
        when(teacherMapper.toDto(any(Teacher.class))).thenReturn(teacherDto);
        TeacherController teacherController = new TeacherController(teacherService,teacherMapper);
        ResponseEntity<?> response = teacherController.findById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teacherDto,response.getBody());
        verify(teacherService).findById(any(Long.class));
        verify(teacherMapper).toDto(any(Teacher.class));
    }

    @Test
    void findAll() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        List<TeacherDto> teachersDto = new ArrayList<>();
        teachersDto.add(teacherDto);
        when(teacherService.findAll()).thenReturn(teachers);
        when(teacherMapper.toDto(teachers)).thenReturn(teachersDto);
        TeacherController teacherController = new TeacherController(teacherService, teacherMapper);
        ResponseEntity<?> response = teacherController.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teachersDto, response.getBody());
        verify(teacherService).findAll();
        verify(teacherMapper).toDto(teachers);
    }
}
