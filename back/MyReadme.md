
# sourcer l'environnement 
- $ . ./.env

# $ mvn spring-boot:run
- Créer le conteneur docker Mysql
- Démarrer l'application back

$ docker ps
>CONTAINER ID   IMAGE          COMMAND                  CREATED       STATUS       PORTS                                           NAMES
0f1d66826d84   mysql:latest   "docker-entrypoint.s…"   2 hours ago   Up 2 hours   0.0.0.0:14044->3306/tcp, [::]:14044->3306/tcp   back_mysql

Attention : le port 14044 peut être changé :
2b73f9e5fe2f   mysql:latest   "docker-entrypoint.s…"   14 minutes ago   Up 14 minutes   0.0.0.0:65399->3306/tcp, [::]:65399->3306/tcp   back_mysql


=> Soucis pour se connecter à la base sans connaissance

- PS C:\Users\Moi> mysql -h 127.0.0.1 -P 14044 -u root -p
> Enter password:root_password

- PS C:\Users\Moi> mysql -h 127.0.0.1 -P 14044 -u userchatop -p
> Enter password: *********

- mysql> show databases;
> test



Erreur lors
$ docker run mysql:latest
> 2026-05-09 10:04:53+00:00 [Note] [Entrypoint]: Entrypoint script for MySQL Server 9.7.0-1.el9 started.
2026-05-09 10:04:54+00:00 [Note] [Entrypoint]: Switching to dedicated user 'mysql'
2026-05-09 10:04:54+00:00 [Note] [Entrypoint]: Entrypoint script for MySQL Server 9.7.0-1.el9 started.
2026-05-09 10:04:54+00:00 [ERROR] [Entrypoint]: Database is uninitialized and password option is not specified
You need to specify one of the following as an environment variable:
-MYSQL_ROOT_PASSWORD
-MYSQL_ALLOW_EMPTY_PASSWORD
-MYSQL_RANDOM_ROOT_PASSWORD

Solution propre :
- supprimer le conteneur 
- renseigner les trois variables manquantes

Pour revenir en arrière :
# suppression objets docker
docker rm -f <container_id>              # supprimer conteneur
docker volume rm back_db_data            # supprimer volume spécifique
docker rmi mysql:latest                  # supprimer image spécifique

Moi@SPC MINGW64 /d/data/IntellijiHome/IdeaProjects/MyOc2P4_Studio_Yoga/back (mymain)
=> $ env | grep DB
> DB_PASSWORD=chatoppwd
 DB_USER=userchatop

=> Resourcer env 
>$ cat .env
DB_USER=user_test
DB_PASSWORD=test_password
DB_HOST=localhost
DB_PORT=3306
DB_NAME=test
TOKEN_SECRET=cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e

$ . ./.env

# Erreur lors de démarrage :
>2026-05-09T16:13:10.750+02:00  WARN 16880 --- [back] [main] r$InitializeUserDetailsManagerConfigurer......annotation.authentication.configuration.
> InitializeUserDetailsBeanManagerConfigurer' to ERROR

$ docker ps -a
>CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                                           NAMES
20a6f0aaa611   mysql:latest   "docker-entrypoint.s…"   9 minutes ago   Up 9 minutes   0.0.0.0:59884->3306/tcp, [::]:59884->3306/tcp   back_mysql

$ mysql -h 127.0.0.1 -P 59884 -u user_test -p
>Enter password: *************

mysql> use test;
>Database changed

mysql> source insert_user.sql;
>Query OK, 1 row affected (0.02 sec)

mysql> select * from users;
>
+----+--------------+---------------------+-----------------+------------+-----------+--------------------------------------------------------------+---------------------+
| id | admin        | created_at          | email           | first_name | last_name | password                                                     | updated_at          |
+----+--------------+---------------------+-----------------+------------+-----------+--------------------------------------------------------------+---------------------+
|  1 | 0x01         | 2026-05-09 14:28:14 | yoga@studio.com | Admin      | Admin     | $2a$10$.Hsa/ZjUVaHqi0tp9xieMeewrnZxrZ5pQRzddUXE/WjDu2ZThe6Iq | 2026-05-09 14:28:14 |
+----+--------------+---------------------+-----------------+------------+-----------+--------------------------------------------------------------+---------------------+

