# OMO - Smart Factory

### [UML diagram (zip)](https://gitlab.fel.cvut.cz/zecmarce/omo_semestralka/blob/master/UML%20diagram/UMLdiagram.zip)

### Popis diagramu

##### Hlavný diagram

Hlavná entita systému je Factory */singleton/* .
Za výrobný proces zodpovedá trieda ProductionOperator.
Produkčný operátor má zoznam produkčných liniek a na ních spustí výrobu (o operátorovy a linke detailnejšie v popise [Production diagram](https://gitlab.fel.cvut.cz/zecmarce/omo_semestralka/edit/master/README.md#production-diagram)).

Produkčná linka pošle neopracovaný produkt prvému pracovníkovy výroky a tým je stroj,robot alebo človek (majú interface LineWorker). Ten ho opracuje a posiela ďalej následujúcemu pracovníkovy */chain of responsibility/* .
Posledný stroj vo výrobe je kontrolór, ktorý v prípade správneho počtu vyrobených výrobkov odošle event o ukončení výrobnej série (o strojoch detailnejšie v popise [Machines diagram](https://gitlab.fel.cvut.cz/zecmarce/omo_semestralka/edit/master/README.md#machines-diagram)).

Eventy sa odosielajú do EventList(u), ktorý ich uchováva a zároveň je observovaný EventOperator(om) */observer/* .
EventOperator okamžite spracováva určité druhy eventov. V prípade Alert eventu požiada RepairmentPool o Repairman(a), aby ho poslal opraviť pokazený stroj */object pool/* .
Ak žiaden opravár nie je voľný, tak si tento event uloží do zoznamu nevyriešených eventov. Ak príjme event o ukončení opravy tak zaradí opravára naspäť do RepairmentPool(u) a pozrie sa do zoznamu nevybavených eventov (o eventoch detialnejšie v popise [Events diagram](https://gitlab.fel.cvut.cz/zecmarce/omo_semestralka/edit/master/README.md#events-diagram)).

Továreň má ďalej triedu FactoryTimer, ktorá má zoznam všetkých pracovníkom (interface FactryWorker) a pravidelne im bude aktualizovať čas. 

V továrni sa nachádzajú okrem strojov aj ľudia. Opravári už boli spomenutý a ďalej sú to výrobný pracovníci, inšpektor a riaditeľ. 
Riaditeľ a inšpektor budú robiť návštevy prostredníctvom iterátorov továrne (viac v popise [FactoryIterators diagram](https://gitlab.fel.cvut.cz/zecmarce/omo_semestralka/edit/master/README.md#factoryiterators-diagram)).

V aplikácií je aj trieda FileManager, ktorá bude generovať konfiguračný report továrne a zároveň načítavať konfigráciu továrne.

##### Production diagram

ProdutionOperator má výrobný plán továrne, v ňom sú všetky naplánované a ukončené výrobné série.
Výrobná séria v sebe nesie informáciu o množstve výrobkov a referencie na ProductFactory a LineBuilder */factory method a builder/* .
Nové série vytvára ProdactionOperator pomocou SeriesFactory, ktorá nakombinuje správnu ProductFactory s LineBuilder(om).

Výrobá sa spustí tak, že ProductionOperator postupne prechádza zoznám plánovaných výrobných sérií v produkčonom pláne.
Od LineBuilder(a) série požiada o postavenie výrobnej linky. LineBuilder pri stavaní linky si u produkčného operátora overuje dostupnosť strojov/robotov/ľudí.
Ak nebude mať dostatok pracovníkov tak linku nepostaví a produkčný operátor prechádza ďalšie naplánované série. 
Naopak ak je možné postaviť linku tak je tato linka vrátená výrobnému operátorovi a ten ju zaradí do zoznamu aktívních liniek a vyradí jej pracovníkov zo zoznamu
dostupných pracovníkov. Po prejdení celého plánu spustí výrobu aktívnych liniek.
Výroba začína tým, že produkčná linka prostredníctvom ProductFactory pošle prvý neopracovaný výrobok prvému pracovníkový linky.

##### Machines diagram

Stroje po každom takte ukladajú CostStatement, kde je uvedené koľko pohonných látok za daný takt spotrebovali.
Každý stroj má na začiatku stav Working, ktorý sa po istom čase zmení na stav Broken a potom stroj vysiela AlertEvent aby ho prišli opraviť /state/. 

##### Events diagram

Základné fungovanie EventList(u) už bolo popísané pri hlavnom diagramu. V hlavnom diagrame kvôli prehľadnosti chýbalo prepojenie EventList(u) a ProductionOperator(a), 
preto je doplnený v tomto diagrame. ProductionOperator po stustení liniek posiela eventy o začiatku produkcie.
Ďalej bude EventList vytvárať EventIterator (pravdepodobne aj viac iterátorov), ktorý budú využívat reporteri.
Každý reportér bude zodpovedať za iný druh reportu.

##### FactoryIterators diagram

Inšpektor bude robiť inšpekcie prechádzaním strojov prostredníctom InspectorIterator(u) a bude jednotlivé stroje navštevovať */iterator a visitor/* .
Riaditeľ bude navštevovať isté entity továrne prostredníctvom DirectorIterator(u) a tak ako inšpector ich bude navštevovať.
Aby mohli byť navštevy vykonané tak všetci pracovníci a iné vybrané entity maju interface Visitable.

#### Zoznam návrhových vzorov
* singleton
* chain of responsibility
* observer
* factory method
* builder
* visitor
* iterator
* state


Sandra Hamráková a Marcel Žec 