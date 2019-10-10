# Payroll calculation POC
This is a service to calculate a payroll with data from employee service.

## Technologies, FrameWorks, Concepts e Architecture

* Java
* JPA
* REST
* GIT
* Docker
* Spring Boot
* MVC
* Gradle
* Flyway
* PostgreSQL

## Prerequisites

* [Git](https://git-scm.com/downloads)
* [Java 1.8](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/downloads-list.html) select JDK for your platform
* Latest version [Gradle](https://gradle.org/releases/)
* [Postman](https://www.getpostman.com/downloads/)
* [Docker](https://www.docker.com/products/docker-desktop) optional

## Setup
From a terminal, open on the root of the project

```
git clone https://github.com/TiagoMarquesTR/payroll-cal.git
cd payroll-cal
```
## Build

```
.\gradlew clean build
```

## Run

```
.\gradlew run
```
## Start Database in Docker
This is a option for running database locally:

```
docker run --name payroll-cal-db -e POSTGRESS_PASSWORD=password -d -p 5432:5432 postgres:alpine
```