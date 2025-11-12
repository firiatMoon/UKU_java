package uku.java.JavaBackend.MVC.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pet {

    @Null
    private Long id;

    @NotBlank(message = "Please enter your name.")
    @Size(max=30, message = "The name length must not exceed 30 characters.")
    private String name;

    @NotNull(message = "The value must not be empty.")
    @Positive(message = "The value must be positive.")
    private Long user;

    public Pet(Long id, String name, Long user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public Pet() {}

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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
