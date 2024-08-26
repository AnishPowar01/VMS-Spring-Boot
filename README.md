
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

### Pending Features

1. **Add Dose to Particular User**
    - Functionality to add a new dose to a specific user.

2. **Give Dose After 120 Days**
    - Enforces a minimum gap of 120 days between doses before administering another dose.

3. **Delete User**
    - Ability to delete a user from the system.

4. **Same Dose Equality Check**
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
