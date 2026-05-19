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
Screenshot Erreur :
  ![Gestion_Erreur_Apres_dev2.png](pictures/Gestion_Erreur_Apres_dev2.png)