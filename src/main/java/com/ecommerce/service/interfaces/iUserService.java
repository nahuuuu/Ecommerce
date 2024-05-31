package com.ecommerce.service.interfaces;

import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.UserUpdateRequest;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers(Pagination pagination);

    UserDto getUser(Long id);

    String deleteUser(Long id);

    UserDto updateUser(Long id, UserUpdateRequest user);

    UserDto findByUsername(String username);

    UserDto findByEmail(String email);

}
