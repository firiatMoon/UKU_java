package uku.java.JavaBackend.MVC.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    @Null
    private Long id;

    @NotBlank
    @Size(max=30)
    private String name;

    @NotBlank
    @Email
    private String email;

    @Positive
    @NotNull(message = "Возраст обязателен")
    @Min(value = 18, message = "Минимальный возраст - 18 лет")
    private Integer age;

    @Null
    private List<PetDTO> pets;

    public UserDTO(Long id, String name, String email, Integer age, List<PetDTO> pets) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.pets = pets;
    }

    public UserDTO() {}

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

    public List<PetDTO> getPets() {
        return pets;
    }

    public void setPets(List<PetDTO> pets) {
        this.pets = pets;
    }
}
