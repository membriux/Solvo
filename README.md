
# Solvo

**App Description:** Platform for helping people and entrepreneurs come up with solutions to modern problems.

<img src="https://i.imgur.com/AkLbGaJ.gif" height=500>

## Table of Contents

- [Solvo](#solvo)
  - [Table of Contents](#table-of-contents)
  - [Setup/Installation 🏗](#setupinstallation-)
  - [Tech Stack 🍽](#tech-stack-)
    - [Client](#client)
    - [Web Server](#web-server)
    - [Database](#database)
    - [Containers 📦](#containers-)
    - [Issues Encountered](#issues-encountered)
  - [Authors 📝](#authors-)

## Setup/Installation 🏗

1. Install [Docker Compose](https://docs.docker.com/compose/install/)
2. Install Android Studio
3. Start Docker with `systemctl start docker` or its equivalent in your system.
4. Run `docker-compose up -d --build`
5. Run Android client app on android studio

**(Optional)**

1. Navigate to `localhost:8000/docs` to view the FastAPI docs for our project

## Tech Stack 🍽

### Client

We created the client mainly on native Android (Java) for this project. We used the following third party library to supplement our application for handling the network requests:

- [**RetroFit**](https://square.github.io/retrofit/) – A type-safe HTTP client for Android and Java


### Web Server

For our project, we integrated a Python FastAPI server that works as an API for sending/receiving data and handling user authentication.

Tech Used
- **Python**
- [**FastAPI**](https://fastapi.tiangolo.com/) –FastAPI is a modern, fast (high-performance), web framework for building APIs with Python 3.6+ based on standard Python type hints.

<img src="https://i.imgur.com/z9fpgUr.png" width=700>


**API Endpoints**

User

| HTTP Verb | Endpoint        | Description             |
| --------- | --------------- | ----------------------- |
| `GET`     | /users          | get users (for testing) |
| `POST`    | /user           | create user             |
| `GET`     | /user/{user_id} | retrieve specific user  |

Problems

| HTTP Verb | Endpoint           | Description                 |
| --------- | ------------------ | --------------------------- |
| `POST`    | /problem/{user_id} | post a problem made by user |
| `GET`     | /problems          | get problems data           |


Solutions

| HTTP Verb | Endpoint                | Description                       |
| --------- | ----------------------- | --------------------------------- |
| `POST`    | /solutions/{problem_id} | post a solution for problem       |
| `GET`     | /solutions/{problem_id} | get solutions of specific problem |


### Database 

We installed a PostgreSQL database containerized using the PostgreSQL image in the Docker registry. 

- [**SQLite3**](https://docs.python.org/3/library/sqlite3.html) – database
- [**Flask SQLAlchemy**](https://flask-sqlalchemy.palletsprojects.com/en/2.x/) – ORM extension for handling our data from Postgre

**Data Schemas**

User

| Property  | Type      | Description                |
| --------- | --------- | -------------------------- |
| email     | string    | user's email               |
| id        | int       | user id                    |
| is_active | bool      | user's been active         |
| problems  | [Problem] | user's interested problems |


Problem

| Property    | Type       | Description                   |
| ----------- | ---------- | ----------------------------- |
| title       | string     | title of problem              |
| id          | int        | problem id                    |
| description | string     | description of problem        |
| createdAt   | date       | date that problem was posted  |
| owner_id    | int        | id of owner user              |
| solutions   | [Solution] | proposed solutions of problem |

Solution

| Property    | Type   | Description                   |
| ----------- | ------ | ----------------------------- |
| description | string | description of solution       |
| id          | int    | solution id                   |
| createdAt   | date   | date that solution was posted |
| problem_id  | int    | id of solution's problem      |
| owner_id    | int    | id of owner of solution       |

### Containers 📦

In our project, we containerized and isolated the API component to prevent others from running into issues with different python versions and simplify the process of running the API instance. This helped us be more efficient and productive developing in our project. Below is a table that represents the containers, networks, and dependencies of this project (from docker-compose):

| Container Name | Component          |
| -------------- | ------------------ |
| api            | FastAPI web server |

### Issues Encountered

The main issue we encountered in this project was connecting the client (Android) with the backend (FastApi). The main reason is due to being our first time experimenting with FastAPI and combining it with Android (Retrofit) together. Some other issues we encountered were with designing the database schema but after drawing and designing we were able to connect all the pieces together. 

## Authors 📝

- Dee Yeum - [**@ChefYeum**](https://github.com/ChefYeum)
	- Database Integration
	- FastAPI setup/Integration
	- Data schemas design
- Adnan Arshad - [**@adnan-creator**](https://github.com/adnan-creator)
	- UI/UX
	- Android developer
- Guillermo Sanchez – [**@membriux**](https://github.com/membriux)
	- Project Manager (GitHub Projects)
	- Initial Project Design
	- Network Requests (Client using Retrofit)
	- Database Design


