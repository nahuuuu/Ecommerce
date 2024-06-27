package com.ecommerce.service.interfaces;

import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.dto.UserUpdateRequest;
import com.ecommerce.dto.auth.RegisterRequest;
import com.ecommerce.entity.UserEntity;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAllUsers(Pagination pagination);

    String createUser(RegisterRequest request);

    UserDTO getUser(Long id);

    String deleteUser(Long id);

    UserDTO updateUser(Long id, UserUpdateRequest user);

    UserDTO findByUsername(String username);

    UserDTO findByEmail(String email);

}
