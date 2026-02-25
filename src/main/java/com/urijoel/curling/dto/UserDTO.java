/**
 *
 * @author Uri
 */
package com.urijoel.curling.dto;

public class UserDTO {

    private String id;
    private String name;
    private String email;
    private Integer age;
    private String sex;
    private String level;
    private String role;

    public UserDTO() {
    }

    // constructores con los datos basicos que se envian a angular
    public UserDTO(String id, String name, String email, Integer age, String sex, String level, String role) {
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}