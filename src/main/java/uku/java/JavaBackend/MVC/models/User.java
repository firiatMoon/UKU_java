package uku.java.JavaBackend.MVC.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import uku.java.JavaCore.StreamAPI.Customer;
import uku.java.JavaCore.StreamAPI.Order;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Null
    private Long id;

    @NotBlank(message = "Please enter your name.")
    @Size(max=30, message = "The name length must not exceed 30 characters.")
    private String name;

    @NotBlank(message = "Please enter your email address.")
    @Email(message = "Please enter a valid email address.")
    private String email;

    @Positive(message = "The value must be positive.")
    @NotNull(message = "Please enter your age.")
    @Min(value = 18, message = "Minimum age: 18 years.")
    private Integer age;

    @Null
    private List<Pet> pets;

    public User(Long id, String name, String email, Integer age, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.pets = pets;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}
