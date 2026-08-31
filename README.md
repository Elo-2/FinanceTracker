# Finance Tracker

Java aplikacija za praćenje ličnih finansija razvijena korištenjem **Java Swing GUI-a**, **MongoDB Atlas** baze podataka i objektno-orijentisanog pristupa.

## Opis projekta

Finance Tracker omogućava korisniku da jednostavno evidentira i upravlja svojim prihodima i rashodima.

Aplikacija omogućava:

* dodavanje novih transakcija
* pregled svih transakcija
* odabir vrste transakcije
* odabir kategorije
* ažuriranje postojeće transakcije
* brisanje transakcije uz potvrdu
* automatsko računanje ukupnih prihoda
* automatsko računanje ukupnih rashoda
* prikaz trenutnog salda
* export podataka u tekstualni fajl
* trajno čuvanje podataka u MongoDB Atlas bazi

## Tehnologije

* **Java**
* **Java Swing**
* **IntelliJ IDEA**
* **Swing UI Designer**
* **MongoDB Atlas**
* **MongoDB Java Driver**
* **OOP (Object-Oriented Programming)**

## Struktura projekta

```text
FinanceTracker
│
└── src
    └── financeapp
        ├── Main.java
        ├── FinanceTrackerForm.java
        ├── FinanceTrackerForm.form
        ├── Transaction.java
        ├── TransactionManager.java
        └── MongoDBConnection.java
```

## Glavne klase

### Main.java

Glavna klasa aplikacije koja pokreće Finance Tracker GUI.

### FinanceTrackerForm.java

Glavna Swing forma aplikacije.

Sadrži:

* polja za unos iznosa i opisa
* padajući meni za vrstu transakcije
* padajući meni za kategoriju
* tabelu transakcija
* dugme za dodavanje
* dugme za ažuriranje
* dugme za brisanje
* dugme za export
* prikaz prihoda
* prikaz rashoda
* prikaz salda

### Transaction.java

Model klase koji predstavlja jednu finansijsku transakciju.

Svaka transakcija sadrži:

* ID
* vrstu
* kategoriju
* iznos
* opis

### TransactionManager.java

Klasa zadužena za komunikaciju sa MongoDB bazom.

Omogućava:

* dodavanje transakcija
* čitanje svih transakcija
* ažuriranje transakcije
* brisanje transakcije
* računanje ukupnog prihoda
* računanje ukupnog rashoda

### MongoDBConnection.java

Klasa koja uspostavlja vezu između Java aplikacije i MongoDB Atlas baze podataka.

## Kategorije

Aplikacija podržava sljedeće kategorije:

* Plata
* Hrana
* Računi
* Zabava
* Prijevoz
* Ostalo

## Vrste transakcija

Korisnik može odabrati:

* Prihod
* Rashod

## MongoDB

Podaci se čuvaju u MongoDB Atlas bazi podataka.

Korištena je baza:

```text
financeTrackerDB
```

i kolekcija:

```text
transactions
```

Primjer dokumenta:

```json
{
  "Vrsta": "Prihod",
  "Kategorija": "Plata",
  "Iznos": 1500,
  "Opis": "Mjesečna plata"
}
```

## Ažuriranje transakcije

Korisnik može odabrati postojeći red u tabeli.

Nakon odabira transakcije:

1. Podaci se učitavaju u polja za unos.
2. Korisnik može promijeniti vrstu.
3. Može promijeniti kategoriju.
4. Može promijeniti iznos.
5. Može promijeniti opis.
6. Klikom na **Ažuriraj** podaci se mijenjaju u MongoDB Atlas bazi.
7. Tabela se automatski osvježava.

Transakcija se identifikuje pomoću MongoDB `_id` vrijednosti.

## Brisanje transakcije

Korisnik odabire transakciju i klikne na **Briši**.

Aplikacija prikazuje poruku za potvrdu:

```text
Jeste li sigurni da želite izbrisati ovu transakciju?
```

Nakon potvrde transakcija se briše iz MongoDB baze i tabela se osvježava.

## Export

Aplikacija omogućava export finansijskog izvještaja u tekstualni format.

Izvještaj sadrži:

```text
Ukupni prihod: 1500
Ukupni rashod: 900
Stanje: 600

Rashodi po kategorijama:
Hrana: 200
Prevoz: 150
Zabava: 100
Računi: 450
```

## Pokretanje projekta

Za pokretanje projekta potrebno je:

1. Instalirati JDK.
2. Instalirati IntelliJ IDEA.
3. Otvoriti projekat.
4. Dodati MongoDB Java Driver.
5. Podesiti MongoDB Atlas konekciju.
6. Pokrenuti `Main.java`.

## MongoDB Atlas konekcija

U klasi `MongoDBConnection.java` potrebno je postaviti vlastiti MongoDB Atlas connection string.

Connection string se ne treba javno objavljivati na GitHubu jer može sadržavati korisničko ime i lozinku baze podataka.

## Cilj projekta

Cilj projekta je demonstrirati primjenu:

* Java programiranja
* objektno-orijentisanog programiranja
* Java Swing GUI-a
* Swing UI Designera
* rada sa MongoDB bazom podataka
* CRUD operacija
* rada sa tabelama
* obrade korisničkog unosa
* računanja finansijskih podataka
* exporta podataka

## Autor

**Finance Tracker - Projekat 1**

Java Edition
