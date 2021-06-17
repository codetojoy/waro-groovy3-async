
waro-groovy3-async
===========

The War-O [code kata](https://en.wikipedia.org/wiki/Kata_(programming)) in Groovy.

To run:

* install Gradle from http://gradle.org
* configure numCards, numGames and players in `config.groovy`
* type (or script): 
```
gradle run
```

Notes:

* uses Groovy 3.0.8
* Gradle wrapper 7.1
* tested with JDK 16 (AdoptOpenJDK 16.0.1.j9-adpt)

Goals:

* API/remote strategy (called in the background, via `CompleteableFuture`)
    - see [here](https://github.com/codetojoy/WarO_Strategy_API_Java) for implementation

Rules:
---------
Game rules are [here](Rules.md).
