package com.leeyumo.userCenter.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class UserQueryDto {
    private String username;
}
