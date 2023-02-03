# Spring-movieweb
A CRUD application made with Spring Boot + PostgreSQL.


## Endpoints (plus means it exists, minus it does not)
|endpoint-name|GET|PUT|POST|DELETE|
|-|-|-|-|-|
|/api/users/|+|-|+|-|
|/api/users/{userId}|+|+|-|+|
|/api/reviews|+|-|+|-|
|/api/reviews/{reviewId}|+|+|-|+|
|/api/movies|+|-|+|-|

## ReviewService layer
The main logic is in the ReviewService file where rating of a choosen movie is computed. It is based on reviews that are added or modified by users.

## Table relations
Users OneToMany Reviews

Movies OneToMany Reviews

## Example endpoints execution
POST localhost:8080/api/reviews
```bash
curl --location --request POST 'localhost:8080/api/reviews' \
--header 'Content-Type: application/json' \
--data-raw '{
    "movieId": 1,
    "userId": 1,
    "description": "Nice movie",
    "rate": 4
}'
```
GET localhost:8080/api/reviews
<details>
  <summary>Response</summary>
  

```json
[
    {
        "id": 1,
        "movie": {
            "id": 1,
            "title": "Gladiator",
            "producer": "Ridley Scott",
            "rating": 2.0,
            "numOfRates": 1
        },
        "user": {
            "id": 1,
            "name": "Jonasz"
        },
        "description": "Nice movie",
        "rate": 4.0
    }
]
```
</details>

GET localhost:8080/api/movies
<details>
  <summary>Response</summary>
  
```json
[
    {
        "id": 1,
        "title": "Gladiator",
        "producer": "Ridley Scott",
        "rating": 4.0,
        "numOfRates": 1
    }
]
```
</details>

POST localhost:8080/api/reviews
```bash
curl --location --request POST 'localhost:8080/api/reviews' \
--header 'Content-Type: application/json' \
--data-raw '{
    "movieId": 1,
    "userId": 2,
    "description": "Average movie",
    "rate": 3
}'
```
</details>
GET localhost:8080/api/movies

<details>
  <summary>Response</summary>
  
```json
[
    {
        "id": 1,
        "title": "Gladiator",
        "producer": "Ridley Scott",
        "rating": 3.5,
        "numOfRates": 2
    }
]
```
</details>

GET localhost:8080/api/reviews
<details>
  <summary>Response</summary>
  

```json
[
    {
        "id": 1,
        "movie": {
            "id": 1,
            "title": "Gladiator",
            "producer": "Ridley Scott",
            "rating": 3.5,
            "numOfRates": 2
        },
        "user": {
            "id": 1,
            "name": "Jonasz"
        },
        "description": "Nice movie",
        "rate": 4.0
    },
    {
        "id": 2,
        "movie": {
            "id": 1,
            "title": "Gladiator",
            "producer": "Ridley Scott",
            "rating": 3.5,
            "numOfRates": 2
        },
        "user": {
            "id": 2,
            "name": "Maciej"
        },
        "description": "Average movie",
        "rate": 3.0
    }
] 
```
</details>

GET localhost:8080/api/user/2
<details>
  <summary>Response</summary>
  
```json
{
    "id": 2,
    "name": "Maciej",
    "listOfReviews": [
        {
            "title": "Gladiator",
            "producer": "Ridley Scott",
            "rating": 3.0,
            "description": "Average movie"
        }
    ]
}
```
</details>

GET localhost:8080/api/review/1

<details>
  <summary>Response</summary>

```json
{
    "id": 1,
    "movie": {
        "id": 1,
        "title": "Gladiator",
        "producer": "Ridley Scott",
        "rating": 3.0,
        "numOfRates": 1
    },
    "user": {
        "id": 2,
        "name": "Maciej"
    },
    "description": "Average movie",
    "rate": 3.0
}
```
</details>
