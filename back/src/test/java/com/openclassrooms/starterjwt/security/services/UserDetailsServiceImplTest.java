package com.openclassrooms.starterjwt.security.services;

import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {
    @Mock
    private UserRepository userRepository;

    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        userDetailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    void loadUserByUsernameWhenUserExist() {
        User user= new User(10l,"yoga@yoga.com","username","username","yoga!123",false, LocalDateTime.now(),LocalDateTime.now());
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(user));
        assertThat(userDetailsService.loadUserByUsername("yoga@yoga.com").getUsername()).isEqualTo("yoga@yoga.com");
        verify(userRepository).findByEmail(any(String.class));
    }

    @Test
    void loadUserByUsernameWhenUserNotExist() {
        String username = "username";
        UsernameNotFoundException thrown = Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(username);
        }, "User Not Found with email: " + username);

        Assertions.assertEquals("User Not Found with email: username", thrown.getMessage());
    }
}
