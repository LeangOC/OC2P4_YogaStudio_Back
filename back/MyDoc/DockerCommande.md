# $ docker ps
-> afficher uniquement les conteneurs en cours d’exécution

#  $ docker inspect <nom_du_conteneur> | grep "HostPort"
> $ docker inspect mysql:latest | grep 3306
                 "3306/tcp": {},
                 "33060/tcp": {} 

=> Docker_Mysql_double_ports.md  

### $ docker stop 340046b779c3
➜ arrête seulement le conteneur
> 340046b779c3



# $ docker-compose down
➜ arrête + supprime le conteneur
> [+] Running 2/2
✔ Container back_mysql  Removed                                                                                                                                                                                                                                  1.6s
✔ Network back_default  Removed 

=> Arrêt conteneur dans compose.yaml : Docker_Mysql_docket-compose_down.md
Par défaut, down ne supprime pas :
- les volumes (données MySQL par exemple)
- les images
- les fichiers locaux


### $ docker-compose down -v 
Là on supprimes aussi :
- les volumes
- donc les données MySQL

# $ docker volume ls
->
>local     back_db_data
>local     medilabop9_mysql_data 

# $ docker inspect back_db_data
>"Mountpoint": "/var/lib/docker/volumes/back_db_data/_data"

### docker volume rm back_db_data
-> supprimer volume back_db_data  ( uniquement la base)


## $ docker images
>mysql:8.0.42                  63823b8e2cbe       1.07GB          249MB        
 mysql:latest                  f0ef1d92fa65        1.3GB          290MB

# $ docker inspect mysql:latest
-> inspecter l'image 

# $ docker run mysql:latest
-> Démarrer l'image
>2026-05-09 10:04:54+00:00 [ERROR] [Entrypoint]: Database is uninitialized and password option is not specified
You need to specify one of the following as an environment variable:
-MYSQL_ROOT_PASSWORD
-MYSQL_ALLOW_EMPTY_PASSWORD
-MYSQL_RANDOM_ROOT_PASSWORD

=> 

# $ docker ps -a
-> Affiche TOUS les conteneurs, y compris :
- ceux qui tournent 🟢
- ceux qui sont arrêtés 🔴
- ceux qui ont crashé 💥
>CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS                     PORTS     NAMES
 ea0045297126   mysql:latest   "docker-entrypoint.s…"   8 minutes ago   Exited (1) 8 minutes ago             awesome_nightingale

### $ docker container prune
-> Supprimer tous les conteneurs arrêtés

# $ docker rm ea0045297126
-> Supprimer le conteneur précisé 
>ea0045297126

### docker run -d -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 mysql:latest
-> solution pour pouvoir démarrer la base manuellement.

# $ docker volume rm back_db_data
-> suppression Volume
>back_db_data

# $ docker rmi mysql:latest
-> suppression image
>Untagged: mysql:latest
Deleted: sha256:f0ef1d92fa650fcfa5b85f1d82bb1a56a6dd579bf256b8f8f2a5a0b1b61c8b0b


# $ docker exec -it back_mysql bash
-> un terminal interactif à l’intérieur d’un conteneur Docker : 
> bash-5.1# mysql -u root -p
