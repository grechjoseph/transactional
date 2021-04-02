package com.jg.transactional.api;

import com.jg.transactional.model.Person;
import com.jg.transactional.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private UUID defaultId;

    private final PersonRepository personRepository;

    /**
     * Without Transactional, save and delete will clash on the same thread, since by the time delete is called, save
     * is not yet complete (Event based) and the table would still be locked.
     *
     * Issue seems to not be encountered if findByFirstName is used.
     */
    @GetMapping
    @Transactional
    public Person tryMe() {
        log.info("Starting...");
        final Person person = new Person();
        person.setId(UUID.fromString("96a3bab9-65c6-4233-aa31-82213bdc0586"));
        person.setFirstName("Joseph");
        person.setLastName("Grech");
        personRepository.save(person);
        // personRepository.findByFirstName("Joseph");
        personRepository.deleteById(defaultId);
        personRepository.deleteByFirstName("Joseph");
        log.info("End.");
        return person;
    }

    @PostConstruct
    public void defaultPerson() {
        final Person person = new Person();
        person.setFirstName("First");
        person.setFirstName("First");
        defaultId = person.getId();
        personRepository.save(person);
    }

}
