# DD2480-group1-assignment2
Continouos integration. This repo will contain source code for an CI server

Status February 11th 2021
The Main branch now includes a HTML file filled with mocked functionality, it is not yet connected to the database and does not display dynamic data.
The branch also contains the smallest-java-CI-server and is able to compile with Maven.

Pull requests that have not yet been merged are:
1. The integration of the database, because there are issues with accessing it (authentication is needed)
2. Webhook, because we are waiting for the pull request to be reviewed.
3. Email functionality, since this needs to be linked with the incoming POST notification. (Cant be done until 2. is merged)


To start CI server

  $ mvn clean compile assembly:single

  $ java -cp target/CI-1.0-SNAPSHOT-jar-with-dependencies.jar group1.Main

  $ ./ngrok http 8080

To run email.java and send an email to Robin

  $ mvn clean compile assembly:single

  $ java -cp target/CI-1.0-SNAPSHOT-jar-with-dependencies.jar group1.email


Statement of contribution:
Robin:
Joakim:
Caroline:
Anton:
Olof:
