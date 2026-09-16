# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## Modules

The application has three modules.

- **Client**: The command line program used to play a game of chess over the network.
- **Server**: The command line program that listens for network requests from the client and manages users and games.
- **Shared**: Code that is used by both the client and the server. This includes the rules of chess and tracking the state of a game.

## Starter Code

As you create your chess application you will move through specific phases of development. This starts with implementing the moves of chess and finishes with sending game moves over the network between your client and server. You will start each phase by copying course provided [starter-code](starter-code/) for that phase into the source code of the project. Do not copy a phases' starter code before you are ready to begin work on that phase.

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared test`      | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

## Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```
[phase 2 diagram](https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZM6MFACeq3ETQBzGAAYAdAE5M9qBACu2AMQALADMABwATG4gMP7I9gAWYDoIPoYASij2SKoWckgQaJiIqKQAtAB85JQ0UABcMADaAAoA8mQAKgC6MAD0PgZQADpoAN4ARP2UaMAAtihjtWMwYwA0y7jqAO7QHAtLq8soM8BICHvLAL6YwjUwFazsXJT145NQ03PnB2MbqttQu0WyzWYyOJzOQLGVzYnG4sHuN1E9SgmWyYEoAAoMlkcpQMgBHVI5ACU12qojulVk8iUKnU9XsKDAAFUBhi3h8UKTqYplGpVJSjDpagAxJCcGCsyg8mA6SwwDmzMQ6FHAADWkoGME2SDA8QVA05MGACFVHHlKAAHmiNDzafy7gjySp6lKoDyySIVI7KjdnjAFKaUMBze11egAKKWlTYAgFT23Ur3YrmeqBJzBYbjObqYCMhbLCNQbx1A1TJXGoMh+XyNXoKFmTiYO189Q+qpelD1NA+BAIBMU+4tumqWogVXot3sgY87nae1t+7GWoKDgcTXS7QD71D+et0fj4PohQ+PUY4Cn+Kz5t7keC5er9cnvUexE7+4wp6l7FovFqXtYJ+cLtn6pavIaSpLPU+wgheertBAdZoFByyXAmlDtimGD1OEThOFmEwQZ8MDQcCyxwfECFISh+xXOgHCmF4vgBNA7CMjEIpwBG0hwAoMAADIQFkhRYcwTrUP6zRtF0vQGOo+RoFmipzGsvz-BwVygYKQH+iMykoKp+h-Ds0KPMB4lUEiMAIEJ4oYoJwkEkSYCkm+hi7jS+4MkyU76XOnl3kuwowGKEpujKcplu8So3gFDpJr6zpdhu7pbm5gpufUcDxCgIAampOyYCqwYagAkmgVAmkg64FQC27uR+Zn+sy0yXtASAAF4oBwUYxnGhQ6fCFn+pQJYwCiNgFAY6FDZUolpk4ACMBE5qoebzNBRYlvUPitXq7VdbsdFNulHm8iO9SHnIKDPvE56Xtew7xZUy4BmuAYPWlSXtoN9QOeKGSqABmCDSB1S6YR5bEaR3wUVR9YkahDZaQl8DIKmMC4fhoyQ9F0NI7Bl7w8hiO0Y2DGeN4fj+F4KDoDEcSJLT9MOb4WCiRl4Olg00gRvxEbtBG3Q9HJqgKcMcOIegM3aU1paS0hINy5znb1DZ9hs-d8FS2grnfWdC6jjAjJgLdWuUTr-nnc9Qr1KFT6ffIso1jrsXW4uFlWbdr761St78vUHAoNwx6XubxNW4b97BdIwdMoY3tfZ2P1y-UQP7krsIYajoEvJpXOYej2GY3hWbHRTTHUyi67+Ng4oavxaIwAA4kqGgc8N3PNwLwv2EqEtE67KOVL9xqD4roOey61loq3Obh5b9WCk99LG0yZsK+gkf7tHdvig7L7aM7MCQIrK8CjnSX1In8hLwbXkzzkc9qBi2+BS9wXN0ylYIC3SoeufMGqs-5zBlMgHId9GpZ1LM-MBaJM5fiATUBkbcYAACkyAtAAHIy1RvNEu2M+45nQZgnB9FTCU2Yv4DgAB2NwTgUBOBiBGYIcAuIADZ4ATkMM-IoRcxKJQktzVoHRe79z2hbJCWYiEoCwUqfOQjZbQPqJvNAMAADUMARgyLkXMUy0CVaWWnlddEz8MRwG4c-JyagXJ3z9nFVeJsN7jy3m7KOQU94ShvsAY+p9paAMvsA7xdiZD+1XiYlAZiZGPTCRfD+193qwK3AEqeyUZGlWkJAkeqdjZKgyQg8yncXhjHSdIFCi0FGJmTPwnCpccalPKeXRiVMAiWGDjZTYDMkAJDAG0vsEBOloIgOKEBhh-DJFAGqPhJQBEdmQY0JozIZI9BkQPbWUjRjYAQMANpUA4AQBslANYpTKnZ2ycose6z6aaJGFsnZlB9mHOOXk6Q+jEGpPqAAK2GWgMxQzxRWJQISGxetk73wumvU2YdVFvxtq9e2H1D5O0in4woKTBFGOSsE069j3ZGxNlEl5sLFzxJCvvUZEV5T5PRXMqySTb44tCQ4o2fgtCmKVBiaJ2hiVxNtpKbAbKeH-yTpilOFz04jgKdnIpWjTmzTRjM2p2MRhNModTLwOyuk9I1fKRAwZYDAGwFswgeR4wdwxZJXm-NBbC2MLg85X5LrcDwONIFzkpXwgxVZEAzqoCvxCefJ1+r-UBNJXql1bpVBrGflGxFV4RWDigY6mAEr+QeqQbpOVhdFUELLuTTAQA)
