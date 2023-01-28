# SheroozDrive
### Start the Application with the help of Docker
Go to the project directory and execute the following command in the terminal
```
docker network create sherooz-network
docker-compose up
docker exec -it mongo bash
 # mongo -u root -p root
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

docker run -d --network sherooz-network --name sherooz \
    -e SERVER_PORT=8000
    -e SPRING_PROFILES_ACTIVE=prod
    -e SPRING_APPLICATION_NAME=$APP_NAME
    -e SPRING_DATA_MONGODB_AUTHENTICATION_DATABASE=sherooz
    -e SPRING_DATA_MONGODB_AUTO_INDEX_CREATION=true
    -e SPRING_DATA_MONGODB_HOST=mongo
    -e SPRING_DATA_MONGODB_PORT=27017
    -e SPRING_DATA_MONGODB_USERNAME=user1
    -e SPRING_DATA_MONGODB_PASSWORD=user1
    -e SPRING_DATA_MONGODB_DATABASE=sherooz
    -p 8000:8000 \
	sheroozdrive-web:latest
```
### Create user in Mongo
```
use sherooz
db.createUser({user: "user1", pwd: "user1", roles: [{role: "readWrite", db: "sherooz"}]})
```
