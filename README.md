# E-Commerce Backend API

Spring Boot based backend API for an e-commerce system.

## Features
- JWT Authentication
- Role Based Access Control
- Product Management
- Cart System
- Order Placement
- Pagination
- Global Exception Handling

## Tech Stack
- Java
- Spring Boot
- Spring Security
- JWT
- MySQL
- JPA / Hibernate

## API Endpoints

Auth
POST /auth/register
POST /auth/login

Products
GET /products

Cart
POST /cart/add
GET /cart

Orders
POST /orders
GET /orders

Users
GET /users (Admin only)

## Run Locally

```bash
git clone repo
cd ecommerce-api
mvn spring-boot:run
