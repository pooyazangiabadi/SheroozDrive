# SheroozDrive
### Start the Application with the help of Docker
Go to the project directory and execute the following command in the terminal
```
docker network create sherooz-network
docker-compose up
docker exec -it mongo bash
 > use sherooz
 > db.createUser({user: "user1", pwd: "user1", roles: [{role: "readWrite", db: "sherooz"}]})
```

### Swagger ui url
```
http://localhost:8000/swagger-ui/index.html
```
### Just run MongoDB in a Docker container
```
docker network create sherooz-network

docker run -d --network sherooz-network --name mongo \
	-e MONGO_INITDB_ROOT_USERNAME=root \
	-e MONGO_INITDB_ROOT_PASSWORD=root \
	-e MONGO_INITDB_DATABASE=sherooz \
	-p 27017:27017 \
	mongo:4.4
```
### Create user in Mongo
```
use sherooz
db.createUser({user: "user1", pwd: "user1", roles: [{role: "readWrite", db: "sherooz"}]})
```
