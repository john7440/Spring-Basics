# SpringShopJPA 

A console based Spring Boot app for managing an e-commerce shop (articles and categories),  
developed as a Spring data JPA training exercise

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Technologies](#technologies)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [Usage](#usage)
- [Project Structure](#project-structure)
  
## Overview

This application provides a console based admin interface for an e-commerce platform.  
It allows managing artciles and categories stored in a relational database, with full CRUD  
and paginated article browsing.  
It is built with Spring Boot and Spring Data JPA, following a multi-layered architecture   
(Entity, Repository, Business, UI)

## Features

### Article Management 
- Display all articles (with or without pagination)
- Add a new article
- view an article by id
- Update an existing artcile
- Delete an article by ID

### Category management

- Add a new category
- View a catgory by id
- Update an existing category
- Delete a category by ID

### Browsing and Search
- Display all articles of a given category
- Navigate articles page by page (NEXT,PREV,PAGE x, EXIT)
- Search articles by brand and description

## Architecture

The application follows a **multi-layered architecture**:

- **UI Layer** (Console UI) — User interaction and menu display
- **Business Layer** — Application logic wrapping repository calls
- **Repository Layer** (Spring Data JPA) — Database access via JpaRepository
- **Entity Layer** — JPA-mapped domain objects
- **Database** (MySQL / MariaDB)

## Technologies

- **Language**: Java 17
- **Framework**: Spring Boot 2.5.3
- **ORM**: Spring Data JPA / Hibernate
- **Database**: MySQL 8.x / MariaDB 10.x
- **Build Tool**: Maven
- **Code Quality**: SonarQube
- **Version Control**: Git

## Installation

### 1. Clone the repository
```bash
https://github.com/john7440/Spring-Basics.git
```
### 2.Open in IntellijIDEA
1. File -> Open -> Select the project folder
2. Wait for intellij to index the prject and download dependencies
3. Verify that the Maven `pom.xml` is correctly recognized

## Database Setup

The database configuration is located in `src/main/resources/application.properties`:
```text
spring.application.name=SpringShopJpa

#Database
spring.datasource.url=jdbc:mariadb://localhost:3308/stock?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

#Jpa
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
```
Note: Update credentials if your MySQL setup uses a different port, username or password

## Usage

Run the application  
Option A: From IntelliJ IDEA

Navigate to `src/main/java/fr/fms/SpringShopJpaApplication.java`  
Right-click on the file    
Select `Run 'SpringShopJpaApplication.main()'`   

Option B: From Command Line (Maven)  
```bash
mvn spring-boot:run
```

## Menu Navigation

```text
Bienvenu dans notre application de gestion d'articles !
1: Afficher tous les articles sans pagination
2: Afficher tous les articles avec pagination
*************************
3: Ajouter un article
4: Afficher un article
5: Supprimer un article
6: Modifier un article
**************************
7: Ajouter une categorie
8: Afficher une categorie
9: Supprimer une categorie
10: Mettre a jour une categorie
11: Afficher tous les articles d'une categorie
***********************
12: Sortir du programme
```
## Pagination commands

| Command | Action                      |
| ------- | --------------------------- |
| NEXT    | Go to next page             |
| PREV    | Go to previous page         |
| PAGE 7  | Set page size to 7 articles |
| MENU    | Display pagination help     |
| EXIT    | Return to main menu         |

## Project Structure

```text
SpringShopJPA/
├── src/
│   └── main/
│       ├── java/fr/fms/
│       │   ├── SpringShopJpaApplication.java   # Entry point + Exo 1
│       │   ├── business/
│       │   │   └── ShopService.java            # Business logic layer
│       │   ├── dao/
│       │   │   ├── ArticleRepository.java      # Spring Data JPA repository 
│       │   │   └── CategoryRepository.java     # Spring Data JPA repository
│       │   ├── entities/
│       │   │   ├── Article.java                # Article JPA entity
│       │   │   └── Category.java               # Category JPA entity
│       │   └── ui/
│       │       └── ShopUI.java                 # Console UI layer
│       └── resources/
│           └── application.properties          # App & DB configuration
├── pom.xml                                     # Maven dependencies
└── README.md                                   # This file
````
### License  
This project is part of a Spring Boot / JPA training exercise and is for educational purposes only
