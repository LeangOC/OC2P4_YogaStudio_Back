# Docker_Conteneur_Terminal.md
=> tu peux m'expliquer ce que fait cette commande "$ docker exec -it back_mysql bash"

Oui. Cette commande Docker :

```bash
docker exec -it back_mysql bash
```

sert à **ouvrir un terminal interactif à l’intérieur d’un conteneur Docker déjà en cours d’exécution**.

Décomposition :

* `docker exec`
  → exécute une commande dans un conteneur actif.

* `-it`
  → combinaison de deux options :

    * `-i` = mode interactif (garde l’entrée standard ouverte)
    * `-t` = alloue un pseudo-terminal
      Ensemble, ça permet d’avoir un shell “normal” dans le conteneur.

* `back_mysql`
  → nom (ou ID) du conteneur cible.
  Ici, probablement un conteneur MySQL de ton projet backend.

* `bash`
  → la commande lancée dans le conteneur.
  Ici, on démarre un shell Bash.

Concrètement, après cette commande, ton terminal change souvent de forme :

```bash
root@a1b2c3d4:/#
```

et tu es “dans” le conteneur. Tu peux alors :

* voir les fichiers :

  ```bash
  ls
  ```

* inspecter la config :

  ```bash
  cat /etc/mysql/my.cnf
  ```

* lancer le client MySQL :

  ```bash
  mysql -u root -p
  ```

* vérifier des logs/processus :

  ```bash
  ps aux
  ```

Pour sortir du conteneur :

```bash
exit
```

ou `Ctrl + D`.

Petit détail utile : certains conteneurs minimalistes n’ont pas `bash`. Dans ce cas, on utilise souvent :

```bash
docker exec -it back_mysql sh
```

Tu veux aussi que je t’explique la différence entre `docker exec`, `docker run` et `docker attach` ?
