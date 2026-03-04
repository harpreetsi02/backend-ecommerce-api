# E-Commerce Backend API

A scalable RESTful backend for an e-commerce system built using Spring Boot. The application provides secure authentication with JWT and role-based authorization, along with product management, cart operations, and order processing.

## Features

* JWT Authentication & Authorization
* Role-Based Access Control (Admin/User)
* Product Management APIs
* Cart System
* Order Processing
* Pagination Support
* Global Exception Handling
* Clean Layered Architecture (Controller, Service, Repository)

## Tech Stack

* Java
* Spring Boot
* Spring Security
* JWT
* MySQL
* JPA / Hibernate
* Maven

## API Endpoints

### Authentication

POST /auth/register
POST /auth/login

Example Request:
{
"name": "Harpreet",
"email": "user@test.com",
"password": "123456"
}

### Products

GET /products
POST /products (Admin only)

### Cart

POST /cart/add
GET /cart

### Orders

POST /orders
GET /orders

### Users

GET /users (Admin only)

## Project Structure

controller → Handles API requests
service → Business logic layer
repository → Database operations
entity → Database entities
security → JWT & security configuration
dto → Data transfer objects
exception → Global exception handling

## Run Locally

```bash
git clone repo
cd ecommerce-api
mvn spring-boot:run
