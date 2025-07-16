# pickomino-server


<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Licence Creative Commons" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/80x15.png" /></a><br />Ce(tte) œuvre est mise à disposition selon les termes de la <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Licence Creative Commons Attribution - Pas d’Utilisation Commerciale - Partage dans les Mêmes Conditions 4.0 International</a>.

Auteur : Arnaud Lanoix Brauer

Utilise [Ktor](https://ktor.io/)

Documentation Pickomino
- https://www.scifi-universe.com/critiques/6515/pickomino
- https://www.gigamic.com/jeu/pickomino

## compile 

exécuter la tâche gradle `buildFatJar`

##  run

dans `build/libs/`, exécuter la commande `java -jar iut.pickomino.pickomino-server-all.jar`
 


## TODO list

- [ ] revoir certaines routes en POST/PUT plutôt qu'en GET
- [X] règles : quand on remet un pickomino, si c'est le dernier, on ne le "grille" pas
- [X] règles : on n'est pas obligé de prendre un pickomino chez un autre joueur
- [ ] ajouter une "vraie" gestion d'utilisateurs





## API documentation 

Documentation des routes

- GET `/game`
    - `OK` : donne toutes les parties
    - `NotFound` : pas de partie trouvée

- GET `/game/{id}/{key}`
    - `OK` : donne la partie identifiée par `id`
    - `BadRequest` : `id` or 'key' manquant
    - `NotFound` : pas de partie identifiée par `id`
    - `NotAcceptable` : mauvais format pour `id`
    - `Unauthorized` : incorrect `key`

- GET `/game/new/{players}`
    - `Created` : ajoute une nouvelle partie pour `players` joueurs et donne son `id`

  `{"id":44, "key"=142}`

    - `BadRequest` : `players`manquant
    - `Forbidden` : `players` < 2 ou > 4
    - `NotAcceptable` : mauvais format pour `players`

- GET `/roll/{id}/{key}`
    - `OK` : donne une liste de dés lancés aléatoirement

  `["d4","d4","d1","worm","d3","d3","worm","d1"]`

    - `BadRequest` : `id`manquant
    - `NotFound` : pas de partie identifiée par `id`
    - `NotAcceptable` : mauvais format pour `id`
    - `MethodNotAllowed` : pas le moment de lancer les dés
    - `Unauthorized` : incorrect `key`

- POST `/roll/debug/{id}/{key}` ;  `body = ["d4","d4","d1","worm","d3","d3","worm","d1"]`
    - `OK` : donne les 'n' premiers dés passés en POST
    - `BadRequest` : `id` manquant
    - `NotFound` : pas de partie identifiée par `id`
    - `NotAcceptable` : mauvais format pour `id`
    - `MethodNotAllowed` : pas le moment de lancer les dés
    - `Unauthorized` : incorrect `key`

- GET `/keep/{id}/{key}/{dice}`
    - `OK` :
    - `BadRequest` : `id` manquant
    - `NotFound` : pas de partie identifiée par `id`
    - `NotAcceptable` : mauvais format pour `id`
    - `BadRequest` : `dice` manquant
    - `NotAcceptable` : mauvais format pour `dice`
    - `NoContent` : `dice` pas dans le lancer
    - `Conflict` : `dice` déjà gardé précédemment
    - `MethodNotAllowed` : pas le moment de garder des dés
    - `Unauthorized` : incorrect `key`

- GET `/take/{id}/{key}/{picko}`
    - `OK` :
    - `BadRequest` : `id` manquant
    - `NotFound` : pas de partie identifiée par `id`
    - `NotAcceptable` : mauvais format pour `id`
    - `BadRequest` : `picko` manquant
    - `NotAcceptable` : mauvais format pour `picko`
    - `Conflict` : mauvais `picko`choisi
    - `MethodNotAllowed` : pas le moment de prendre un pickomino
    - `Unauthorized` : incorrect `key`