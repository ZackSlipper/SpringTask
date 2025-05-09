# API Examples

## Authentication

### Registration

- [POST] http://localhost:8080/api/v1/auth/signup

```json
{
    "password": "password12345",
    "email": "test@test.com",
    "role": ["user", "admin"],
    "username": "admin"
}
```

### Login

- [POST] http://localhost:8080/api/v1/auth/signin
- Copy the token from the response and use it as the **bearer token** in all further requests.

```json
{
    "password": "password12345",
    "username": "admin"
}
```

## Categories

### Get All Categories

- This retrieves all movie categories from the database.
- [GET] http://localhost:8080/api/v1/categories

### Create category

- This creates a new category in the database.
- [POST] http://localhost:8080/api/v1/categories

```json
{
	"name": "Action"
}
```

## Movies

### Get All Movies

- This retrieves all movies from the database.
- [GET] http://localhost:8080/api/v1/movies

### Get Movie by ID
- This retrieves a movie by its ID from the database.
- [GET] http://localhost:8080/api/v1/movies/{id}
- Replace `{id}` with the actual movie ID.

### Create A New Movie

- This creates a new movie in the database.
- [POST] http://localhost:8080/api/v1/movies

```json
{
    "title": "Test movie",
    "description": "This is a test movie",
    "thumbnail": "https://example",
    "categoryId": 1,
    "releaseYear": 2005,
    "imdbRating": 5.5
}
```

