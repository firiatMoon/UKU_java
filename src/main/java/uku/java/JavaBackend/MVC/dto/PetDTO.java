package uku.java.JavaBackend.MVC.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import uku.java.JavaBackend.MVC.models.Pet;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetDTO {

    @NotBlank(message = "Please enter your name.")
    @Size(max=30, message = "The name length must not exceed 30 characters.")
    private String name;

    @NotNull(message = "The value must not be empty.")
    @Positive(message = "The value must be positive.")
    private Long user;

    public PetDTO(String name, Long user) {
        this.name = name;
        this.user = user;
    }

    public PetDTO() {}

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
