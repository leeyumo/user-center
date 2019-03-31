package com.leeyumo.userCenter.domain;


import com.google.common.collect.Lists;
import com.leeyumo.common.models.BaseEntity;
import com.leeyumo.tool.code.generator.creator.GenerateCreator;
import com.leeyumo.tool.code.generator.creator.GenerateCreatorIgnore;
import com.leeyumo.tool.code.generator.updater.GenerateUpdater;
import com.leeyumo.tool.code.generator.updater.GenerateUpdaterIgnore;
import com.leeyumo.tool.code.generator.util.Description;
import com.leeyumo.tool.code.generator.vo.GenerateVO;
import com.leeyumo.tool.code.generator.vo.GenerateVOIgnore;
import com.leeyumo.userCenter.domain.creator.UserCreator;
import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Data
@Table(name = "tb_user")
@Entity
@QueryEntity
@GenerateCreator
@GenerateUpdater
@GenerateVO
public class User extends BaseEntity {

    @Column(name = "username")
    @Description("用户登录名")
    private String username;

    @Column(name = "password")
    @Description("用户密码")
    @GenerateVOIgnore
    @GenerateUpdaterIgnore
    private String password;

    @Column(name = "nickname")
    @Description("用户昵称")
    private String nickname;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    @GenerateCreatorIgnore
    @GenerateUpdaterIgnore
    @GenerateVOIgnore
    private List<Role> roleList = Lists.newArrayList();

    public void doCreate(UserCreator creator){
        creator.accept(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(nickname, user.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, nickname);
    }
}
