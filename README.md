# CRUD
Basic CRUD operations for Institutes with Encryption

# Institute Management System

This project implements a backend service for an Institute Management System using Java, Spring Boot, and PostgreSQL. It provides RESTful APIs for managing institutes, including registration, modification, and retrieval of institute information. Additionally, it includes a Docker solution for easy deployment.

## Functional Requirements

### Institute Registration

- Implemented an endpoint for institute registration.
- Included fields for institute name, location, contact information, and any additional details. Additional columns of created by, created date, updated date, updated by added using Spring Auditor.
- Ensured that institute data is securely stored by adding AES Encryption.

### Institute Modification

- Implemented an endpoint for modifying institute information based on the institute's ID.
- Allowed changes to fields such as institute name, location, and contact information.

### Institute Information Retrieval

- Implemented an endpoint to retrieve information about a specific institute based on the institute's ID.
- Implemented an endpoint to retrieve information of all institutes.

### Institute Deletion

- Implemented an endpoint to soft Delete any institute based on institute id.

### Testing

- JUNITs and integration tests with the help of test containers added.

### Security

- Spring Security utilized for security implementation.


### Technology Stack

- Java and the Spring Boot framework for development.
- PostgreSQL for storing institute information.

### Docker Integration

- Docker solution for the Spring Boot application added.
- Application can be easily deployed using Docker.


## Usage

1. Clone the repository:

git clone https://github.com/Nipun0499/CRUD.git


2. Configure PostgreSQL database in `application.yml:

url: jdbc:postgresql://localhost:5434/Institute
username: yourusername
password: yourpassword


3. Build the project:

mvn clean install


4. Run the Spring Boot application:

mvn spring-boot:run



5. Access the API endpoints using a tool like Postman or curl.

## API Endpoints

<img width="959" alt="image" src="https://github.com/Nipun0499/CRUD/assets/59878125/262a09bd-76b2-4703-b6ac-34e58fcfe455">

  
### Docker Deployment

- Build Docker image:

docker build -t institute-management .


- Run Docker container:

docker run -p 8080:8080 institute-management


## Contributors

- Nipun Jain <er.nipun.jain.official@gmail.com>

Feel free to contribute to this project by submitting issues or pull requests.




