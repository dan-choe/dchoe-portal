# dchoe-portal

Builds API with Spring Boot and MongoDB.

### Features

- Create and manage users
- Login
- Create and manage blog posts

### Simple usage

- POST /auth/login
- GET, POST, PUT, DELETE /users/
- GET /users/{id}
- GET, POST, PUT, DELETE /notes/
- GET /notes/{id}
- GET /notes/page?page=N&page-size=M

### Updates:

2019.08.10
- User password is hashed by using Bcrypt


### Todo:
- Set JWT token to authenticate and authorize a user access.
- 