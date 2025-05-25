# 📦 Tracking Number Generator API

Objective:
Design and implement a RESTful API that generates unique tracking numbers for parcels. This
API should be scalable, efficient, and capable of handling high concurrency.
---

## ✨ Features

- Generates or returns an existing tracking number based on parcel data.
- Accepts input via:
    - `GET /api/next-tracking-number` with query parameters
    - `POST /api/next-tracking-number` with JSON body
- Ensures tracking number uniqueness across all instances.
- Returns timestamps in **RFC 3339 (ISO 8601)** format.
---
## 🧪 Requirements

To run this application locally, ensure the following:

- ✅ Java 17 or higher
- ✅ Maven (`mvn`)
- ✅ PostgreSQL 17 database
---

## Running locally

### Set environment variable

Powershell:

```
$env:SPRING_TRACK_NUMBER_GEN_DATASOURCE_URL="jdbc:postgresql://localhost:5432/<DatabaseName>"
$env:SPRING_TRACK_NUMBER_GEN_DATASOURCE_USER="<username>"
$env:SPRING_TRACK_NUMBER_GEN_DATASOURCE_PASSWORD="<password>"
```

```
./mvnw spring-boot:run
```

After the application up and running. 

## 🧪 Swagger Documentation

Swagger UI available at:

Go to [swagger-ui](http://localhost:8080/swagger-ui/index.html#/)

Test endpoints directly from browser.

## 🔗 Deployed Version

Backend is deployed using https://render.com

Database is using https://neon.tech
