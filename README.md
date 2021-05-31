# Logikai algoritmusok beadandó
A repo egy Maven projektet tartalmaz, benne a két beadandó feladat megoldásával:
1. Konstruáljon egy módszert a 3-színezési probléma megfogalmazására BDD/ZDD feladatként.
2. Fogalmazza meg SAT problémaként az Einstein/Zebra rejtvényt.

## 3-színezési probléma:
A feladat, hogy megmondjuk egy gráfról, hogy ki lehet-e színezni a csúcsait 3 színnel, azzal a feltétellel, hogy ha két csúcs között van egy él akkor azok különböző színűek.  
A gráf definiálásához készült egy GraphNode (a csúcsokhoz) és egy GraphEdge (az élekhez) osztály. Hogy egyszerűbb legyen tesztelni, az Util-ban megadtam két előre definiált gráfot.A ColoringProblem.java egy ilyen módon megadott gráfról tudja eldönteni a színezhetőségét a javabdd segítségével.
A program kimenetként megmondja, hogy hány féle színezési mód van, és ha létezik ilyen akkor ad rá egy példát.

## Zebra rejtvény
A feladat a jól ismert Einstein/Zebra rejtvényt megoldása egy SAT solverrel, én erre a sat4j-t használtam.  
A folyamat a változók létrehozásával kezdődik, ezek a Container-ben jönnek létre, minden ház esetén kell egy igaz/hamis érték az egyes attribútumokra. Pl.: Az első ház zöld, A második házban az angol lakik, stb. Mivel 5 házunk van és 25 attribútumunk, így 5 * 25 = 125 változóra van szükség. A ZebraPuzzle.java tartalmazza a lényeget, külön a DIMACS formátum nem lett legeneráltatva, közvetlen clause-k kerültek meghatározásra a szabályok mentén.
A program a rejtvény megoldásának kiírásával zárul le.


