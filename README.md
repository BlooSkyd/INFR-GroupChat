# INFR-GroupChat

## Auteurs
Le projet a été réalisé par **John WANG** et **Clément TAURAND**

## Fonctionnement
Deux programme sont a exécuter : `Receiver.java` et `Sender.java`
Pour cela, il vous suffit d'éxécuter les commandes suivantes (pas d'ordre précis sauf pour le premier) :
1. `javac *.java` (ça a déjà été fait, mais ça ne coûte rien)
- `java Receiver`
- `java Sender <pseudo>` Attention, ne pas oublier ici de renseigner un pseudo sinon vos messages seront très probablement mal reçus par vos correspondant.

## Information

Par défaut (tel que livré), les programmes affiches les messages de debug.
Il est possible de changer cela dans les fichiers `Receiver.java` et `Sender.java`

## Présentation sommaire des classes

La classe `Message` est uniquement dédiée à gérer la structure d'envoie et de réception des messages.
La classe `CarnetAdresse` s'occupe simple de "charger" les adresses des destinataires via le fichier `IP.txt`.

La classe `Receiver` s'occupe seulement de la réception et délègue le traitement à `IOMessaageHandler`.

La classe `Sender` ennvoie à chaque destinataire récupéré via `CarnetAdresse`, en formattant les messages avec `IOMessageHandler`. La classe intercepte aussi les possibles *commandes* envoyées par l'utilisateur.

Enfin, la classe IOMessageHandler s'occuper de l'ensemble du traitement des messages :
- analyse et vérifications du formatage ;
- gestion "des" commandes ;
- gestion de l'affichage du debug...