# Spring Boot Application using Spring Web, Spring Data & H2 Database #

This repository contains a simplified application made for the purpose of assessment. The task is to implement a REST API that can manage basic cryptocurrency data. The API must support adding, reading, updating and deleting records & the records must be stored in an inmemory database. The API must also support paging and sorting for the endpoint /api/currencies.

## Tools and technologies used

- JDK
- Spring Boot (with Spring Web MVC, Spring Data JPA)
- H2 Database
- Hibernate JPA implementation
- Maven
- IntelliJ IDEA

## Setup Spring Boot project

Spring Boot has options to test REST APIs & on the other hand Spring Data makes a number of standard repositories available and I take advantage of them to create this Currency application using spring-web, spring-data and H2 database components togther and achive the goal mentioned above. The application.properties file which shows the configuration of Spring Boot with the components is avaialabe in src/main/resources folder.


## Data Model

Our data model is Currency with four fields: ticker, name, number_of_coins and market_cap. Hence, I first define Currency class [model/Currency.java] in the model package. Some anotation features of the class are:

- @Entity annotation which indicates that the class is a persistent Java class.

- @Table annotation which provides the table that maps this entity.

- @Column annotation which used to define the column in database that maps annotated field.

- @Id annotation is for the primary key.

- @GeneratedValue annotation is used to define generation strategy for the primary key.

## Repository Interface & Spring Rest APIs Controller

I create a repository to interact with Currencies from the database. CurrencyRepository [repository/CurrencyRepository.java] is an interface that extends JpaRepository for CRUD methods. And hence, it uses JpaRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById(). In addition to these methods, I define custom finder methods [findByTicker() & findByName()].

This interface will be autowired in CurencyController. The implementation is plugged in by Spring Data JPA automatically &currencies table will be generated in the H2 Database (in memory).


I also create a controller that provides APIs for creating, retrieving, updating, deleting and finding Currency. The CurrencyController [controller/CurrencyController.java] is a RestController which has request mapping methods for RESTful requests such as: getAllCurrencies, createCurrency, updateCurrency, deleteCurrency, findByTicker, findByName and so on.
I also use the following anotation for the class.

- @RestController annotation which used to define a controller and to indicate that the return value of the methods should be be bound to the web response body.

- @RequestMapping("/api") which declares that all APIs’ url in the controller will start with /api.
and finally

- @Autowired to inject CurrencyRepository bean to local variable.

## Test and run the application

Nowadays unit testing is so important in Software Development, and Spring Framework also provides @DataJpaTest annotation to make writing test for JPA Repository more simpler. I also apply @DataJpaTest in this Spring Boot Project with TestEntityManager. [JPAUnitTest.java] is the main test class used for testing JPA and annotated with @DataJpaTest.
@DataJpaTest is the annotation that Spring supports for a JPA test that focuses only on JPA components. It will disable full auto-configuration and then, apply only enable configuration relevant to JPA tests.

By default, tests annotated with @DataJpaTest are transactional and roll back at the end of each test. In-memory embedded database (like H2 database in this example) generally works well for tests, it is fast and does not require any installation.

The interface CurrencyRepository which extends JpaRepository for CRUD methods and custom finder methods will be autowired in this JPAUnitTest class.

After running JPAUnitTest class, the result shows that all 9 tests [that I tried] passed the test.

[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 7.901 s -


Finally, runing the Spring Boot application with command: mvn spring-boot:run & then checking H2 database in the H2 console with url: http://localhost:8080//h2-console/, we can see that currencies table automatically generated in the database. The table will show some availabe recods too. This is because the in-memory database [data.sql] is filled with a number of predefined records before the application started. The H2 database Currencies table looks like this:

![alt text](https://github.com/Esubalew-github/PharmaPartnersAssessment/blob/main/Schermafbeelding%202021-03-06%20113936.png)


Applying some CRUD operations with help of Postman, some of the results are shown below:

Retrieve all Currencies:


![alt text](https://github.com/Esubalew-github/PharmaPartnersAssessment/blob/main/Schermafbeelding%202021-03-06%20155349.png)


Retrieve a Currency by Name:

 
![alt text](https://github.com/Esubalew-github/PharmaPartnersAssessment/blob/main/Schermafbeelding%202021-03-06%20155444.png)


Retrieve a Currency by Id:


![alt text](https://github.com/Esubalew-github/PharmaPartnersAssessment/blob/main/Schermafbeelding%202021-03-06%20155537.png)

Delete a Currency by Id:

![alt text](https://github.com/Esubalew-github/PharmaPartnersAssessment/blob/main/Schermafbeelding%202021-03-06%20155831.png)

All Currencies after delete:

![alt text](https://github.com/Esubalew-github/PharmaPartnersAssessment/blob/main/Schermafbeelding%202021-03-06%20155942.png)

This Spring Boot Application will also provide APIs for Pagination and Sorting by Ascending or Descending order. One of the result is shown below:


![alt text](https://github.com/Esubalew-github/PharmaPartnersAssessment/blob/main/Schermafbeelding%202021-03-06%20114606.png)


## Web Frontend

I also implement a web frontend [using Angular] that can use the REST API and the respository is avilable at the github link: https://github.com/Esubalew-github/Angular11Crud.git

