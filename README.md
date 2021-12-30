# Oldskool demot

Demoja oli tekemässä
- Laura Talvio, 903518
- Kalle Kankaanpää, 793456

## Miten demon voi ajaa

Demon voi ajaa komennolla `sbt run`

## Miten demo rakennettiin

### 3D Starfield
Aloitin demon tekemisen kopiomalla rotaatiopohjan Teamsista ja poistamalla siitä minulle tarpeettomat asiat ja
muokkaamalla sitä lähemmäs tarpeitani. Aluksi tein tähtien satunnaisgeneraation ja tähtien z arvon laskemisen
joka kierroksella. Kun tähdet oli saatu paikoilleen ja "liikkumaan" kohti katsojaa lisäsin makePic metodiin
x ja y koordinaattien muunnoksen. Sen jälkeen vuorossa oli tähden harmauden määrittäminen ja lopulta viimeisenä
lisäsin koordinaattien siirtymisen ympyrän kaarta pitkin, joka aiheuttaa pyörimisefektin.

### Voxelimaisema
Ensin etsittiin valmis perlin noise-kuva, josta ohjelma laskee ja tallettaa vektori-kokoelmaan yksittäisten 
pixelien tummuudesta/vaaleudesta kyseiselle koordinaatile korkeuden tulevaan kuvaan. Korkeuden perusteellaa 
valitaan kyseiselle koordinatille myös väri. Ohjelma piirtää rivi kerralaan takaa eteen rivin suorakulmioita, 
joiden korkeus ja väri saadaan vektorista. Joka "piirtokierroksella" alkuperäisestä vektori kokoelmasta 
otetaan hieman eri kohdasta joukko vektoreita, jolloin maisema näyttää liikkuvan.
