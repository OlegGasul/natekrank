## Dependencies
### Backend
- JDK 1.8 or later
- Maven 3 or later
- MySQL 5.6 or later
- Apache Tomcat 9 or later
- GIT
### Frontend
- NodeJS 6.x or later
- npm

## Set-up project
0) Clone the project  
__git clone__ https://github.com/OlegGasul/natekrank

1) Import maven project  
Use any IDE and __import pom.xml__

2) Install __Lombok__ plugin to your IDE.  
Lombok is library that provides way to prevent too much handmade like getters, setters, defining hasshcode, equals etc.  
For Intellij Idea https://plugins.jetbrains.com/plugin/6317

3) Create database  
Open MySQL Workbench. Create database and run script __/resources/db.sql__

4) Set-up properties  
Rename __application.default.properties__ to __application.properties__.  
Open __application.properties__ and set the following:

__jdbc section__  
__url__ - database connection string  
__username__ - database user  
__password__ - database password  

__mail section__  
__host__ - SMTP host  
__port__ - SMTP port  
__user__ - SMTP user  
__password__ - password for SMTP user  
__url__ - link on application that will be send in mails  
__subject__ - mail subject  


## Running from Maven with embedded Tomcat

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
