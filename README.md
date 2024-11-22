# Timperio Customer Relationship Management (CRM)
A web application for Timperio to manage and analyse their customer data, as well as manage marketing features for their customers.

## Setup
### Prerequisites:
- [Java](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) v21
- [Node.js](https://nodejs.org/en/) v20.15.0 (_or above_)
- A running [MySQL](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/) server
  - or alternatively, [WAMP](https://wampserver.aviatechno.net/)
  - or alternatively, [MAMP](https://www.mamp.info/en/windows/)
- A Google account setup with an [app password](https://support.google.com/mail/answer/185833?hl=en)
- .env file in the `backend` directory to store MySQL credentials. Here are the default values for WAMP users (_replace the values in `<>` to with your own credentials_):
    ```
    MYSQL_HOST=localhost
    MYSQL_PORT=3306
    MYSQL_DB=oilwhere_test
    MYSQL_USERNAME=root
    MYSQL_PASSWORD=
    SMTP_HOST=smtp.gmail.com
    SMTP_PORT=587
    SMTP_USERNAME=<your Gmail address>
    SMTP_PASSWORD=<your app password>
    ```
    MAMP users will have MYSQL_PASSWORD=root

### Running the app:
1. Package the backend Spring Boot application.

    `~/oilwhere/backend$ source package.sh`

    For Windows users, execute the `package.bat` file or run it in cmd.exe

1. Run the backend Spring Boot application via Java jar. It will be on http://localhost:8080 by default.

    `~/oilwhere/backend$ source run.sh`

    For Windows users, execute the `run.bat` file or run it in cmd.exe

1. Install dependencies in the `frontend` directory.

    `~/oilwhere/frontend$ npm install`

1. Run the frontend server. It will be on http://localhost:3000 by default.

    `~/oilwhere/frontend$ npm run start`

### Cleaning up:
- To clean up the compiled content for the backend, use the maven wrapper in the backend directory:

    `~/oilwhere/backend$ ./mvnw clean`

- To clean up the built content for the frontend, remove the `node_modules` directory:

    `~/oilwhere/frontend$ rm -r node_modules`
