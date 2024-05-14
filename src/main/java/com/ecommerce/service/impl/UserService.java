package com.ecommerce.service.impl;

import com.ecommerce.mapper.Mappers;
import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.UserDto;
import com.ecommerce.dto.UserUpdateRequest;
import com.ecommerce.entity.OrderDetailEntity;
import com.ecommerce.entity.PurchaseEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.OrderDetailRepository;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.interfaces.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements iUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<UserDto> getAllUsers(Pagination pagination){

        Pageable pageable = PageRequest.of(pagination.page(), pagination.size(), Sort.by("username").ascending());

        Page<UserEntity> pageUser = userRepository.findAll(pageable);

        List<UserEntity> allUsers = pageUser.getContent();

        if (allUsers.isEmpty()){ throw new ResourceNotFoundException("There are not users to show");
        }

        return allUsers.stream().map(Mappers::userToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id '" + "' not found")
        );

        return Mappers.userToUserDto(user);
    }

    @Override
    public String deleteUser(Long id){
        UserEntity findUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id '" + "' not found")
        );

        List<PurchaseEntity> findPurchase = purchaseRepository.findByUsername(findUser.getUsername());

        List<OrderDetailEntity> findOrderDetails = findPurchase.stream()
                .flatMap(purchaseEntity -> orderDetailRepository.findByPurchase(purchaseEntity).stream()).toList();

        findOrderDetails.stream().map(OrderDetailEntity::getId).forEach(orderDetailRepository::deleteById);

        findPurchase.stream().map(PurchaseEntity::getId).forEach(purchaseRepository::deleteById);

        userRepository.deleteById(id);

        return "The user has been successfully deleted";
    }

    @Override
    public UserDto updateUser(Long id, UserUpdateRequest userUpdate){
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id '" + id + "' not found")
        );

        Mappers.updateUser(user, userUpdate);

        userRepository.save(user);

        return Mappers.userToUserDto(user);
    }

    @Override
    public UserDto findByUsername(String username){
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with name '" + username + "' was not found")
        );

        return Mappers.userToUserDto(user);
    }

    @Override
    public UserDto findByEmail(String email){
        UserEntity user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User with email '" + email + "' was not found")
        );

        return Mappers.userToUserDto(user);
    }
}
