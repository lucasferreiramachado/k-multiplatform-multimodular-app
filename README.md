# KApp: Kotlin Multiplatform Multimodular App

## Key Topics:

### Coordinator

### Deeplink

### Feature Module
  
  - Example Compose App
    - Dev Environment
  
  - Compose Module
    - Feature Coordinator
        - Flow/Journey Coordinators
            - Composable screens

## Architecture

### Applicaton

- AppCoordinator: root coordinator to handle app behaviors like initialization, setup environment settings, screens like splash screen and tutorial

- FeaturesCoordinator: Feature setup and integration

- DeeplinkCoordinator: Deeplink setup and handlers

### Features modules:

- KApp-Auth: Authentication Feature
- KApp-Home: Main flow Feature

- Others features:
    - KApp-Products:
    

### Base modules:

- Kapp-Deeplink: App internal and public deeplinks builders

- KApp-Data: Repositories, DataSource, etc..

    - kapp-data-user: 
    - others modules:
        - kapp-data-products
        - kapp-data-purchase 