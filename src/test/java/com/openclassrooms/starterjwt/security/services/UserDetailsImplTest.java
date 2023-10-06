package com.openclassrooms.starterjwt.security.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class UserDetailsImplTest {
    private UserDetailsImpl userDetailsImpl;

    @BeforeEach
    void init(){
        userDetailsImpl = new UserDetailsImpl(1l,"username","firstname","lastname",true,"password");
    }

    @Test
    void getAuthorities() {
        assertThat(userDetailsImpl.getAuthorities()).isInstanceOf(HashSet.class);
    }

    @Test
    void isAccountNonExpired() {
        assertThat(userDetailsImpl.isAccountNonExpired()).isTrue();
    }

    @Test
    void isAccountNonLocked() {
        assertThat(userDetailsImpl.isAccountNonLocked()).isTrue();
    }

    @Test
    void isCredentialsNonExpired() {
        assertThat(userDetailsImpl.isCredentialsNonExpired()).isTrue();
    }

    @Test
    void isEnabled() {
        assertThat(userDetailsImpl.isEnabled()).isTrue();
    }

    @Test
    void testEqualsWhenObjectIsEqualsToUserDetailsImpl() {
        assertThat(userDetailsImpl.equals(userDetailsImpl)).isTrue();
    }

    @Test
    void testEqualsWhenObjectIsEqualsToNull() {
        assertThat(userDetailsImpl.equals(null)).isFalse();
    }

    @Test
    void testEqualsWhenObjectIsNotEqualsToUserDetailsImpl() {
        UserDetailsImpl user = new UserDetailsImpl(2l,"username","firstname","lastname",true,"password");
        assertThat(userDetailsImpl.equals(user)).isFalse();
    }


    @Test
    void getId() {
        assertThat(userDetailsImpl.getId()).isEqualTo(1l);
    }

    @Test
    void getUsername() {
        assertThat(userDetailsImpl.getUsername()).isEqualTo("username");
    }

    @Test
    void getFirstName() {
        assertThat(userDetailsImpl.getFirstName()).isEqualTo("firstname");
    }

    @Test
    void getLastName() {
        assertThat(userDetailsImpl.getLastName()).isEqualTo("lastname");
    }

    @Test
    void getAdmin() {
        assertThat(userDetailsImpl.getAdmin()).isTrue();
    }

    @Test
    void getPassword() {
        assertThat(userDetailsImpl.getPassword()).isEqualTo("password");
    }

    @Test
    void builder() {
        assertThat(userDetailsImpl.builder()).isNotNull();
    }
}
