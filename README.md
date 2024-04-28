# Online Store Core
## Description
This project is an online store core, which is responsible for managing the products, categories, users, orders, and payments of an online store.

The architecture of this project is a microservices architecture.

## REST API
### Products
#### Create Product
```
POST /products
```
```json
{
    "name": "Product 1",
    "description": "Description of product 1",
    "price": 100.0,
    "category": 1
}
```

#### Get Products
```
GET /products
```

#### Get Product
```
GET /products/{id}
```

#### Update Product
```
PUT /products/{id}
```
```json
{
    "name": "Product 1",
    "description": "Description of product 1",
    "price": 100.0,
    "category": 1
}
```

#### Delete Product
```
DELETE /products/{id}
```

### Clients
#### Create Client
```
POST /clients
```
```json
{
    "name": "Client 1",
    "email": "mail@mail.com",
    "password": "12345678"
}
```

#### Get Clients
```
GET /clients
```

#### Get Client
```
GET /clients/{id}
```

#### Update Client
```
PUT /clients/{id}
```
```json
{
    "name": "Client 1",
    "email": "mail@mail.com",
    "password": "987654123"
}
```

#### Delete Client
```
DELETE /clients/{id}
```

#### Purchase History
```
GET /clients/{id}/addProduct/{idProduct}
```