package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void init(){
        userService = new UserService(userRepository);
    }

    @Test
    void delete() {
        userService.delete(any(Long.class));
        verify(userRepository).deleteById(any(Long.class));
    }

    @Test
    void findByIdWhenUserIsNotNull() {
        User user= new User(10l,"yoga@yoga.com","username","username","yoga!123",false, LocalDateTime.now(),LocalDateTime.now());
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        assertThat(userService.findById(10l)).isEqualTo(user);
        verify(userRepository).findById(any(Long.class));

    }

    @Test
    void findByIdWhenUsertNull() {
        assertThat(userService.findById(any(Long.class))).isNull();
        verify(userRepository).findById(any(Long.class));

    }
}
