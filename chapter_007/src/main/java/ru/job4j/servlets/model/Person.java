package ru.job4j.servlets.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
<<<<<<< HEAD
     private String firstName;
     private String lastName;
     private String sex;
     private String description;
     private String country;
     private String region;
     private String city;
=======

     String firstName;
     String lastName;
     String sex;
     String description;

>>>>>>> origin/master


    public Person() {
        super();
    }
<<<<<<< HEAD

    public Person(String firstName, String lastName, String sex, String description, String country, String region, String city) {
=======
    public Person(String firstName, String lastName, String sex, String description) {
>>>>>>> origin/master
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.description = description;
<<<<<<< HEAD
        this.country = country;
        this.region = region;
        this.city = city;
    }

=======
    }
>>>>>>> origin/master
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

<<<<<<< HEAD
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

=======
>>>>>>> origin/master
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (sex != null ? !sex.equals(person.sex) : person.sex != null) return false;
<<<<<<< HEAD
        if (description != null ? !description.equals(person.description) : person.description != null) return false;
        if (country != null ? !country.equals(person.country) : person.country != null) return false;
        if (region != null ? !region.equals(person.region) : person.region != null) return false;
        return city != null ? city.equals(person.city) : person.city == null;
=======
        return description != null ? description.equals(person.description) : person.description == null;
>>>>>>> origin/master
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
<<<<<<< HEAD
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
=======
>>>>>>> origin/master
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", description='" + description + '\'' +
<<<<<<< HEAD
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
=======
>>>>>>> origin/master
                '}';
    }
}
