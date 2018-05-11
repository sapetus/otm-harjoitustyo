

## Space Invaders
Klassinen Space Invaders -henkinen peli. 11x5 vihollista liikkuvat edes takaisin alustalla, joita pelaajan tulee ampua ja näin kerätä pisteitä. Pisteistä pidetään 
kirjaa e$

## Dokumentointi
[tuntikirjanpito](https://github.com/sapetus/otm-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

[vaativusmäärittely](https://github.com/sapetus/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[arkkitehtuuri](https://github.com/sapetus/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

## Releaset


## Komentorivi toiminnot
#### sovelluksen suorittaminen
mvn compile exec:java -Dexec.mainClass=spaceinvaders.ui.SpaceInvadersUI
#### testit
mvn test
###### testiraportin luominen
mvn test jacoco:report

löytyy: /target/site/jacoco/index.html
#### jarin generointi
mvn package
###### jarin suorittaminen
/target kansiossa java -jar spaceinvaders-1.0-snapshot.jar
### Javadoc
mvn javadoc:javadoc

löytyy /target/site/apidocs/index.html
#### Checkstyle
###### tarkistaminen
mvn jxr:jxr checkstyle:checkstyle

löytyy: /target/site/checkstyle.html

