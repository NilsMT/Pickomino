# Point de vigilances de l'application

## Librairie pickomino-jar

Le mode debug nous permettra de simuler le serveur pour le besoin de nos tests.



Il nous permettra aussi de choisir les dés exacts que l'on veut obtenir pour nous permettre de tester nos méthodes dépendantes du résultat de ceux-ci sans avoir à subir l'aléatoire.



Les exceptions du companion "factory" nous permettront également de récuperer les erreurs liées è la classe Connector plus facilement.



La doc ne nous donne pas d'infos claires sur la privatisation des variables et méthodes ce qui reduit notre efficacité sur l'écriture de tests.  



## Application Pickomino

### Modele

Toutes les méthodes/variables du modèle citées auront besoin de getter/setter ou etre publiques ou avoir un stub pour pouvoir les moduler: 



    la variable nb_joueurs



    la variable est_local



    la variable key



    la variable id



En ce qui concerne les méthodes je ne peux pas encore en parler car pas encore totalement définis



### Vue

Les vues sont assez compliquées à tester à part visuellement donc les seules choses que l'on pourra tester sont les méthodes de celle-ci 



### Controleur

Les controleurs seront eux aussi assez compliqué à tester car pas encore définis et très variables de plus qu'ils agissent sur des évenements par exemple.




