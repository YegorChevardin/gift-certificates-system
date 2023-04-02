# Certificate system

REST API for Gift Certificates system
(solving
this [task](https://github.com/mjc-school/MJC-School/blob/old/stage%20%233/java/module%20%232.%20REST%20API%20Basics/rest_api_basics_task.md))

### 1.1 Database set up
Go to the `database-structure` folder and run `Certificate-system.sql` in your mysql server. It will create schema and all important tables for application to work.
### 1.2 Build project

> - Run `./gradlew build` from the source folder.
>- Put generated `.war` file from `web/build/libs/` folder to `webapps` folder in your tomcat and then start it!