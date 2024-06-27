package com.ecommerce.service.impl;


import com.ecommerce.dto.UserDTO;
import com.ecommerce.dto.auth.RegisterRequest;
import com.ecommerce.entity.RoleEntity;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.UserUpdateRequest;
import com.ecommerce.entity.OrderDetailEntity;
import com.ecommerce.entity.PurchaseEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.OrderDetailRepository;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.interfaces.IUserService;
import com.ecommerce.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String createUser(RegisterRequest request){
        Optional<UserEntity> findByEmail = userRepository.findByEmail(request.email());

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleRepository.findByrole(Role.USER));

        if (findByEmail.isEmpty()) {
            UserEntity user = UserEntity.builder()
                    .username(request.username())
                    .email(request.email())
                    .password(request.password())
                    .role(roles)
                    .build();
            userRepository.save(user);
            return user.getEmail();

        }else {throw new IllegalArgumentException("The email already exist in the database");}
    }

    @Override
    public List<UserDTO> getAllUsers(Pagination pagination){

        Pageable pageable = PageRequest.of(pagination.page(), pagination.size(), Sort.by("username").ascending());

        Page<UserEntity> pageUser = userRepository.findAll(pageable);

        List<UserEntity> allUsers = pageUser.getContent();

        if (allUsers.isEmpty()){ throw new ResourceNotFoundException("There are not users to show");
        }

        return allUsers.stream().map(Mappers::userToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUser(Long id){
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
    public UserDTO updateUser(Long id, UserUpdateRequest userUpdate){
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id '" + id + "' not found")
        );

        Mappers.updateUser(user, userUpdate);

        userRepository.save(user);

        return Mappers.userToUserDto(user);
    }

    @Override
    public UserDTO findByUsername(String username){
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with name '" + username + "' was not found")
        );

        return Mappers.userToUserDto(user);
    }

    @Override
    public UserDTO findByEmail(String email){
        UserEntity user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User with email '" + email + "' was not found")
        );

        return Mappers.userToUserDto(user);
    }
}
