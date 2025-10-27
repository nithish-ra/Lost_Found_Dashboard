# Lost & Found Application

This project is a **Lost & Found Board** web application built with **Vue 3 + Pinia** for frontend and **Spring Boot + MongoDB** for backend.  
It allows users to register, login, post lost/found items, and manage them. Admin and normal users have different privileges.

---

## Features

- User registration and login
- Admin and normal user roles
- CRUD operations for lost/found items
- Search items by title or status
- Responsive Vue.js frontend
- REST API built with Spring Boot
- MongoDB database integration

---

## Project Structure

lost-found-frontend/ # Vue 3 frontend
lost-found-backend/ # Spring Boot backend

markdown
Copy code

**Frontend:**

- `src/store/` – Pinia stores for user and item management
- `src/services/` – Axios API services
- `src/pages/` – Vue pages for UI
- `src/router/` – Vue Router setup

**Backend:**

- `com.example.lostfound.model` – MongoDB entity classes
- `com.example.lostfound.dto` – DTOs for API transfer
- `com.example.lostfound.repository` – Spring Data Mongo repositories
- `com.example.lostfound.service` – Business logic
- `com.example.lostfound.controller` – REST API controllers

---

## Requirements

- Node.js >= 18
- npm >= 9
- Java 17
- Maven
- MongoDB running locally or on cloud

---

## Backend Setup

1. Navigate to backend folder:

