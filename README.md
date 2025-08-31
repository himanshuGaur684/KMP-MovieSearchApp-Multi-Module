# 🎬 Kotlin Multiplatform Movie Search App

This repository contains the source code for my YouTube tutorial on  
📺 **Kotlin Multiplatform Modularization with a Movie Search App**  

The app demonstrates how to build a **multi-module KMP project** with **Clean Architecture (MVVM)**.  
It includes **feature modules**, **core modules**, and platform-specific navigation while sharing logic across Android and iOS.

---

## 🚀 Key Highlights

- 🧩 **Multi-Module Architecture** – Feature-based + Core modules  
- 🏗 **Clean Architecture + MVVM** – Each feature has `data`, `domain`, and `ui` layers  
- 🌍 **Kotlin Multiplatform** – Shared business logic for Android & iOS  
- 🔗 **Ktor Client** – Networking layer for fetching movie data  
- ⚙️ **Koin** – Dependency Injection across modules  
- 🎨 **Jetpack Compose (Android)** + **SwiftUI (iOS)**  
- 📦 **Core Modules** – `coreNetwork`, `coreNavigation`  
- 📱 **Platform-Specific Navigation**  
  - Android → handled inside `composeApp`  
  - iOS → handled inside `coreNavigation` (within `iosApp`)  

---

## 📂 Project Structure
```
shared/
 ├── core-network/               # Ktor client setup, networking layer
 ├── features/
 │    ├── search/
 │    │    ├── data/             # Repository, DTOs, API calls for searching movies
 │    │    ├── domain/           # UseCases, domain models for search
 │    │    └── ui/               # Compose Multiplatform UI & ViewModels for search
 │    └── details/
 │         ├── data/             # Repository, DTOs, API calls for movie details
 │         ├── domain/           # UseCases, domain models for details
 │         └── ui/               # Compose Multiplatform UI & ViewModels for details
composeApp/                      # Android App entry point & Jetpack Compose Navigation
iosApp/
 ├── CoreNavigation/              # iOS Navigation handling
 └── Features/
     ├── SearchUI/                # iOS Search UI
     └── DetailsUI/               # iOS Details UI
```



 ## 🛠️ Tech Stack

- **Kotlin Multiplatform (KMP)**
- **Clean Architecture + MVVM**
- **Multi-Module Project Structure**
- **Ktor Client** – REST API calls
- **Koin DI** – Dependency Injection
- **Coroutines + Flow**
- **Jetpack Compose (Android UI)**
- **SwiftUI (iOS UI)**

---


## 🎬 Watch the Demo Video

[![Watch the video](https://youtu.be/xbF6nTpltXw)

