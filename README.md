# GA-JDB-HWSPRING1 — Steam Player Profile API

A small Spring Boot REST API themed around gaming: browse, search, filter, and manage a list of games, plus a developer profile endpoint.

## Developer Profile
- **Name:** Fatima
- **Theme:** Gaming
- **Intro:** Java Developer, API builder, and sometimes gamer
- **Favourite game:** Elden Ring
- **Currently learning:** Spring Boot REST API

## How to Run
1. Clone or unzip the project.
2. Open in IntelliJ (or run `./mvnw spring-boot:run` from the project root).
3. Run `GamesApiApplication`.
4. The API runs on `http://localhost:8080`.

## Endpoints

| Method | Endpoint | Description |
|--------|----------|--------------|
| GET | `/api/welcome` | App and developer profile |
| GET | `/api/games` | All games |
| GET | `/api/games/{id}` | One game by id |
| GET | `/api/games/search?name=` | Search games by name |
| GET | `/api/games/filter?genre=&year=&minRating=` | Filter games |
| POST | `/api/games` | Add a new game |
| PUT | `/api/games/{id}` | Update a game |
| DELETE | `/api/games/{id}` | Delete a game |
| GET | `/api/games/summary` | Stats: average rating, highest rated, average downloads, most popular |
| GET | `/api/games/favourite` | Developer's favourite game |

## Notes on Sample Data
The list includes two entries for "Minecraft" (ids `123` and `1256`) intentionally, to demonstrate the DELETE endpoint on a duplicate.

## Screenshots

GET: http://localhost:8080/api/welcome

<img width="306" height="532" alt="image" src="https://github.com/user-attachments/assets/b327df10-6910-4106-840d-124c633e0683" />

GET: http://localhost:8080/api/games

<img width="316" height="523" alt="image" src="https://github.com/user-attachments/assets/23ff5a37-b80c-4d0d-8b37-ab15a6fe8b1d" />

GET: http://localhost:8080/api/games/1213

<img width="315" height="533" alt="image" src="https://github.com/user-attachments/assets/f7d1db80-d2e7-4532-81cf-663d96de9a42" />

POST: Creating a new game;; http://localhost:8080/api/games

<img width="484" height="519" alt="image" src="https://github.com/user-attachments/assets/9cdd5ccb-b426-4d38-aed0-720f78c59e76" />

<img width="477" height="516" alt="image" src="https://github.com/user-attachments/assets/2c6ab241-9570-48fe-8f45-5f679fd799c0" />

PUT: updating game;; http://localhost:8080/api/games/11

<img width="478" height="527" alt="image" src="https://github.com/user-attachments/assets/f4f31611-1b3f-49c4-8e61-bad2aef09593" />

<img width="480" height="507" alt="image" src="https://github.com/user-attachments/assets/3d120c4c-1f83-4a5f-9325-ecac69bbed5d" />

DELETE (duplicate game);; http://localhost:8080/api/games/1256

<img width="467" height="507" alt="image" src="https://github.com/user-attachments/assets/03cf19f0-8f86-4946-94e3-db04f5b3c478" />

<img width="476" height="382" alt="image" src="https://github.com/user-attachments/assets/9aab7f2f-3276-4f44-8d7d-af594a397001" />
