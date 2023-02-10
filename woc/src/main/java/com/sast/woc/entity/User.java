package com.sast.woc.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// Lombok 的注解：不需要我们写构造器和 Getter Setter 方法。
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private Integer role;
    private String token;
}


