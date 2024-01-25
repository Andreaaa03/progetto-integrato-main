#Istruzzioni per avvio applicazione

##Impoirt database
###Requisiti
####XAMPP

###Import
Aprire il proprio brawser e andiamo a questo URL:
http://localhost/phpmyadmin/

Andare su import all interno della pagina
importare il file nba.sql al interno della cartella principale del progetto
database aggiornato al 25/01/2024(dati utente fittizzi)

##Avvio applicazione java
###Requisiti
####Java JDK-21
Windows https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.exe
####Maven(```Linux linea di comando```)
```mvnw clean install```

##Avvio
####Eclipse

Aprire il progetto Maven nell'IDE.
Fare clic con il pulsante destro del mouse sul progetto e selezionare "Run As" > "Maven Build".
Nella finestra "Edit Configuration", digitare package nella casella "Goals".
Fare clic su "Run".

####IntelliJ IDEA

Aprire il progetto Maven nell'IDE.
Fare clic con il pulsante destro del mouse sul progetto e selezionare "Run" > "Maven Build".
Nella finestra "Run/Debug Configuration", selezionare "package" dal menu a discesa "Goals".
Fare clic su "Run".

####Dalla riga di comando(```Linux```)

Aprire un terminale o una finestra del prompt dei comandi.
Passare alla directory del progetto.
Eseguire il seguente comando:
```mvnw spring-boot:run```


