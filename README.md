# OMO - Smart Factory

### [UML diagram (zip)](https://gitlab.fel.cvut.cz/zecmarce/omo_semestralka/blob/master/UML%20diagram/UMLdiagram.zip)

### Popis diagramu

##### Hlavný diagram

Hlavná entita systému je Factory */singleton/* .
Za výrobný proces zodpovedá trieda ProductionOperator.
Produkčný operátor má zoznam produkčných liniek a na ních spustí výrobu (o operátorovy a linke detailnejšie v Production diagrame a jeho popise).

Produkčná linka pošle neopracovaný produkt prvému stroju/robotu/človeku (interface LineWorker). Ten ho opracuje a posiela ďalej následujúcemu pracovníkovy */chain of responsibility/* .
Ak je všetko v poriadku tak posledný stroj vo výrobe je kontrolór, ktorý v prípade správneho počtu vyrobených výrobkov odošle event o ukončení výrobnej série (o strojov detailnejšie v popise diagrau Machines).

Eventy sa odosielajú do EventListu, ktorý ich uchováva a zároveň je observovaný EventOperatorom */observer/* .
EventOperator okamžite spracováva určité druhy eventov. V prípade Alert eventu požiada RepairmentPool o Repairman(a), aby ho poslal opraviť pokazený stroj */object pool/* .
Ak žiaden opravár nie je voľný tak si tento event uloží do zoznamu nevyriešených eventov. Ak príjme event o ukončení opravy tak zaradí opravára naspäť do RepairmentPool(u) a pozrie sa do zoznamu nevybavených eventov (o eventoch detialnejšie pri popise digramu Events).

Továreň má triedu FactoryTimer, ktorá má zoznam všetkých pracovníkom a pravidelne im bude aktualizovať čas (interface FactryWorker). 

V továrni sa nachádzajú aj ľudia, okrem už spomínaných opravárov sú to výrobný pracovníci, inšpektor a riaditeľ. 
Riaditeľ a inšpektor budú robiť návštevy prostredníctvom iterátorov továrne (viac v popise diagramu Factoryiterators).

V aplikácií je aj trieda FileManager, ktorá bude generovať konfiguračný report továrne a zároveň načítavať konfigráciu továrne.

##### Production diagram

ProdutionOperator má výrobný plán továrne, v ňom sú všetky naplánované a ukončené výrobné série.
Výrobná séria v sebe nesie informáciu o množstve výrobkov a referencie na ProductFactory a LineBuilder */factory method a builder/* .
Nové série vytvára ProdactionOperátor pomocou SeriesFactory, ktorá nakombinuje správnu ProductFactory s LineBuilder(om).

Výrobá sa spustí tak, že ProductionOperator postupne prechádza zoznám plánovaných výrobných sérií v produkčonom pláne.
Od LineBuilder(a) série požiada o postavenie výrobnej linky. LineBuilder pri stavaní linky si u produkčného operátora overuje dostupnosť strojov/robotov/ľudí.
Ak nebude mať dostatok pracovníkov tak linku nepostaví a produkčný operátor prechádza ďalšie naplánované série. 
Naopak ak je možné postaviť linku tak je tato linka vrátená výrobnému operátorovi a ten ju zaradí do zoznamu aktívních liniek a vyradí jej pracovníkov zo zoznamu
dostupných pracovníkov. Po prejdení celého plánu spustí výrobu aktívnych liniek.
Výroba začína tým, že produkčná linka prostredníctvom ProductFactory pošle prvý neopracovaný výrobok prvému pracovníkový linky.

##### Machines diagram

Stroje po každom takte ukladajú CostStatement, kde je uvedené koľko pohonných látok za daný takt spotrebovali.
Každý stroj má na žačiatku stav Working, ktorý sa po istom čase zmení na stav Broken a potom stroj vysiela AlertEvent aby ho prišli opraviť.

##### Events diagram

Základné fungovanie EventList(u) už bolo popísané pri hlavnom diagramu. V hlavnom diagrame kvôli prehľadnosti chýbalo prepojenie EventListu a ProductionOperator, 
preto je doplnený v tomto diagrame. ProductionOperator po stustení liniek posiela eventy o začiatku produkcie.
Ďalej bude EventList vytvárať EventIterator (možno aj viac iterátorov), ktorý budú využívat reporteri.
Každý reportér bude zodpovedať za iný druh reportu.

##### FactoryIterators

Inšpektor bude robiť inšpekcie prechádzaním strojov prostredníctom InspectorIterator(u) a bude jednotlivé stroje navštevovať */iterator a visitor/* .
Riaditeľ bude navštevovať isté entity továrne prostredníctvom DirectorIterator(u) a tak ako inšpector ich bude navštevovať.
Aby mohli byť navštevy vykonané tak všetci pracovníci a iné vybrané entity maju interface Visitable.