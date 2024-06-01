package com.ecommerce.service;

import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.dto.UserUpdateRequest;
import com.ecommerce.entity.OrderDetailEntity;
import com.ecommerce.entity.PurchaseEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.OrderDetailRepository;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @Mock
    private Pagination pagination;

    @Mock
    private Page<UserEntity> page;



    @Test
    @DisplayName("getAllUser_shouldReturnAListOfUser_whenCalled")
    public void GetAllUserTest(){

        // Config of pagination mock to set page and size
        when(pagination.page()).thenReturn(0);
        when(pagination.size()).thenReturn(10);

        /* Configure the repository mock to return the page mock when findAll method is
         * called*/
        when(userRepository.findAll(any(Pageable.class))).thenReturn(page);

        // Config page mock to return a list
        when(page.getContent()).thenReturn(Collections.singletonList(new UserEntity()));


        // Calling the method under test
        List<UserDTO> result = userService.getAllUsers(pagination);

        // Verify that the result is not empty
        assertFalse(result.isEmpty());

        // Verify that the findAll method of the repository was called with the correct parameters
        verify(userRepository).findAll(PageRequest.of(0, 10, Sort.by("username").ascending()));

    }

    @Test
    @DisplayName("getAllUser_shouldThrowResourceNotFoundException_whenNoUsers")
    public void getAllUserFailTest(){

        // Config of pagination mock to set page and size
        when(pagination.page()).thenReturn(0);
        when(pagination.size()).thenReturn(10);

        /* Configure the repository mock to return the page mock when findAll method is
         * called*/
        when(userRepository.findAll(any(Pageable.class))).thenReturn(page);

        /* Configure the page mock to return an empty list when the
        getContent method is called */
        when(page.getContent()).thenReturn(Collections.emptyList());

        /* Call the method being tested and verify that the expected exceptions is thrown */
        Assertions.assertThrows(ResourceNotFoundException.class, () ->{
            userService.getAllUsers(pagination);
        });
    }


    @Test
    @DisplayName("getUser_shouldReturnUserDTO_whenUserExists")
    void getUserTest(){

        // Arrange
        Long userId = 1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // Act
        UserDTO result = userService.getUser(userId);

        // Assert
        Assertions.assertNotNull(result, "UserDTO is NULL");

        Assertions.assertEquals(userId, result.id());

    }

    @Test
    @DisplayName("getUser_shouldThrowResourceNotFoundException_whenUserNotFound")
    void getUserFailTest(){

        // Arrange
        Long userId = 2L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                userService.getUser(userId));
    }

    @Test
    @DisplayName("deleteUser_shouldReturnString_whenUserIsDeleted")
    void deleteUserTest(){

        // Arrange
        Long userId = 1L;
        String username = "example";

        UserEntity user = new UserEntity();

        user.setId(userId);
        user.setUsername(username);

        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setId(1L);

        OrderDetailEntity orderDetail = new OrderDetailEntity();
        orderDetail.setId(1L);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        when(purchaseRepository.findByUsername(user.getUsername()))
                .thenReturn(Collections.singletonList(purchase));

        when(orderDetailRepository.findByPurchase(purchase)).thenReturn(Collections.singletonList(orderDetail));


        // Act

        userService.deleteUser(userId);

        // Assert

        verify(orderDetailRepository, times(1)).deleteById(orderDetail.getId());

        verify(purchaseRepository, times(1)).deleteById(purchase.getId());

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    @DisplayName("deleteUser_shouldThrowResourceNotFoundException_whenUserNotFound_or_purchaseNotFound_or_orderDetailNotFound")
    public void deleteUserFailTest(){

        // Arrange

        Long idUser = 1L;
        UserEntity user = new UserEntity();
        user.setId(idUser);

        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setId(1L);

        OrderDetailEntity orderDetail = new OrderDetailEntity();
        orderDetail.setId(1L);

        when(userRepository.findById(idUser)).thenReturn(Optional.empty());

        // Act and Assert


        assertThrows(ResourceNotFoundException.class, () ->
                userService.deleteUser(idUser));

        verify(orderDetailRepository, never()).deleteById(any());
        verify(purchaseRepository, never()).deleteById(any());




    }

    @Test
    @DisplayName("updateUser_shouldReturnUserDTO_whenUserUpdated")
    public void updateUserTest(){

        // Arrange
        Long userId = 1L;
        UserEntity existingUser = new UserEntity();

        existingUser.setId(userId);
        existingUser.setUsername("oldUsername");
        existingUser.setEmail("oldEmail");

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("newUsername", "newEmail");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // Act

        UserDTO updateUserDTO = userService.updateUser(userId ,userUpdateRequest);

        // Assert

        verify(userRepository, times(1)).save(existingUser);
        assertEquals(userUpdateRequest.username(), existingUser.getUsername());
        assertEquals(userUpdateRequest.email(), existingUser.getEmail());

        assertEquals(existingUser.getId(), updateUserDTO.id());
        assertEquals(userUpdateRequest.username(), updateUserDTO.username());
        assertEquals(userUpdateRequest.email(), updateUserDTO.email());

    }



}
