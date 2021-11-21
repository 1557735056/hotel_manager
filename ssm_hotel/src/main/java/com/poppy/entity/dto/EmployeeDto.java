package com.poppy.entity.dto;

/**
 * @Author poppy
 * @Date 2021/11/2 16:38
 * @Version 1.0
 */
public class EmployeeDto {
    private String loginName;
    private String loginPwd;

    public EmployeeDto() {
    }

    public EmployeeDto(String loginName, String loginPwd) {
        this.loginName = loginName;
        this.loginPwd = loginPwd;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                '}';
    }
}
