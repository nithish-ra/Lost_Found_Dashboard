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