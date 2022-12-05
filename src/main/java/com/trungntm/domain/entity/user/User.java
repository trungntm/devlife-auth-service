package com.trungntm.domain.entity.user;

import com.trungntm.domain.attribute.AbstractAttributeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractAttributeEntity {

    String username;

    Boolean enabled;

    String firstName;

    String lastName;

    String middleName;

    LocalDate dateOfBirth;

    String phoneNumber;

    String address;

    String hometown;

    String email;

    String externalId;

}
