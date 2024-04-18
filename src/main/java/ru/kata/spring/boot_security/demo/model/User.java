package ru.kata.spring.boot_security.demo.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import ru.kata.spring.boot_security.demo.model.Role;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "pass")
    private String pass;

    @Transient
    private String passwordConfirm;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "role")
    private Set<Role> roles;

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPasswordConfirm() {return passwordConfirm;}

    public void setPasswordConfirm(String passwordConfirm) {this.passwordConfirm = passwordConfirm;}

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String name, String pass, String lastname, int age, String gender, Set<Role> roles) {
        this.name = name;
        this.pass = pass;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Пользователь: " +
                "№: " + id +
                ", Имя: " + name +
                ", Фамилия: " + lastname +
                ", Возраст: " + age +
                ", Пол: " + gender;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
