package com.urijoel.curling.dto;

import com.urijoel.curling.model.Level;
import com.urijoel.curling.model.Role;

public class UserDTO {

    private String id;
    private String name;
    private String email;
    private Integer age;
    private String sex;
    private Level level;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(String id, String name, String email, Integer age, String sex, Level level, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.level = level;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}