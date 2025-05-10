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

### Get Category by ID

- This retrieves a category by its ID from the database.
- [GET] http://localhost:8080/api/v1/categories/{id}

### Create category

- This creates a new category in the database.
- [POST] http://localhost:8080/api/v1/categories

```json
{
    "name": "Action"
}
```

### Delete Category

- This deletes a category by its ID from the database.
- [DELETE] http://localhost:8080/api/v1/categories/{id}

### Update Category

- This updates a category by its ID in the database.
- [PUT] http://localhost:8080/api/v1/categories/{id}

```json
{
    "name": "Action"
}
```

### Patch Category

- This partially updates a category by its ID in the database.
- [PATCH] http://localhost:8080/api/v1/categories/{id}

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

### Get Movies By Title

- This retrieves movies by their title from the database.
- [POST] http://localhost:8080/api/v1/movies/search/title

```json
{
    "title": "Test movie"
}
```

### Get Movies By Category Name

- This retrieves movies by their category name from the database.
- [POST] http://localhost:8080/api/v1/movies/search/categoryName

```json
{
    "name": "Action"
}
```

### Get Movies By Category Id

- This retrieves movies by their category id from the database.
- [POST] http://localhost:8080/api/v1/movies/search/categoryId

```json
{
    "id": 1
}
```

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

### Delete A Movie

- This deletes a movie by its ID from the database.
- [DELETE] http://localhost:8080/api/v1/movies/{id}

### Update A Movie

- This updates a movie by its ID in the database.
- [PUT] http://localhost:8080/api/v1/movies/{id}

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

### Patch A Movie

- This partially updates a movie by its ID in the database.
- [PATCH] http://localhost:8080/api/v1/movies/{id}

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

## Comments

### Search Comments By User Id

- This retrieves comments by their user ID from the database.
- [POST] http://localhost:8080/api/v1/comments/search/userId

```json
{
    "id": 1
}
```

### Search Comments By Movie Id

- This retrieves comments by their movie ID from the database.
- [POST] http://localhost:8080/api/v1/comments/search/movieId

```json
{
    "id": 1
}
```

### Get Comment by Id

- This retrieves a comment by its ID from the database.
- [GET] http://localhost:8080/api/v1/comments/{id}

### Create A New Comment

- This creates a new comment in the database.
- [POST] http://localhost:8080/api/v1/comments

```json
{
    "movieId": 1,
    "userId": 1,
    "comment": "This is a test comment"
}
```

### Delete A Comment

- This deletes a comment by its ID from the database.
- [DELETE] http://localhost:8080/api/v1/comments/{id}

### Update A Comment

- This updates a comment by its ID in the database.
- [PUT] http://localhost:8080/api/v1/comments/{id}

```json
{
    "movieId": 1,
    "userId": 1,
    "comment": "This is a test comment"
}
```

### Patch A Comment

- This partially updates a comment by its ID in the database.
- [PATCH] http://localhost:8080/api/v1/comments/{id}

```json
{
    "movieId": 1,
    "userId": 1,
    "comment": "This is a test comment"
}
```
