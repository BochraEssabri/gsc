
#GSC - Gestion stations carburants

## Résumé sur le projet GSC

Plus de détails et démonstration sont disponibles [ici](https://drive.google.com/file/d/1zQh6br8H__KviRTrARl1XU_A6GHcLrj1/view?usp=sharing)

Ce projet est réalisé en binome dans le cadre de mes études en master systèmes informatiques et mobiles, module applications distribuées.
- GSC est une application Java EE distribuée sous forme de plusieurs applications de différentes technologies ( application web, WS Rest, WS Soap, et Persistance avec JPA ) qui permet d'assurer la bonne gestion des carburants ( Essence, Gazole...) sur plusieurs stations au Maroc .
- Elle possède une IHM Web sécurisée, qui se communique avec des web services Rest sécurisées à fin de permet à un utilisateur de faire une recherche sur les prix de carburants en fonction d'une date, une station et une ville. Ces deux éléments forment la partie rest-Jersey de notre projet ( comme l'indique son, elle a été réalisée à l'aide de Jersey)
- La partie Rest communique avec la partie WS SOAP pour récupérer les données concernant les prix des carburants et d'autres informations. C'est une sorte de simulation d'une communication avec une application externe.
Cette application est aussi sécurisée via une clé de type AUTH1, et se communique avec la partie Persistance pour récupérer des données à partir de la base de données. Cette partie est utilisée comme étant une dépendance Maven et fonctionne à l'aide de JPA ( Implémentation Hibernate ) pour faciliter la communication avec la base de données.

- Dernièrement, on a réalisé une partie commune qui contient les ressources partagées entres tous les autres projets.

## Plus de détails sur les technologies utilisées
Dans le cadre de la réalisation de ce projet, nous avons utilisées plusieurs d'autres technologies comme par exemple: WSImport (plugin de maven pour générer un client SOAP à partir d'une URL vers le fichier WSDL), Log4J pour tracer les messages, Postman pour réaliser des tests sur les WS Rest, SOAPUI pour des tests SOAP, Bootstrap ( un framwork front end pour faire le design de l'IHM web), Jquery pour tous ce qu'est dynamisation côté front et communication avec les WS Rest à partir du navigateur, Tomcat pour déployer l'application web, MySQL pour la gestion des base de données, Maven pour tous ce qu'est gesttion ...


## Versions du projet
- Ceci est une version modifiée de notre projet original, dans laquelle j'ailiées toutes les parties du projet.
- L'objectif principal de ce projet était la réalisation de chaque partie indépendante des autres. 
- Il y avait aussi une partie supplémentaire qui montre une communication entre deux applications: Application web ( page Jsp) et Services Web Rest en utilisant l'implémentation de Jersey, et une deuxième implémentation de JaxRs qu'est différente de celle-ci( nous avons choisie RestEasy).

## Documentation détaillée et démonstration
- Pour consulter la documentation contenant plus de détails et démonstration des deux version du projet GSC, [cliquer ici](https://drive.google.com/file/d/1zQh6br8H__KviRTrARl1XU_A6GHcLrj1/view?usp=sharing)

