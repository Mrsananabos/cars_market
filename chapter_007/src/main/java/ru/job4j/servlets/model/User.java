package ru.job4j.servlets.model;

import java.util.Date;

public class User {

    private int id;
    private String login;
    private String role;
    private String email;
    private String password;
    private String address;
    private Date createDate;

    public User(int id, String login, String role, String email, String password, String address, Date date) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.email = email;
        this.password = password;
        this.address = address;
        this.createDate = date;
    }

    public User(String login, String role, String email, String password, String address) {
        this.login = login;
        this.role = role;
        this.email = email;
        this.password = password;
        this.address = address;
        this.createDate = new Date();
    }

    public static void main(String[] args) {
        System.out.println(new Date());
    }

    public int getId() {
        return this.id;
    }

    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return this.address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void updateCreateDate() {
        this.createDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        return createDate != null ? createDate.equals(user.createDate) : user.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", createDate=" + createDate + '\'' +
                '}';
    }
}
