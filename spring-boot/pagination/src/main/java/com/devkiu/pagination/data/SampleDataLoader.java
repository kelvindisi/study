package com.devkiu.pagination.data;

import com.devkiu.pagination.model.Address;
import com.devkiu.pagination.model.Person;
import com.devkiu.pagination.repository.PersonRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(SampleDataLoader.class);

    private final PersonRepository personRepository;
    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading sample data...");

        List<Person> people = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new Person(

                        null,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.phoneNumber().phoneNumber(),
                        new Address(
                                null,
                                faker.address().fullAddress(),
                                faker.address().city(),
                                faker.address().state(),
                                faker.address().zipCode()
                        )
                )).collect(Collectors.toList());

        personRepository.saveAll(people);
    }
}
