package com.example.javaspringbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

  private static final Logger LOG = LoggerFactory.getLogger(PersonItemProcessor.class);

  @Override
  public Person process(Person item) throws Exception {
    String firstName = item.getFirstName().toUpperCase();
    String lastName = item.getLastName().toUpperCase();
    Person transformedPerson = new Person(firstName, lastName);
    LOG.info("Converting (" + item + ") into (" + transformedPerson + ")");
    return transformedPerson;
  }
}
