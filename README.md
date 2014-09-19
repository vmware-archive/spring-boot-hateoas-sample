spring-boot-hateoas-sample
==========================

This sample uses Spring Boot, Spring Data JPA, and Spring Data Rest to expose SpringOne session and presenter data over a HATEOAS compliant API using the [HAL Specification](http://stateless.co/hal_specification.html). The code is based on the [Accessing JPA Data with REST](http://spring.io/guides/gs/accessing-data-rest/) getting started guide. The data was scraped off the [SpringOne schedule pages](https://2014.event.springone2gx.com/schedule/2014-09-09) using [this javascript function on the browser](src/main/resources/public/springone-scraper.js). The [JSON data](src/main/resources/springone-data.json) is then loaded into an H2 embedded database on applicaiton startup.

The app is deployed to PWS: http://spring-boot-hateoas.cfapps.io/ Two entites are exposed, Presenters and Sessions, with a bidirectional many-to-many relationship. 
