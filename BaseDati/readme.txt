UNIVERSITÀ DEGLI STUDI DI NAPOLI FEDERICO II
CORSO DI LAUREA IN INFORMATICA
INSEGNAMENTO DI BASI DI DATI (Gr1) I E OBJECT ORIENTED (Gr1)
ANNO ACCADEMICO 2023/2024


***************Traccia 3 – Unina Delivery***************


***************Autori***************

Gruppo OOBD2324_33
Fabio DI PIERNO – N86004852
Giuseppe DI MARTINO – N86004948

***************Docenti***************

Prof.ssa Mara SANGIOVANNI
Prof. Sergio DI MARTINO
Prof. Sergio DI MEGLIO


***************Testato su***************
Oracle Database v19c



***************Struttura dell'archivio***************

L'archivio è composto da 2 cartelle principali:
    -   Scripts
    -   bdd_dao

***************Folder Scripts***************
La cartella Scripts contiene:
    - Una cartella Navigation finalizzata alla consultazione e composta dalle seguenti sottocartelle:
        - Functions
        - Procedures
        - Tables
        - Triggers
        - Views

    - Una cartella SetupFolder finalizzata alla build della base di dati step by step e composta dalle seguenti sottocartelle:
        - 01.Setup
        - 02.AppDomain
        - 03.GeoDomain
        - 04.OrgDomain
        - 05.CustomerDomain
        - 06.ShipmentDomain

    - file BuildAll.sql per la build della base di dati in singola esecuzione con un set di dati a campione

***************Folder bdd_dao***************
La cartella bdd_dao contiene un esempio di classe D.A.O. nel linguaggio di programmazione Java.
Abbiamo scelto di presentare la classe OracleOrdineClienteDAO, che implementa l'interfaccia OrdineClienteDAO
per la gestione dell'ordine cliente.
Per poter rendere eseguibile l'applicazione abbiamo dovuto includere anche altre classi D.A.O.,
le abbiamo ridotte ai minimi metodi necessari affinché l'esempio potesse essere leggibile.


***************Istruzioni per l'installazione***************
Nella cartella bdd_dao è presente anche una cartella Wallet_BPK3181NCIYDUJMQ che consente di eseguire la connessione
alla nostra istanza Autonomous DB dell'OCI (Oracle Cloud Infrastructure).
Nel caso in cui volesse connettersi all'istanza per consultare la base di dati direttamente buildata in questa stessa
directory è presente un'immagine che illustra un esempio di connessione utilizzando l'IDE DataGrip di JetBrains.

Le credenziali di accesso sono:
username: uninaDelivery
password: UnidevProd4010$