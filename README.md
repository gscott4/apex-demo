# apex-demo
This is a simple Spring project that represents an API for customers who have made transactions and how many reward points they have accumulated over the last three months.

The project has an embedded H2 database with an initial script that will run on startup that will populate a user with some transactions.

The main endpoints are:
- "/customers" (GET) : Brings back the full list of customers
- "/customers/{id}" (GET) : Brings back the customer with their transactions and points in a 3 month period
- "/customers" (POST) : Creates the customer. Only need to pass in the "customerName" as the JSON request body for it to create new user.
- "/transactions" (GET) & (POST) : Either shows all the transactions or creates a new transaction.

Here are samples of the Postman requests:
### Create New User: "/customers"
![image](https://user-images.githubusercontent.com/20051089/179562331-caa3a691-69c8-4121-b014-6040c928c991.png)

Response will return a Http 201 Status Code for a created object.

### Create New Transaction: "/transactions"
![image](https://user-images.githubusercontent.com/20051089/179563665-65917658-e65f-4aab-9677-c23951f3067c.png)


