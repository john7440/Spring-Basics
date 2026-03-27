# SpringShopJPA 

A console based Spring Boot app for managing an e-commerce shop (articles and categories),  
developed as a Spring data JPA training exercise

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Technologies](#technologies)

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

