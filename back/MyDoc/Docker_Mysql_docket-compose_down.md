### Docker_Mysql_docket-compose_down.md
La commande correcte est :

```bash
docker-compose down
```

(et pas *docker-composer*, qui est une faute de frappe fréquente 🙂)

---

## Ce que fait exactement `docker-compose down`

Cette commande sert à **arrêter et nettoyer complètement une stack Docker Compose**.

Dans ton cas, la sortie montre :

```
✔ Container back_mysql  Removed
✔ Network back_default  Removed
```

---

## Décomposition précise

Quand tu exécutes :

```bash
docker-compose down
```

Docker Compose fait plusieurs choses :

### 1. Arrêt des conteneurs

Tous les conteneurs définis dans ton `docker-compose.yml` sont :

* stoppés (si encore en cours)
* puis supprimés

Exemple ici :

* `back_mysql` est supprimé

---

### 2. Suppression des conteneurs

Contrairement à `stop`, ici les conteneurs ne sont pas conservés :

* `docker stop` ➜ arrête seulement
* `docker-compose down` ➜ arrête + supprime

---

### 3. Suppression du réseau créé par Compose

Docker Compose crée automatiquement un réseau du type :

```
back_default
```

Ce réseau permet aux services de communiquer entre eux.

Il est aussi supprimé ici :

```
✔ Network back_default Removed
```

---

### 4. Ce qui n’est PAS supprimé par défaut

Par défaut, `down` ne supprime pas :

* les volumes (données MySQL par exemple)
* les images
* les fichiers locaux

👉 Donc ta base de données peut rester intacte si elle est dans un volume.

---

## Option importante : `-v`

Si tu fais :

```bash
docker-compose down -v
```

Là tu supprimes aussi :

* les volumes
* donc les données MySQL (⚠️ reset complet)

---

## Résumé simple

| Commande                 | Effet                                 |
| ------------------------ | ------------------------------------- |
| `docker-compose stop`    | arrête les conteneurs                 |
| `docker-compose down`    | arrête + supprime conteneurs + réseau |
| `docker-compose down -v` | idem + supprime les données (volumes) |

---

## Dans ton cas

Tu avais :

* un conteneur MySQL (`back_mysql`)
* un réseau (`back_default`)

Donc `down` a simplement :

👉 supprimé toute la stack définie dans ton Compose

---

Si tu veux, je peux aussi t’expliquer la différence entre `docker run`, `docker-compose up` et `docker compose (v2)` parce que ça crée souvent de la confusion.
