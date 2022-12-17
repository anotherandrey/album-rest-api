### What is it?

[album-rest-api](https://github.com/anotherandrey/album-rest-api) is REST API for [album-rest-api-react-ui](https://github.com/anotherandrey/album-rest-api-react-ui), to
store _*.jpeg's_, _*.png's_, or another content.

### How to run with Gradle:
1. Firstly, specify (*application.properties in `web` module*):
   - `images.parent-directories`, to specify parent directories to store images.
   - `images.content-type`, to specify images content types.
   - `web.mvc.allowed-origins` and `web.mvc.allowed-methods`, to specify CORS policy.
2. Then you should specify *db* configuration (*postgresql* only).
3. Run `./gradlew bootRun`, to start.

### How to run with Docker:
1. Firstly, specify (*application.properties in `web` module*):
   - `images.parent-directories`, to specify parent directories to store images.
   - `images.content-type`, to specify images content types.
   - `web.mvc.allowed-origins` and `web.mvc.allowed-methods`, to specify CORS policy.
2. Build docker imageEntity (`docker build -t album-rest-api .` maybe).
3. Then you should specify db configuration environment and imageEntity (*docker-compose.yml*).
4. Run `docker compose up`.
