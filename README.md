# Auth Project: Spring Boot + HTML/CSS + Axios

Login and admin login with JWT. Backend: Spring Boot. Frontend: plain HTML, CSS and Axios.

## Folder structure

```
auth-project/
  backend/    Spring Boot API (port 8080)
  frontend/   login.html, home.html, admin.html, css/, js/
```

## Run the backend

Needs Java 17+ and Maven.

```
cd backend
mvn spring-boot:run
```

## Run the frontend

Open the `frontend` folder in VS Code and start it with the Live Server extension (open `login.html`).
You can also run `python -m http.server 5500` inside `frontend` and open http://localhost:5500/login.html.

## Demo accounts

| Role  | Username | Password |
|-------|----------|----------|
| User  | user     | user123  |
| Admin | admin    | admin123 |

## API

| Method | URL                      | Who can call it    |
|--------|--------------------------|--------------------|
| POST   | /api/auth/login          | Anyone (USER accounts only) |
| POST   | /api/auth/admin/login    | Anyone (ADMIN accounts only) |
| GET    | /api/home                | Any logged-in user |
| GET    | /api/admin/dashboard     | ADMIN only         |

Send the token as `Authorization: Bearer <token>`.
