package exercise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
// BEGIN
@Setter
@Getter
public class GuestCreateDTO {
    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+[0-9]{11,13}$")
    private String phoneNumber;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4}$")
    private String clubCard;

    @NotNull
    @Future
    private LocalDate cardValidUntil;

}
// END
