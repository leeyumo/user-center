package com.leeyumo.userCenter.service.impl;

import com.leeyumo.common.exception.BusinessException;
import com.leeyumo.common.models.PageRequestWrapper;
import com.leeyumo.userCenter.domain.QUser;
import com.leeyumo.userCenter.domain.User;
import com.leeyumo.userCenter.domain.constants.UserCenterMsg;
import com.leeyumo.userCenter.domain.creator.UserCreator;
import com.leeyumo.userCenter.domain.dto.UserLoginDto;
import com.leeyumo.userCenter.domain.vo.UserVO;
import com.leeyumo.userCenter.repository.UserRepository;
import com.leeyumo.userCenter.service.UserService;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserCreator req){
        long userCount = jpaQueryFactory.selectFrom(QUser.user)
                .where(QUser.user.username.eq(req.getUsername()))
                .fetchCount();
        if (userCount>0){
            throw new BusinessException(UserCenterMsg.userAlreadyExist);
        }
        User user = new User();
        user.doCreate(req);
        user.setPassword(bCryptPasswordEncoder.encode(req.getPassword()));
        userRepository.save(user);
    }

    //登录时调用
    @Override
    public UserVO checkUserInfo(UserLoginDto dto){
        if (dto==null|| StringUtils.isBlank(dto.getUsername())|| StringUtils.isBlank(dto.getPassword())){
            throw new BusinessException(UserCenterMsg.userInfoError);
        }
        User user = jpaQueryFactory.selectFrom(QUser.user)
                .where(QUser.user.username.eq(dto.getUsername()))
                .fetchFirst();
        if (user==null||!bCryptPasswordEncoder.matches(dto.getPassword(),user.getPassword())){
            throw new BusinessException(UserCenterMsg.userInfoError);
        }
        return new UserVO(user);
    }

    @Override
    public Page<UserVO> getAllUsers(PageRequestWrapper wrapper){
        QueryResults<User> queryResults = jpaQueryFactory.selectFrom(QUser.user)
                .offset((wrapper.getPageNum()-1)*wrapper.getPageSize())
                .limit(wrapper.getPageSize())
                .fetchResults();
        return new PageImpl<>(
                queryResults.getResults().stream().map(UserVO::new).collect(Collectors.toList()),
                PageRequest.of((wrapper.getPageNum()-1),wrapper.getPageSize()),
                queryResults.getTotal()
        );
    }
}
