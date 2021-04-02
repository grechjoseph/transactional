package com.jg.transactional.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Person {

    @Id
    private UUID id = UUID.randomUUID();
    private String firstName;
    private String lastName;

}