```bash
cd lost-found-backend
Configure MongoDB URI in application.properties or application.yml.

Build and run Spring Boot app:

bash
Copy code
mvn clean install
mvn spring-boot:run
API will run on http://localhost:8080/api/.

Frontend Setup
Navigate to frontend folder:

bash
Copy code
cd lost-found-frontend
Install dependencies:

bash
Copy code
npm install
Run development server:

bash
Copy code
npm run serve
Frontend will run on http://localhost:8081.

API Endpoints
Users:

Method	URL	Description
POST	/api/users	Register new user
POST	/api/users/login	Login user
GET	/api/users/{id}	Get user by ID
PUT	/api/users/{id}	Update user
DELETE	/api/users/{id}	Delete user

Items:

Method	URL	Description
POST	/api/items	Add new item
GET	/api/items	List all items
GET	/api/items/{id}	Get item by ID
PUT	/api/items/{id}	Update item
DELETE	/api/items/{id}	Delete item

Notes
Admin user is hardcoded for now:

Email: admin@board.com

Password: password

Normal users are stored in MongoDB.

Ensure CORS is enabled in Spring Boot for frontend (http://localhost:8081).



Project Name: Lost & Found
Description: A Spring Boot project to track lost and found items.

run the App.java and open the postman to run the below commands for testing

1️⃣ Create an Item (POST)

Method: POST

URL: http://localhost:8080/api/items

Headers:

Content-Type: application/json


Body (raw JSON):

{
    "title": "Lost Wallet leather",
    "description": "Brown leather wallet lost near library",
    "status": "lost",
    "location": "Library",
    "contact": "9876543210"
}


Expected Response: 201 Created

{
    "id": "auto_generated_id",
    "title": "Lost Wallet leather",
    "description": "Brown leather wallet lost near library",
    "status": "lost",
    "location": "Library",
    "contact": "9876543210",
    "postedByUserId": null
}

2️⃣ List All Items (GET)

Method: GET

URL: http://localhost:8080/api/items

Headers: None

Body: None

Optional Query Params:

status=lost → filter by status

q=wallet → search by title keyword

Expected Response: 200 OK

[
    {
        "id": "68f4973f6877c621c942b5d4",
        "title": "Lost Wallet leather",
        "description": "Brown leather wallet lost near library",
        "status": "lost",
        "location": "Library",
        "contact": "9876543210",
        "postedByUserId": null
    }
]

3️⃣ Get Single Item (GET)

Method: GET

URL: http://localhost:8080/api/items/68f4973f6877c621c942b5d4

Headers: None

Body: None

Expected Response: 200 OK

{
    "id": "68f4973f6877c621c942b5d4",
    "title": "Lost Wallet leather",
    "description": "Brown leather wallet lost near library",
    "status": "lost",
    "location": "Library",
    "contact": "9876543210",
    "postedByUserId": null
}

4️⃣ Update Item (PUT)

Method: PUT

URL: http://localhost:8080/api/items/68f4973f6877c621c942b5d4

Headers:

Content-Type: application/json


Body (raw JSON):

{
    "title": "Lost Wallet leather",
    "description": "Brown leather wallet lost near library, contains ID cards",
    "status": "lost",
    "location": "Library",
    "contact": "9876543210"
}


Expected Response: 200 OK

{
    "id": "68f4973f6877c621c942b5d4",
    "title": "Lost Wallet leather",
    "description": "Brown leather wallet lost near library, contains ID cards",
    "status": "lost",
    "location": "Library",
    "contact": "9876543210",
    "postedByUserId": null
}

5️⃣ Delete Item (DELETE)

Method: DELETE

URL: http://localhost:8080/api/items/68f4973f6877c621c942b5d4

Headers: None

Body: None

Expected Response: 204 No Content

6️⃣ Create User (POST)

Method: POST

URL: http://localhost:8080/api/users

Headers:

Content-Type: application/json


Body (raw JSON):

{
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "9876543210"
}


Expected Response: 201 Created

{
    "id": "auto_generated_user_id",
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "9876543210"
}

7️⃣ Get Single User (GET)

Method: GET

URL: http://localhost:8080/api/users/<user_id>

Expected Response: 200 OK

8️⃣ Update User (PUT)

Method: PUT

URL: http://localhost:8080/api/users/<user_id>

Body (raw JSON):

{
    "name": "John Doe Updated",
    "email": "john.updated@example.com",
    "phone": "9876543211"
}


Expected Response: 200 OK

9️⃣ Delete User (DELETE)

Method: DELETE

URL: http://localhost:8080/api/users/<user_id>

Expected Response: 204 No Content
# Lost & Found Application

This project is a **Lost & Found Board** web application built with **Vue 3 + Pinia** for frontend and **Spring Boot + MongoDB** for backend.  
It allows users to register, login, post lost/found items, and manage them. Admin and normal users have different privileges.

---

## Features

- User registration and login
- Admin and normal user roles
- CRUD operations for lost/found items
- Search items by title or status
- Responsive Vue.js frontend
- REST API built with Spring Boot
- MongoDB database integration

---

## Project Structure

lost-found-frontend/ # Vue 3 frontend
lost-found-backend/ # Spring Boot backend

markdown
Copy code

**Frontend:**

- `src/store/` – Pinia stores for user and item management
- `src/services/` – Axios API services
- `src/pages/` – Vue pages for UI
- `src/router/` – Vue Router setup

**Backend:**

- `com.example.lostfound.model` – MongoDB entity classes
- `com.example.lostfound.dto` – DTOs for API transfer
- `com.example.lostfound.repository` – Spring Data Mongo repositories
- `com.example.lostfound.service` – Business logic
- `com.example.lostfound.controller` – REST API controllers

---

## Requirements

- Node.js >= 18
- npm >= 9
- Java 17
- Maven
- MongoDB running locally or on cloud

---

## Backend Setup

1. Navigate to backend folder:

```bash
cd lost-found-backend
Configure MongoDB URI in application.properties or application.yml.

Build and run Spring Boot app:

bash
Copy code
mvn clean install
mvn spring-boot:run
API will run on http://localhost:8080/api/.

Frontend Setup
Navigate to frontend folder:

bash
Copy code
cd lost-found-frontend
Install dependencies:

bash
Copy code
npm install
Run development server:

bash
Copy code
npm run serve
Frontend will run on http://localhost:8081.

API Endpoints
Users:

Method	URL	Description
POST	/api/users	Register new user
POST	/api/users/login	Login user
GET	/api/users/{id}	Get user by ID
PUT	/api/users/{id}	Update user
DELETE	/api/users/{id}	Delete user

