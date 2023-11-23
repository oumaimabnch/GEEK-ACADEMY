package com.gdev.geekacademybackend.services.payload;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeDTO {
    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}
