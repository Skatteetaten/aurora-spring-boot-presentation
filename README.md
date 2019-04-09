# aurora-spring-boot-presentation

Dette repoet inneholder code for slides og eksempler i forbindelse med kurset "Introduksjon til Spring Boot"

Presentasjonen er publisert her:

* https://skatteetaten.github.io/aurora-spring-boot-presentation/index.html

Notater/manuskript for gjennomføring finnes her: https://skatteetaten.github.io/aurora-spring-boot-presentation/script.html 

I mappen "examples" finnes kode for flere av eksemplene som ble gjennomgått.

Presentasjonen finnes i "presentation"-mappen og er laget med reveal.js. Les README.md for mer informasjon om hvordan den kan bygges.

Manuskriptet for presentasjonen ligger under "notes" og er skrevet med asciidoc. Les README.md for informasjon om hvordan den kan bygges til html og pdf.

Dokumentasjonen kan republiseres med

    ./gradlew gitPublishPush
  
Det fordrer at dokumentasjonen er ferdig bygget og auth er satt opp riktig via miljøvariablene GRGIT_USER og GRGIT_PASS.

    export GRGIT_USER=...
    export GRGIT_PASS=(cat ~/.github_pass) # fish shell
    export GRGIT_PASS=$(cat ~/.github_pass) # sh