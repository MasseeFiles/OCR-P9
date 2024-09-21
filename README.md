# DIAGNOSIS APP
# _Microservice PATIENT_

DIAGNOSIS est une application d'aide à la détection du diabète de type 2 comportant 5 microservices (Gateway, View, Patient, Risk et Note). Le microservice PATIENT a pour rôle de gérer les données personnelles des patients. Il expose pour cela des endpoints REST accessibles aux autres microservices de l'appli via la gateway.

### Persistence des données
Le microservice utilise une base SQL pour la persistence des données.

### Port
Le microservice PATIENT est exposé sur le port 8081

### Docker

Le microservice comporte un fichier Dockerfile à la racine du projet pour la création de son image DOCKER.

