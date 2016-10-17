# Registration and Login Example with Spring MVC 4, Spring Security, Spring Data JPA, XML Configuration, Maven, JSP, and MySQL.

## Guide
https://hellokoding.com/registration-and-login-example-with-spring-xml-configuration-maven-jsp-and-mysql/

## Prerequisites
- JDK 1.8 or later
- Maven 3 or later
- MySQL 5.6 or later

## Stack
- Spring MVC
- Spring Security
- Spring Data JPA
- Maven
- JSP
- MySQL

## Run
```mvn jetty:run```

## Frontend build

Frontend toolset is nodejs based. Install [nodejs >= 6.x](https://nodejs.org/) to proceed.

To install toolset (in project directory):

```sh
npm install 
```

Note that you don't need to install `grunt-cli` globally.

### Development
To start in development mode (it's faster and more lightweight). 

```sh
npm start
```

In order to deploy static without recompiling whole Java app, create file `grunt.config.js` in project root and make it look like so:

```js
module.exports = {
    // Change path accordingly to your setup
    deployPath: 'C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\natekrank\\resources',
    // you can also override any value from grunt config here 
};
```

If there's `tomcatDir` value in config, `deployPath` will be derived automatically and you will receive little helper `grunt copy:war`. 

This can also require root/Admin rights to write to specified directory (or at least same as tomcat has).

### Production

To build for production (it's longer, but artifact size is smaller):

```sh
npm run build
```
