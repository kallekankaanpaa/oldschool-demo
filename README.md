# 3D Starfield Demo

Tein demon yksin, sillä pikkuryhmässämme tuli viimehetkillä hieman erimielisyyksiä siitä kummalla pohjalla
harjoitustyö tehtäisiin. Eli toisin sanoen demoa oli tekemässä:
- Kalle Kankaanpää, 793456

## Miten demo rakennettiin

Aloitin demon tekemisen kopiomalla rotaatiopohjan Teamsista ja poistamalla siitä minulle tarpeettomat asiat ja
muokkaamalla sitä lähemmäs tarpeitani. Aluksi tein tähtien satunnaisgeneraation ja tähtien z arvon laskemisen
joka kierroksella. Kun tähdet oli saatu paikoilleen ja "liikkumaan" kohti katsojaa lisäsin makePic metodiin
x ja y koordinaattien muunnoksen. Sen jälkeen vuorossa oli tähden harmauden määrittäminen ja lopulta viimeisenä
lisäsin koordinaattien siirtymisen ympyrän kaarta pitkin, joka aiheuttaa pyörimisefektin.