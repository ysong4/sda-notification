## Useful Info

```bash
PORT=8082
SWAGGER_URL="http://localhost:8082/swagger-ui/index.html"
```

## How to run?

```bash
cd ./notification-mysql-local

docker-compose up -d
```

## How to clean database?

```bash
# Delete the entire local MySQL instance
cd ./notification-mysql-local

docker-compose down

rm -rf local-notification-db-mysql-data

# Then apply the steps in `How to run?`
```

## References

Spring Boot & WebSocket:
- https://www.youtube.com/watch?v=TywlS9iAZCM
- https://github.com/ali-bouali/spring-boot-websocket-chat-app/blob/main/src/main/java/com/alibou/websocket/chat/ChatController.java

Exception handling: 
- https://medium.com/@aedemirsen/spring-boot-global-exception-handler-842d7143cf2a

