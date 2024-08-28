
# Vaccine Management System (VMS) - Spring Boot

## Overview

The Vaccine Management System (VMS) is a Spring Boot application designed to manage user registrations and track vaccination status. This project provides functionalities for user management, vaccination tracking, and status updates.

## Features

### Functionalities

1. **Register User**
    - Allows you to add new users to the system.

2. **Show Users and Vaccines**
    - Displays a list of users along with their vaccination details.

3. **Update Status**
    - Automatically updates a user's vaccination status to "fully vaccinated" if they have received more than 2 doses.

4. **Add Dose to Particular User**
    - Functionality to add a new dose to a specific user.

5. **Give Dose After 120 Days**
    - Enforces a minimum gap of 120 days between doses before administering another dose.

6. **Delete User**
    - Ability to delete a user from the system.

7. **Same Dose Equality Check**
    - Ensures that the vaccine dose type administered is the same as the previous dose type.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven or Gradle (depending on the build tool used)
- Git

### Clone the Repository

Clone the repository to your local machine using the following command:

```bash
git clone https://github.com/AnishPowar01/VMS-Spring-Boot.git
````


# DataBase Configuration


## 1. Create Sequence for `users` Table

Create a sequence to generate unique IDs for the `users` table:

```sql
CREATE SEQUENCE user_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
    
```

## 2. Create `users` Table

Create the `users` table with the following schema:

```sql
CREATE TABLE users (
    user_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    phone_number VARCHAR2(15) UNIQUE NOT NULL,
    address VARCHAR2(255),
    date_of_birth DATE
);
```

## 3. Insert Data into `users` Table

Insert a new user into the `users` table:

```sql
INSERT INTO users (user_id, name, email, phone_number, address, date_of_birth)
VALUES (user_seq.NEXTVAL, 'John Doe', 'john.doe@example.com', '1234567890', '123 Main St', TO_DATE('1990-01-01', 'YYYY-MM-DD'));
```

## 4. Create Sequence for `vaccine_dose` Table

Create a sequence to generate unique IDs for the `vaccine_dose` table:

```sql
CREATE SEQUENCE vaccine_dose_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
```

## 5. Create `vaccine_dose` Table

Create the `vaccine_dose` table with the following schema:

```sql
CREATE TABLE vaccine_dose (
    dose_id NUMBER PRIMARY KEY,
    user_id NUMBER NOT NULL,
    dose_number NUMBER NOT NULL,
    vaccination_date DATE NOT NULL,
    vaccine_type VARCHAR2(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

## 6. Insert Data into `vaccine_dose` Table

Insert a new vaccination record for an existing user:

```sql
INSERT INTO vaccine_dose (dose_id, user_id, dose_number, vaccination_date, vaccine_type)
VALUES (vaccine_dose_seq.NEXTVAL, 1, 1, TO_DATE('2024-08-01', 'YYYY-MM-DD'), 'VaccineTypeA');
```

Insert another vaccination record for the same user:

```sql
INSERT INTO vaccine_dose (dose_id, user_id, dose_number, vaccination_date, vaccine_type)
VALUES (vaccine_dose_seq.NEXTVAL, 1, 2, TO_DATE('2024-08-26', 'YYYY-MM-DD'), 'VaccineTypeB');
```
