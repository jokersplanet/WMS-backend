package com.wms.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-05-27 17:00
 */
@Data
public class UserLoginVO implements Serializable {
    private Integer uid;

    private String userName;

    private Integer age;

    private Integer sex;

    private String phone;

    private String role;

    private DepartmentVO departmentVO;

    private static final long serialVersionUID = 1L;
}
