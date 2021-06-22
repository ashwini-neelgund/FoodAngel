package com.foodangel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    String gender;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "alt_phone_number")
    String altNumber;

    @Column(name = "email_address")
    String emailAddress;

    String password;

    @Column(name = "user_type")
    String userType;

    @Lob
    @Column(name = "image_byte", columnDefinition = "MEDIUMBLOB")
    private byte[] imageByte;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    List<Address> address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "seeker_id")
    List<Request> requests;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "angel_id")
    List<Request> requestsAssignedToAngel;
}
