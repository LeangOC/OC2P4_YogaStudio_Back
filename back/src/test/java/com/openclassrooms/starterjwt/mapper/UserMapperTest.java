package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.UserDto;
import com.openclassrooms.starterjwt.models.User;

import org.junit.jupiter.api.Test;

import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserMapper mapper =
            Mappers.getMapper(UserMapper.class);

    @Test
    void shouldMapUserToDto() {

        LocalDateTime now =
                LocalDateTime.now();

        User user =
                User.builder()
                        .id(1L)
                        .email("test@test.com")
                        .firstName("John")
                        .lastName("Doe")
                        .password("password")
                        .admin(false)
                        .createdAt(now)
                        .updatedAt(now)
                        .build();

        UserDto dto =
                mapper.toDto(user);

        assertNotNull(dto);

        assertEquals(
                user.getId(),
                dto.getId()
        );

        assertEquals(
                user.getEmail(),
                dto.getEmail()
        );

        assertEquals(
                user.getFirstName(),
                dto.getFirstName()
        );

        assertEquals(
                user.getLastName(),
                dto.getLastName()
        );

        assertEquals(
                user.isAdmin(),
                dto.isAdmin()
        );

        assertEquals(
                user.getCreatedAt(),
                dto.getCreatedAt()
        );

        assertEquals(
                user.getUpdatedAt(),
                dto.getUpdatedAt()
        );
    }

    @Test
    void shouldMapDtoToUser() {

        LocalDateTime now =
                LocalDateTime.now();

        UserDto dto =
                new UserDto();

        dto.setId(1L);
        dto.setEmail("test@test.com");
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setPassword("password");
        dto.setAdmin(false);
        dto.setCreatedAt(now);
        dto.setUpdatedAt(now);

        User user =
                mapper.toEntity(dto);

        assertNotNull(user);

        assertEquals(
                dto.getId(),
                user.getId()
        );

        assertEquals(
                dto.getEmail(),
                user.getEmail()
        );

        assertEquals(
                dto.getFirstName(),
                user.getFirstName()
        );

        assertEquals(
                dto.getLastName(),
                user.getLastName()
        );

        assertEquals(
                dto.isAdmin(),
                user.isAdmin()
        );

        assertEquals(
                dto.getCreatedAt(),
                user.getCreatedAt()
        );

        assertEquals(
                dto.getUpdatedAt(),
                user.getUpdatedAt()
        );
    }

    @Test
    void shouldMapUserListToDtoList() {

        User user1 =
                User.builder()
                        .id(1L)
                        .email("user1@test.com")
                        .firstName("John")
                        .lastName("Doe")
                        .password("password")
                        .admin(false)
                        .build();

        User user2 =
                User.builder()
                        .id(2L)
                        .email("user2@test.com")
                        .firstName("Jane")
                        .lastName("Smith")
                        .password("password")
                        .admin(false)
                        .build();

        List<User> users =
                new ArrayList<>();

        users.add(user1);
        users.add(user2);

        List<UserDto> dtos =
                mapper.toDto(users);

        assertNotNull(dtos);

        assertEquals(
                2,
                dtos.size()
        );

        assertEquals(
                "user1@test.com",
                dtos.get(0).getEmail()
        );

        assertEquals(
                "user2@test.com",
                dtos.get(1).getEmail()
        );
    }

    @Test
    void shouldMapDtoListToUserList() {

        UserDto dto1 =
                new UserDto();

        dto1.setId(1L);
        dto1.setEmail("dto1@test.com");
        dto1.setFirstName("John");
        dto1.setLastName("Doe");
        dto1.setPassword("password");
        dto1.setAdmin(false);

        UserDto dto2 =
                new UserDto();

        dto2.setId(2L);
        dto2.setEmail("dto2@test.com");
        dto2.setFirstName("Jane");
        dto2.setLastName("Smith");
        dto2.setPassword("password");
        dto2.setAdmin(false);

        List<UserDto> dtos =
                new ArrayList<>();

        dtos.add(dto1);
        dtos.add(dto2);

        List<User> users =
                mapper.toEntity(dtos);

        assertNotNull(users);

        assertEquals(
                2,
                users.size()
        );

        assertEquals(
                "dto1@test.com",
                users.get(0).getEmail()
        );

        assertEquals(
                "dto2@test.com",
                users.get(1).getEmail()
        );
    }

    @Test
    void shouldReturnNullWhenUserIsNull() {

        UserDto dto =
                mapper.toDto((User) null);

        assertNull(dto);
    }

    @Test
    void shouldReturnNullWhenDtoIsNull() {

        User user =
                mapper.toEntity((UserDto) null);

        assertNull(user);
    }

    @Test
    void shouldReturnEmptyListWhenUserListEmpty() {

        List<User> users =
                new ArrayList<>();

        List<UserDto> dtos =
                mapper.toDto(users);

        assertNotNull(dtos);

        assertTrue(
                dtos.isEmpty()
        );
    }

    @Test
    void shouldReturnEmptyListWhenDtoListEmpty() {

        List<UserDto> dtos =
                new ArrayList<>();

        List<User> users =
                mapper.toEntity(dtos);

        assertNotNull(users);

        assertTrue(
                users.isEmpty()
        );
    }
}