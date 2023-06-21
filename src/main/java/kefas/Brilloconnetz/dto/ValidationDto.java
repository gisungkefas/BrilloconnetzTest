package kefas.Brilloconnetz.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ValidationDto {

    private String username;

    private String email;

    private String password;

    private LocalDate dateOfBirth;
}
