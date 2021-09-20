# Finance Tracker - Önálló laboratórium 1

## Specifikáció

### Követelmények

- felhasználókezelés, regisztáció, authentikáció
- mindenkinek lesz egy fiókja és azon belül lehet nyitni zsebeket (pocket), amiket meg lehet osztani más felhasználókkal és így közösen is lehet vezetni egy-egy zsebet
- kiadások, bevételek, megtakarítások kezelése (mennyit, hol, kategória, ki stb.)
- pénzügyek kategorizálása és ebből kimutatások (pl.: előző hónapban milyen arányban voltak a költekezések)
- kiadások tervezése (napi, havi maximum költekezés)
- egyéb: valuta váltó, kifizetési határidőkhöz naptár és emlékeztetők, 

### Technológiák és megvalósítási tervek

- adatbázis: Postgres
- backend: Spring Boot
- frontend: Angular
- authentikáció: JWT tokennel
