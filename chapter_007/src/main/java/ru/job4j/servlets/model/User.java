package ru.job4j.servlets.model;

import java.util.Date;

public class User {
    private int id;
    private String login;
    private String password;
    private Role role;
    private String email;
    private String country;
    private String region;
    private String city;
    private Date createDate;

    public User() {
        super();
    }

    public User(String login, String password, Role role, String email, String country, String region, String city) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.country = country;
        this.region = region;
        this.city = city;
        this.createDate = new Date();
    }

    public User(int id, String login, String password, Role role, String email, String country, String region, String city, java.sql.Date date) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.country = country;
        this.region = region;
        this.city = city;
        this.createDate = date;
    }

    public int getId() {
        return this.id;
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
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
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != user.role) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (country != null ? !country.equals(user.country) : user.country != null) return false;
        if (region != null ? !region.equals(user.region) : user.region != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        return createDate != null ? createDate.equals(user.createDate) : user.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
