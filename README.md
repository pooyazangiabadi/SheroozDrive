# SheroozDrive
### Start the Application with the help of Docker
Go to the project directory and execute the following command in the terminal
```
docker-compose up
```

### Create user in Mongo
```
use sherooz
db.createUser({user: "user1", pwd: "user1", roles: [{role: "readWrite", db: "sherooz"}]})
```
