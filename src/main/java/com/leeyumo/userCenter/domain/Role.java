package com.leeyumo.userCenter.domain;

import com.leeyumo.common.models.BaseEntity;
import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "tb_role")
@Entity
@QueryEntity
public class Role extends BaseEntity {
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_code")
    private String roleCode;


}
