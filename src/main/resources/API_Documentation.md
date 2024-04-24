# API Documentation

## Overview
This document provides an overview of the available API endpoints in the Book Library Management System.

## Base URL
The base URL for all API requests is `http://localhost:8009/`.

## _Endpoints for Book_


### GET/books/bookId
Description: Retrieve book in the library by bookId.
Example: `GET http://localhost:8009/books/bookId`

### GET/books/title
Description: Retrieve book in the library by book title or matched alphabet.
Example: `GET http://localhost:8009/books/title/BookName`

### GET /books
Description: Retrieve all books in the library.
Example: `GET http://localhost:8009/books`

### GET/books/author/authorName
Description: Retrieve all book of particular author by author name.
Example: `GET http://localhost:8009/books/author/authorName`

### GET/books/rented
Description: Retrieve All rented books in the library.
Example: `GET http://localhost:8009/books/rented`

### GET/books/non-rented
Description: Retrieve All non-rented book in the library.
Example: `GET http://localhost:8009/books/available`

### POST /books
Description: Add a new book to the library.
Example: `POST http://localhost:8009/books`

Request Body:
```json
{
  "title": "Example Book",
  "author": "John Doe",
  "isbn": "978-1-2345678900",
  "publicationYear": 2022
}
```
### PUT/books/bookId
Description:  update existing book to the library.
Example: `PUT http://localhost:8009/books/bookId`

### DELETE/books/bookId
Description:  delete existing book to the library.
Example: `DELETE http://localhost:8009/books/bookId`


## _Endpoints for Author_

### GET /author/authorId
Description: Retrieve author in the library.
Example: `GET http://localhost:8009/author/authorId`

### GET /author
Description: Retrieve all author in the library.
Example: `GET http://localhost:8009/author`

### POST /author
Description: Add a new author to the library.
Example: `POST http://localhost:8009/author`

### PUT/author/authorId
Description:  update existing author to the library.
Example: `PUT http://localhost:8009/author/authorId`

### DELETE/author/authorId
Description:  delete existing book to the library.
Example: `DELETE http://localhost:8009/author/authorId`

## _Endpoints for Rental_

### GET /rentals/authorId
Description: Retrieve specific rental in the library.
Example: `GET http://localhost:8009/rentals/rentalId`

### GET /author
Description: Retrieve all rental in the library.
Example: `GET http://localhost:8009/rentals/rentalId`

### POST /rentals/rent/bookId/renterName
Description: Rent a book to renter in the library
Example: `POST http://localhost:8009/rentals/rent/bookId/renterName`

### PUT/rentals/rentalId
Description:  update existing rental to the library.
Example: `PUT http://localhost:8009/rentals/rentalId`

### POST /rentals/return/bookId
Description: Return a book to the library.
Example: `POST http://localhost:8009/rentals/return/bookId`

### DELETE/rental/rentalId
Description:  delete existing rental to the library.
Example: `DELETE http://localhost:8009/rentals/rentalId`