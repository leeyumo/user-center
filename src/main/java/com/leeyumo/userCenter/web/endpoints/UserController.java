package com.leeyumo.userCenter.web.endpoints;

import com.leeyumo.common.models.JsonResult;
import com.leeyumo.common.models.PageRequestWrapper;
import com.leeyumo.userCenter.domain.creator.UserCreator;
import com.leeyumo.userCenter.domain.dto.UserLoginDto;
import com.leeyumo.userCenter.domain.vo.UserVO;
import com.leeyumo.userCenter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/user")
@Api(tags = "User")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "save", notes = "存储用户")
    @RequestMapping(value = "save",method = {RequestMethod.POST})
    public ResponseEntity<JsonResult<Boolean>> save(
            @RequestBody UserCreator creator){
        userService.save(creator);
        return ResponseEntity.ok(JsonResult.success(true));
    }

    @ApiOperation(value = "checkUserInfo", notes = "校验用户信息")
    @RequestMapping(value = "check_user_info",method = {RequestMethod.POST})
    public ResponseEntity<JsonResult<UserVO>> checkUserInfo(
            @RequestBody UserLoginDto dto){
        UserVO userVO = userService.checkUserInfo(dto);
        return ResponseEntity.ok(JsonResult.success(userVO));
    }

    @ApiOperation(value = "getAllUsers", notes = "校验用户信息")
    @RequestMapping(value = "get_all_users",method = {RequestMethod.POST})
    public ResponseEntity<JsonResult<Page<UserVO>>> getAllUsers(
            @RequestBody PageRequestWrapper dto){
        Page<UserVO> res = userService.getAllUsers(dto);
        return ResponseEntity.ok(JsonResult.success(res));
    }
}
