package org.xy.Bo;

import lombok.Data;

@Data
public class LoginBo {
    private String phone;
    private String password;
    private String code;//验证码
}
