### Docker_Mysql_double_ports.md 
Au niveau de mon conteneur mysql docker, pourquoi il y a deux ports d'écoute 3306 et 33060 ? :

C’est normal avec l’image officielle MySQL.

Il faut distinguer :

* les ports **exposés par l’image Docker**
* les ports **publiés vers ta machine hôte**

Dans ton cas :

```bash
docker ps
...
0.0.0.0:14044->3306/tcp
```

ça signifie :

* le port **3306** du conteneur est accessible depuis ton PC via le port **14044**
* donc tu te connectes à MySQL avec :

  ```bash
  localhost:14044
  ```

---

Le résultat :

```bash
docker inspect mysql:latest | grep 3306
"3306/tcp": {},
"33060/tcp": {}
```

vient du fait que l’image `mysql:latest` déclare deux ports `EXPOSE` :

| Port  | Usage                     |
| ----- | ------------------------- |
| 3306  | protocole MySQL classique |
| 33060 | MySQL X Protocol          |

### 3306

C’est le port standard utilisé par :

* JDBC
* PHPMyAdmin
* MySQL CLI
* DBeaver
* Hibernate
* etc.

### 33060

C’est un port plus récent utilisé par le **MySQL X Protocol** :

* API Document Store
* clients modernes MySQL X DevAPI
* communication type NoSQL/JSON

La plupart des projets classiques ne l’utilisent jamais.

---

Important :

Dans ton `docker ps`, seul :

```bash
14044->3306
```

apparaît.

Donc :

* **3306 est publié**
* **33060 ne l’est pas**

Le port 33060 existe seulement **à l’intérieur du conteneur**, pas sur ta machine.

---

Tu peux vérifier avec :

```bash
docker port back_mysql
```

Tu verras probablement :

```bash
3306/tcp -> 0.0.0.0:14044
```

et rien pour `33060`.

---

Si un jour tu voulais aussi exposer 33060 :

```yaml
ports:
  - "14044:3306"
  - "14045:33060"
```

mais dans 99 % des cas ce n’est pas nécessaire.
