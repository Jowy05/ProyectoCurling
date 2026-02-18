/**
 *
 * @author Uri
 */
package com.urijoel.curling.dto;

public class RegisterRequestDTO {
    private String name;
    private String email;
    private String password;
    private Integer age;
    private String sex;
    private String level;

    public RegisterRequestDTO() {}
    
    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    
    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }
    
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
}