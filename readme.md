
# Pré requis
* JVM 1.8 minimum
* Maven 3
* Un vrai shell (comme bash)

# Documentation
## Fonctionnelle
[Tax Computation](documents/readme.md )
### Version 1.2
* Sysout from controller
* mvn exec:exec
* bugfixing

### Version 1.1
* First release
# Installation & exécution
* Récupération de sources `git clone https://github.com/christophe-martel/fa-tax-computation.git`
* Préparation
    * Etre au bon endroit `cd fa-tax-computation`
    * Un classique : `mvn clean install`
    * Des tests ? `mvn test`


# Exécution
## Méthode dite "La RACHE"
Cf. http://www.byatoo.com/la-rache/
* `mvn exec:java`

## How To... (planed for v1.2-STABLE)
* Préparation du package
    * `mvn package`
* Préparation du package & execution
    * `mvn package exec:java`
* Exécution
    * `java -jar target/cma-fa-tc-1.2-full.jar`


# Aide
En cas d'erreur, utiliser de la poudre verte
Cf. http://www.poudreverte.org/
