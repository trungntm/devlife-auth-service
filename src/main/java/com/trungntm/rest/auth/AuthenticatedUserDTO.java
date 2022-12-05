package com.trungntm.rest.auth;

import com.github.dozermapper.core.Mapping;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticatedUserDTO implements Serializable {

    Long id;

    String username;

    boolean enabled;

    String firstName;

    String lastName;

    String middleName;

    LocalDate dateOfBirth;

    String phoneNumber;

    String address;

    String hometown;

    String email;

    String externalId;

    @Mapping("attributes")
    Map<String, Object> attributes;

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }
}
