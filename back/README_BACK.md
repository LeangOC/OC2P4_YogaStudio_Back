# dev1 < mymain
1) Source l'environnement : 
> $ . ./.env
2) Démarrer l'application :
> $ mvn spring-boot:run

3) Vérification Docker:
> $ docker ps -a  
![Docker_PS_dev1.png](pictures/Docker_PS_dev1.png)

4) Vérification base :
>  select * from users;
![Mysql_Users_dev1.png](pictures/Mysql_Users_dev1.png)

5) import postman/yoga.postman_collection.json : 
> Test  Api : http://localhost:8080/api/auth/register
![Register_Api_Postman_dev1.png](pictures/Register_Api_Postman_dev1.png)

> Test Api POST : http://localhost:8080/api/auth/login
![Login_Api_Postman_dev1.png](pictures/Login_Api_Postman_dev1.png)

# dev2 : 
- Mise en place d’une gestion
  Implémentation : 
  - payload/response/ErrorResponse.java
  - exception/UnauthorizedException.java
  - exception/GlobalExceptionHandler.java

# dev3
- Le respect du découpage de l’application en plusieurs couches
  Refactor : UserService, UserController

# dev4
- Les traitements métiers déportés dans les classes de service métier
  Refactor : SessionController, SessionService

# dev5
- Implémentation des tests :
![Jacoco_couverture_code.png](pictures/Jacoco_couverture_code.png)

# dev6
Amélioration Test  
![Jacoco_dev6.png](pictures/Jacoco_dev6.png)

# dev7
Exlusion des tests tout élément DTO et la classe principale
Refactor : pom.xml

![Jacoco_NoDto_dev7.png](pictures/Jacoco_NoDto_dev7.png)

# solution_back < dev7
Renommer Myreadme.md en README_BACK.md

# main < merge 
