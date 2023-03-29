package com.olmez.myamango.model;

import lombok.Data;

@Data
public class PasswordWrapper {
    private String username;
    private String rawPassword;
}
