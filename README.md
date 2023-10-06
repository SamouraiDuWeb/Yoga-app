# Testez une Application Full-Stack - Yoga App

## Démarrage du Projet
### Git Clone :
```bash
git clone https://github.com/SamouraiDuWeb/Yoga-app
```
[https://github.com/SamouraiDuWeb/Yoga-app](https://github.com/SamouraiDuWeb/Yoga-app)

## Installation du Backend
### Aller dans le dossier :
```bash
cd backend
```
### Installer les dépendances :
```bash
mvn clean install
```
### Lancer le serveur backend :
```bash
mvn spring-boot:run
```
### Accéder à l'API backend à [http://localhost:8080](http://localhost:8080).

### Pour lancer et générer la couverture de code Jacoco :
```bash
mvn clean test
```

## Frontend
### Aller dans le dossier :
```bash
cd front
```
### Installer les dépendances :
```bash
npm install
```
### Lancer le Front-end :
```bash
npm run start
```

## Ressources
### Mockoon env
### Collection Postman
Pour Postman, importez la collection :
```bash
ressources/postman/yoga.postman_collection.json
```
en suivant la documentation :

[https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman](https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman)

### MySQL
Le script SQL pour créer le schéma est disponible ici : `ressources/sql/script.sql`

Par défaut, le compte administrateur est :

- login : yoga@studio.com
- mot de passe : test!1234

## Tests
### Tests E2E (End-to-End)
#### Lancement des tests e2e :
```bash
npm run e2e
```
#### Générer le rapport de couverture (vous devez lancer les tests e2e avant) :
```bash
npm run e2e:coverage
```
Le rapport est disponible ici :

`front/coverage/lcov-report/index.html`

### Tests Unitaires
#### Lancement des tests :
```bash
npm run test
```
#### Pour suivre les modifications :
```bash
npm run test:watch
```
