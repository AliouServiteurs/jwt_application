# Gestion des Endpoints avec un token – Spring Boot Application

Une application de gestion des Endpoints avec un token avec **Spring Boot**, sécurisée via **JWT (JSON Web Token)** et Spring Security. L’application permet la gestion des utilisateurs et des personnes, avec authentification et autorisation.

---

## Fonctionnalités principales

- **Enregistrement des utilisateurs** avec mot de passe chiffré (BCrypt).
- **Authentification des utilisateurs** via JWT.
- **Protection des endpoints** avec Spring Security.
- **Gestion des personnes** (CRUD complet pour les entités `Personne`).
- Sécurisation des routes avec JWT pour les endpoints sensibles.
- Exposition d’API REST via Spring MVC.

---

## Technologies utilisées

- Java 17+  
- Spring Boot 3  
- Spring Security  
- JWT (jjwt-impl 0.11.5)  
- Spring Data JPA  
- Hibernate  
- Lombok  
- Base de données relationnelle (ex : MySQL, PostgreSQL)  
- Tomcat embarqué  

---

## Structure des packages

- `controller` : endpoints REST pour l’authentification (`UtilisateurLoginRegisterController`) et la gestion des entités (`PersonneController`).  
- `model` : entités JPA (`Utilisateur`, `Personne`).  
- `repository` : interfaces pour l’accès aux données.  
- `service` : services métiers, notamment pour Spring Security (`CustumerUtilisateurDetailsService`) et gestion des personnes (`PersonneImplement`).  
- `configuration` : configuration Spring Security et JWT (`SecurityConfig`, `JwtUtil`).  
- `filter` : filtre JWT pour intercepter et valider les tokens (`JwtFilter`).  

---

## Endpoints principaux

### Authentification

| Méthode | URL                  | Description                                 | Accès |
|---------|--------------------|---------------------------------------------|-------|
| POST    | `/api/auth/register` | Enregistrer un nouvel utilisateur          | Public |
| POST    | `/api/auth/login`    | Authentifier un utilisateur et générer JWT | Public |

**Exemple JSON pour register/login** :

```json
{
  "nom": "aliou",
  "motDePasse": "123456",
}
```

---

### Gestion des personnes

| Méthode | URL                        | Description                     | Accès       |
|---------|----------------------------|---------------------------------|------------|
| GET     | `/api/personnes/tous`       | Récupérer toutes les personnes | Authentifié |
| GET     | `/api/personnes/trouver/{id}`| Récupérer une personne par ID  | Authentifié |
| POST    | `/api/personnes/ajouter`    | Ajouter une personne           | Authentifié |
| PUT     | `/api/personnes/mettreAJour/{id}` | Modifier une personne         | Authentifié |
| DELETE  | `/api/personnes/supprimer/{id}` | Supprimer une personne        | Authentifié |

---

## Sécurité

- Les routes `/api/auth/**` sont **publiques**.  
- Toutes les autres routes sont **protégées** par JWT.  
- La classe `JwtFilter` intercepte chaque requête pour vérifier le token JWT.  
- Le mot de passe des utilisateurs est **haché avec BCrypt**.

---

## Configuration

### SecurityConfig

- Définit les chemins autorisés sans authentification et ceux protégés.  
- Configure le `PasswordEncoder` (BCrypt).  
- Configure le `AuthenticationManager` pour Spring Security.

### JwtUtil

- Génère et valide les tokens JWT.  
- Extrait le nom d’utilisateur depuis le token pour authentifier les requêtes.

---

## Exécution

1. Configurer la base de données dans `application.properties` :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bibliotheque
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

2. Lancer l’application :

```bash
mvn spring-boot:run
```

3. Tester l’API avec Postman ou curl.  

---

## Exemple de workflow

1. **Inscription** : `POST /api/auth/register`  
2. **Connexion** : `POST /api/auth/login` → reçoit JWT  
3. **Accès aux ressources protégées** : inclure le JWT dans l’en-tête `Authorization: Bearer <token>`  

---

## Remarques

- Les endpoints `/api/personnes/**` nécessitent une **authentification JWT valide**.  
- Les mots de passe ne sont jamais stockés en clair dans la base de données.  
- Les erreurs comme `401 Unauthorized` apparaîtront si le JWT est absent ou invalide.
