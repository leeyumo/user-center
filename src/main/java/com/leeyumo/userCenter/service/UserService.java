package com.leeyumo.userCenter.service;

import com.leeyumo.common.models.PageRequestWrapper;
import com.leeyumo.userCenter.domain.creator.UserCreator;
import com.leeyumo.userCenter.domain.dto.UserLoginDto;
import com.leeyumo.userCenter.domain.vo.UserVO;
import org.springframework.data.domain.Page;

public interface UserService {
    void save(UserCreator req);

    //登录时调用
    UserVO checkUserInfo(UserLoginDto dto);

    Page<UserVO> getAllUsers(PageRequestWrapper wrapper);
}
