/**
 *
 * @author jowyd
 */
package com.urijoel.curling.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;

    @Indexed(unique = true)
    private String email;

    private String password;
    private Integer age;
    private String sex;

    private String level; 
    private String role; 

    public User() {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}