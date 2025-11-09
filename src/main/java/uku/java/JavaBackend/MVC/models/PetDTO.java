package uku.java.JavaBackend.MVC.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetDTO {

    @Null
    private Long id;

    @NotBlank
    @Size(max=30)
    private String name;

    @NotNull
    @Positive
    private Long user;

    public PetDTO(Long id, String name, Long user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public PetDTO() {}

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
