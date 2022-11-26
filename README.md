### What is it?

[album-rest-api](https://github.com/anotherandrey/album-rest-api) is REST API for [album-rest-api-react-ui](https://github.com/anotherandrey/album-rest-api-react-ui), to
store _*.jpeg's_, _*.png's_, or another content.

### How to run with Gradle:
1. Firstly, specify (*application.properties*):
   - `album.parent-dirs`, to specify dir to store data.
   - `album.origins`, to specify CORS policy.
   - `album.content-types`, to specify allowed content types.
2. Then you should specify *db* configuration (*postgresql* only).
3. Run `./gradlew bootRun`, to start.
4. After all, when you run the application, you can run `sh seeds.sh`, to create start data.

### How to run with Docker:
1. Firstly, specify (*application.properties*):
   - `album.parent-dirs`, to specify dir to store data.
   - `album.origins`, to specify CORS policy.
   - `album.content-types`, to specify allowed content types.
2. Build docker image (`docker build -t album-rest-api .` maybe).
3. Then you should specify db configuration environment and image (*docker-compose.yml*).
4. Run `docker compose up`.
