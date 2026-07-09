# MediaTracker

I built this project to practice connecting Java to a real database after learning SQL. The idea is simple — a personal tracker where you can rate anything you want across categories you define your[...] 

---

## What it does

You create your own categories (Movies, Food, Books — anything), add items to them, tag them however you want, and rate them with a score and optional review. You can track the status of anything to[...] 

The whole thing runs as a console app with a menu system. Not the prettiest, but it hits everything I wanted to practice: real database design, JDBC, layered architecture, and Git workflow from day on[...]

---

## Tech

- Java
- PostgreSQL (running in Docker)
- JDBC for database connectivity
- Maven for dependency management
- Git for version control

---

## How the code is organized

I structured this with four layers that only talk to the layer directly below them:

```
UI → Service → Repository → Database
```

```
src/main/java/
  media/
    model/          Plain Java objects — Category, Item, Tag, Rating
    persistence/    Repository interfaces and their JDBC implementations
    service/        Business logic and input validation before hitting the DB
    ui/             Console menus
src/main/resources/
  db.properties.example
```

The persistence layer uses the Repository pattern — each entity (Category, Item, Tag, Rating) has its own interface and JDBC implementation. The service layer sits on top and handles validation, so [...]

---

## Database schema

```
category     — category_id (PK), name (UNIQUE)
item         — item_id (PK), title, category_id (FK), status, date_added
rating       — rating_id (PK), item_id (FK), score (1–10), review, date_rated
tag          — tag_id (PK), name (UNIQUE)
item_tag     — item_id (FK), tag_id (FK)  ← junction table for the many-to-many
```

Items belong to one category. Tags are many-to-many with items through the junction table. Items can have multiple ratings over time so you can track how your opinion changes.

---

## Running it yourself

### What you need

- Java 17+
- Maven
- Docker Desktop

### Setup

**1. Clone the repo**
```bash
git clone https://github.com/cjreastmann/MediaTracker.git
cd MediaTracker
```

**2. Set up your credentials**

Copy the example file and fill in your own values:
```bash
cp src/main/resources/db.properties.example src/main/resources/db.properties
```

```properties
db.url=jdbc:postgresql://localhost:5432/your_database
db.username=your_username
db.password=your_password
```

**3. Start the database**
```bash
docker compose up -d
```

This starts PostgreSQL on port 5432 and pgAdmin on port 5050.

**4. Create the tables**

Open pgAdmin at `http://localhost:5050`, connect to your server, and run this in the Query Tool:

```sql
CREATE TABLE category (
    category_id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE item (
    item_id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    category_id INT NOT NULL REFERENCES category(category_id),
    status VARCHAR(20) NOT NULL CHECK (status IN ('want', 'in_progress', 'done')),
    date_added DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE rating (
    rating_id SERIAL PRIMARY KEY,
    item_id INT NOT NULL REFERENCES item(item_id),
    score INT NOT NULL CHECK (score BETWEEN 1 AND 10),
    review TEXT,
    date_rated DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE tag (
    tag_id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE item_tag (
    item_id INT NOT NULL REFERENCES item(item_id),
    tag_id INT NOT NULL REFERENCES tag(tag_id),
    PRIMARY KEY (item_id, tag_id)
);
```

**5. Run it**

In Eclipse: right-click `ConsoleUI.java` → Run As → Java Application

---

## Things I want to add

- JUnit tests for the service layer
- Status values as a Java enum instead of plain strings
- Average score per item using SQL aggregates
- A persistent Docker volume (right now data is lost if the container is removed)
- Eventually swap the console UI for a Spring Boot REST API

---

## Author

Corey Reastmann — [github.com/cjreastmann](https://github.com/cjreastmann)
