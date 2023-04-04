package com.example.javaspringbatch;

public class Person {
  private String lastName;
  private String firstName;

  public Person() {}

  public Person(String firstName, String lastName) {
    this.lastName = lastName;
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Person setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public Person setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  @Override
  public String toString() {
    return "Person{" + "lastName='" + lastName + '\'' + ", firstName='" + firstName + '\'' + '}';
  }
}
