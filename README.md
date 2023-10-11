# store-procedure
Simple command line Java program that runs a Postgres Stored Procedure

Compile: mvn compile

Package: mvn package

To run (Windows system):
mvn compile exec:java "-Dexec.args=dbUrl dbUser dbPassword"

You have to provide 3 parameters in order:

Change dbUrl for you jdbc database url

Change dbUser for your database user

Change dbPassword for your database password

For other OS it might be
mvn compile exec:java -Dexec.args="dbUrl dbUser dbPassword"