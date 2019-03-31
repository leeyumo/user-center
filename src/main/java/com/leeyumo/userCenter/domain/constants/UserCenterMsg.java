package com.leeyumo.userCenter.domain.constants;

import com.leeyumo.common.constants.BaseEnum;

import java.util.Optional;

public enum UserCenterMsg implements BaseEnum {
    userAlreadyExist(140001,"用户已存在"),
    userInfoError(140002,"用户名或密码错误")
    ;
    private Integer code;
    private String msg;

    UserCenterMsg(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.msg;
    }

    public static Optional<UserCenterMsg> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(UserCenterMsg.class,code));
    }
}
