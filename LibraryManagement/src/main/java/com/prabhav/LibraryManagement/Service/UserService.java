package com.prabhav.LibraryManagement.Service;

import com.prabhav.LibraryManagement.request.UserDTO;
import com.prabhav.LibraryManagement.response.BaseResponse;

public interface UserService {

    BaseResponse registerAccount(UserDTO userDTO);
}
