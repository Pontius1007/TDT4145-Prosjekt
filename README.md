# TDT4145-Prosjekt
Databaseprosjekt for TDT4145 

## Setup
Gjør livet lett - bruk IntelliJ. 

Last ned [SQLite JDBC Driver](https://bitbucket.org/xerial/sqlite-jdbc/downloads/), deretter gå inn i...

`File -> Project Structure -> Libraries -> + -> Java`

Velg deretter driveren og trykk OK. 

## Jar
Lyst på en JAR fil?

`File -> Project Structure -> Project Settings -> Artifacts -> Jar -> From modules with dependencies...`

I dialogen som følger, velg hovedklassen (Diary) og hvis du ønsker kan du huke av for at JAR filen skal oppdateres hver gang koden bygger (ca. hver gang du kjører koden)

## Feilsøking
Klarer du ikke å kjøre koden i IntelliJ? Her er noen klassiske feil.

1. Marker src som Source Root ved å høyreklikke på mappen i prosjektviseren (Alt + 1), deretter naviger ned til "Mark Directory As -> Source Root"
2. Konfigurer en output-mappe ved å gå inn i Project Structure menyen.
3. Spør Anders om hjelp.
