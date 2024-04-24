# API Documentation

## Overview
This document provides an overview of the available API endpoints in the Book Library Management System.

## Base URL
The base URL for all API requests is `http://localhost:8009/`.

## Endpoints

### GET /books
Description: Retrieve all books in the library.
Example: `GET http://localhost:8009/books`

### GET/books/bookId
Description: Retrieve book in the library by bookId.
Example: `GET http://localhost:8009/books/bookId`

### GET/books/title
Description: Retrieve book in the library by book title.
Example: `GET http://localhost:8009/books/title/BookName`

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