Items:

Method	URL	Description
POST	/api/items	Add new item
GET	/api/items	List all items
GET	/api/items/{id}	Get item by ID
PUT	/api/items/{id}	Update item
DELETE	/api/items/{id}	Delete item

Notes
Admin user is hardcoded for now:

Email: admin@board.com

Password: password

Normal users are stored in MongoDB.

Ensure CORS is enabled in Spring Boot for frontend (http://localhost:8081).



Project Name: Lost & Found
Description: A Spring Boot project to track lost and found items.

run the App.java and open the postman to run the below commands for testing

1️⃣ Create an Item (POST)

Method: POST

URL: http://localhost:8080/api/items

Headers:

Content-Type: application/json


Body (raw JSON):

{
    "title": "Lost Wallet leather",
    "description": "Brown leather wallet lost near library",
    "status": "lost",
    "location": "Library",
    "contact": "9876543210"
}


Expected Response: 201 Created

{
    "id": "auto_generated_id",
    "title": "Lost Wallet leather",
    "description": "Brown leather wallet lost near library",
    "status": "lost",
    "location": "Library",
    "contact": "9876543210",
    "postedByUserId": null
}

2️⃣ List All Items (GET)

Method: GET

URL: http://localhost:8080/api/items

Headers: None

Body: None

Optional Query Params:

status=lost → filter by status

q=wallet → search by title keyword

Expected Response: 200 OK

[
    {
        "id": "68f4973f6877c621c942b5d4",
        "title": "Lost Wallet leather",
        "description": "Brown leather wallet lost near library",
        "status": "lost",
        "location": "Library",
        "contact": "9876543210",
        "postedByUserId": null
    }
]

3️⃣ Get Single Item (GET)

Method: GET

URL: http://localhost:8080/api/items/68f4973f6877c621c942b5d4

Headers: None

Body: None

Expected Response: 200 OK

{
    "id": "68f4973f6877c621c942b5d4",
    "title": "Lost Wallet leather",
    "description": "Brown leather wallet lost near library",
    "status": "lost",
    "location": "Library",
    "contact": "9876543210",
    "postedByUserId": null
}

4️⃣ Update Item (PUT)

Method: PUT

URL: http://localhost:8080/api/items/68f4973f6877c621c942b5d4

Headers:

Content-Type: application/json


Body (raw JSON):

{
    "title": "Lost Wallet leather",
    "description": "Brown leather wallet lost near library, contains ID cards",
    "status": "lost",
    "location": "Library",
    "contact": "9876543210"
}


Expected Response: 200 OK

{
    "id": "68f4973f6877c621c942b5d4",
    "title": "Lost Wallet leather",
    "description": "Brown leather wallet lost near library, contains ID cards",
    "status": "lost",
    "location": "Library",
    "contact": "9876543210",
    "postedByUserId": null
}

5️⃣ Delete Item (DELETE)

Method: DELETE

URL: http://localhost:8080/api/items/68f4973f6877c621c942b5d4

Headers: None

Body: None

Expected Response: 204 No Content

6️⃣ Create User (POST)

Method: POST

URL: http://localhost:8080/api/users

Headers:

Content-Type: application/json


Body (raw JSON):

{
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "9876543210"
}


Expected Response: 201 Created

{
    "id": "auto_generated_user_id",
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "9876543210"
}

7️⃣ Get Single User (GET)

Method: GET

URL: http://localhost:8080/api/users/<user_id>

Expected Response: 200 OK

8️⃣ Update User (PUT)

Method: PUT

URL: http://localhost:8080/api/users/<user_id>

Body (raw JSON):

{
    "name": "John Doe Updated",
    "email": "john.updated@example.com",
    "phone": "9876543211"
}


Expected Response: 200 OK

9️⃣ Delete User (DELETE)

Method: DELETE

URL: http://localhost:8080/api/users/<user_id>

Expected Response: 204 No Content
