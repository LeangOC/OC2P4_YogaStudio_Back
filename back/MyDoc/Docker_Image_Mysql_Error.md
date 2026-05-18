### Docker_Image_Mysql_Error.md

Quand je veux démarrer mon image "mysql:latest" manuellement : 
"$ docker run mysql:latest 2026-05-09 10:04:53+00:00 [Note] [Entrypoint]: 
Entrypoint script for MySQL Server 9.7.0-1.el9 started. 2026-05-09 10:04:54+00:00 [Note] [Entrypoint]: 
Switching to dedicated user 'mysql' 2026-05-09 10:04:54+00:00 [Note] [Entrypoint]: 
Entrypoint script for MySQL Server 9.7.0-1.el9 started. 2026-05-09 10:04:54+00:00 [ERROR] [Entrypoint]: 
Database is uninitialized and password option is not specified You need to specify one of the following 
as an environment variable: - MYSQL_ROOT_PASSWORD - MYSQL_ALLOW_EMPTY_PASSWORD - MYSQL_RANDOM_ROOT_PASSWORD"  

C’est un comportement normal de MySQL dans Docker 👍

Ton erreur est très claire :

---

# ❌ Problème

```bash
Database is uninitialized and password option is not specified
```

👉 Ça veut dire :

* tu lances MySQL pour la première fois dans un conteneur
* il n’y a **aucune base initialisée**
* donc MySQL exige un mot de passe root obligatoire

---

# 🧠 Pourquoi ça arrive ?

Quand tu fais :

```bash
docker run mysql:latest
```

Docker crée un conteneur **neuf**, avec :

* aucun volume
* aucune base
* aucun utilisateur

👉 MySQL refuse de démarrer sans configuration de sécurité.

---

# 🔐 Solution obligatoire

Tu dois fournir au minimum :

```bash
MYSQL_ROOT_PASSWORD
```

---

# ✅ Solution simple (recommandée)

```bash id="fix1"
docker run -e MYSQL_ROOT_PASSWORD=root mysql:latest
```

---

# 💡 Autres options possibles

## 1. Mot de passe vide (⚠️ pas recommandé)

```bash id="fix2"
docker run -e MYSQL_ALLOW_EMPTY_PASSWORD=yes mysql:latest
```

---

## 2. Mot de passe aléatoire généré

```bash id="fix3"
docker run -e MYSQL_RANDOM_ROOT_PASSWORD=yes mysql:latest
```

---

# 🚀 Version propre (avec port + accès externe)

```bash id="fix4"
docker run \
  -e MYSQL_ROOT_PASSWORD=root \
  -p 3306:3306 \
  mysql:latest
```

---

# 🧠 Pourquoi ton docker-compose marchait avant ?

Parce que dans ton `docker-compose.yml`, tu avais sûrement :

```yaml id="compose1"
environment:
  MYSQL_ROOT_PASSWORD: root
```

👉 donc Docker savait comment initialiser la base.

---

# ⚠️ Point important

* `docker run mysql:latest` seul ❌ = incomplet
* MySQL exige toujours un password au premier lancement
* sinon il bloque volontairement (sécurité)

---

# 💡 Image mentale

```id="img"
MySQL container vide 🧱
        ↓
“Je n’ai aucune sécurité définie”
        ↓
❌ je refuse de démarrer
```

---

# 🧾 Résumé simple

| Commande                                      | Résultat          |
| --------------------------------------------- | ----------------- |
| `docker run mysql`                            | ❌ erreur password |
| `docker run -e MYSQL_ROOT_PASSWORD=... mysql` | ✔ OK              |
| docker-compose avec env                       | ✔ OK              |

---

# 👍 Si tu veux aller plus loin

Je peux t’expliquer :

* comment créer un conteneur MySQL prêt pour Spring Boot
* comment mapper un volume correctement
* ou comment éviter de perdre tes données entre runs

Dis-moi 👍


Et quand je fais la commande "docker ps -a" : "$ docker ps -a
CONTAINER ID   IMAGE          COMMAND                  CREATED          STATUS                      PORTS     NAMES
ea0045297126   mysql:latest   "docker-entrypoint.s…"   52 seconds ago   Exited (1) 50 seconds ago             awesome_nightingale

Là on voit exactement ce qui s’est passé 👍

---

# 🧠 Lecture de ton résultat

```bash id="ps1"
CONTAINER ID   IMAGE          STATUS
ea0045297126   mysql:latest   Exited (1)
```

Tu as lancé un conteneur MySQL qui :

* a démarré
* a échoué immédiatement
* s’est arrêté

---

# ❌ Pourquoi il s’arrête (Exited 1)

C’est la conséquence directe de ton erreur précédente :

```bash id="err1"
MYSQL_ROOT_PASSWORD is not specified
```

👉 Donc :

1. MySQL démarre
2. détecte qu’il n’a pas de mot de passe root
3. refuse de continuer
4. le conteneur se termine avec code **1 (erreur)**

---

# 📦 Ton conteneur actuel

```bash id="ct1"
awesome_nightingale
```

👉 C’est un nom généré automatiquement par Docker

---

# 🧠 Ce que ça signifie concrètement

| Élément            | État                         |
| ------------------ | ---------------------------- |
| Image mysql:latest | OK                           |
| Conteneur          | créé mais cassé              |
| Base de données    | inexistante                  |
| Volume             | probablement non utilisé ici |

---

# 🚀 Comment corriger ça

Tu dois relancer avec un mot de passe :

```bash id="fix1"
docker run -e MYSQL_ROOT_PASSWORD=root mysql:latest
```

---

## 🔥 Version correcte + accès port

```bash id="fix2"
docker run -d \
  -e MYSQL_ROOT_PASSWORD=root \
  -p 3306:3306 \
  mysql:latest
```

---

# 🧨 Pourquoi ton conteneur échoue systématiquement

Parce que :

👉 MySQL refuse de démarrer sans sécurité initiale

C’est une règle stricte du conteneur officiel.

---

# 🧪 Comment vérifier le problème

Tu peux voir les logs exacts :

```bash id="log1"
docker logs ea0045297126
```

Tu verras le même message d’erreur que précédemment.

---

# 🧠 Image mentale

```id="img1"
Conteneur MySQL 🧱
   ↓
Pas de mot de passe
   ↓
❌ sécurité bloquante
   ↓
💥 arrêt immédiat (Exited 1)
```

---

# 💡 Point important (lié à ton projet)

Tu avais déjà :

* un volume `back_db_data`
* une stack docker-compose fonctionnelle

👉 Donc ton erreur vient uniquement du fait que tu lances MySQL “à la main” sans config.

---

# 🧾 Résumé simple

* ✔ image OK
* ❌ conteneur échoue
* ❌ cause : pas de `MYSQL_ROOT_PASSWORD`
* ✔ solution : ajouter variables d’environnement

---

# 👍 Si tu veux aller plus loin

Je peux t’aider à :

* transformer ton `docker run` en équivalent `docker-compose`
* ou corriger proprement ton environnement Spring Boot + MySQL
* ou éviter ce type d’erreur définitivement

Dis-moi 👍
