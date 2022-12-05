package com.trungntm.rest.auth;

import com.github.dozermapper.core.Mapping;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCreationDTO implements Serializable {

    @NotNull
    @Length(min = 3)
    String username;

    @NotNull
    String password;

    Boolean enabled;

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    String middleName;

    LocalDate dateOfBirth;

    String phoneNumber;

    String address;

    String hometown;

    @NotNull
    @Email
    String email;

    String externalId;

    @Mapping("attributes")
    Map<String, Object> attributes;
}
