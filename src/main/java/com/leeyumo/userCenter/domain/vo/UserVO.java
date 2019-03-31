package com.leeyumo.userCenter.domain.vo;

import com.google.common.collect.Lists;
import com.leeyumo.userCenter.domain.Role;
import com.leeyumo.userCenter.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ApiModel
public class UserVO extends BaseUserVO{

    private List<String> roleCodeList = Lists.newArrayList();

    public UserVO(User source){
        super(source);
        setRoleCodeList(source.getRoleList().stream()
                .map(Role::getRoleCode)
                .collect(Collectors.toList())
        );
    }
}
