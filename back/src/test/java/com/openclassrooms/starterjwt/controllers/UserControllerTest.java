package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.dto.UserDto;
import com.openclassrooms.starterjwt.mapper.UserMapper;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserControllerTest {
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserService userService;
    @Mock
    private UserDetails userDetails;
    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        LocalDateTime date = LocalDateTime.now();
        user = new User(10L,"yoga@yoga.com","lastname","firstname","yoga!123",true, date,date);
        userDto = new UserDto(10l,"yoga@yoga.com","lastname","firstname",true,"yaoga!123", date,date);
    }

    @Test
    void findById() {
        Long testUserId = 10L;
        when(userService.findById(testUserId)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);
        UserController userController = new UserController(userService, userMapper);

        ResponseEntity<?> response = userController.findById(String.valueOf(testUserId));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());

        verify(userService).findById(testUserId);
        verify(userMapper).toDto(user);
    }


}
