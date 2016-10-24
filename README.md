## Dependencies
- JDK 1.8 or later
- Maven 3 or later
- MySQL 5.6 or later
- Apache Tomcat 9 or later
- GIT

## Set-up project
0) Clone the project
git clone https://github.com/OlegGasul/natekrank

1) Import maven project
Use any IDE and import __pom.xml__

2) Install __Lombok__ plugin to your IDE.
Lombok is library that provides way to prevent too much handmade like getters, setters, defining hasshcode, equals etc.
For Intellij Idea https://plugins.jetbrains.com/plugin/6317

3) Create database
Open MySQL Workbench. Create database and run script __/resources/db.sql__

4) Set-up properties
Open __application.properties__ and set the following:

__jdbc section__
url - database connection string
username - database user
password - database password

__mail section__
host - SMTP host
port - SMTP port
user - SMTP user
password - password for SMTP user
url - link on application that will be send in mails
subject - mail subject

## Running

## Running from Maven with embedded Tomcat
