**České vysoké učení technické v Praze, Fakulta elektrotechnická**

**Softwarové inženýrství a technologie**

předmět [Objektový návrh a modelování](https://fel.cvut.cz/cz/education/bk/predmety/31/29/p3129906.html)

---

##### ZADANIE
Vyvinúť aplikáciu, ktorá simuluje inteligentnú továreň za použitia **návrhových vzorov**.
Vyvíjané v jazyku **Java** vo dvojčlennej skupine.

# English


**Czech Technical University in Prague, Faculty of Electrical Engineering**

**Software engineering and technologies**

course [Object-oriented design and Modeling](https://fel.cvut.cz/en/education/bk/predmety/31/29/p3129906.html)

---

##### ASSIGMENT
Develop application that simulate smart factory using design patterns.
Developed in **Java** in two-member group.

---
# Original README from school project

## OMO - Smart Factory

### [UML diagram (zip)](https://github.com/marcel-zec/cvut-fel-sit-omo/blob/master/UML%20diagram/UMLdiagram.zip)

### Popis diagramu

##### Hlavný diagram

Hlavná entita systému je Factory */singleton/* .
Za výrobný proces zodpovedá trieda ProductionOperator.
Produkčný operátor má zoznam produkčných liniek a na ních spustí výrobu (o operátorovy a linke detailnejšie v popise [Production diagram](https://gitlab.fel.cvut.cz/zecmarce/omo_semestralka/blob/master/README.md#production-diagram)).

Produkčná linka v pravidelných intervaloch (na zaklade factory timeru) volá na prvom pracovníkovi výroby (tým je stroj,robot alebo človek) metódu update. Ten ho opracuje a posiela ďalej následujúcemu pracovníkovi */chain of responsibility/* .
Posledný stroj vo výrobe je kontrolór, ktorý v prípade správneho počtu vyrobených výrobkov odošle event o ukončení výrobnej série (o strojoch detailnejšie v popise [Machines diagram](https://gitlab.fel.cvut.cz/zecmarce/omo_semestralka/blob/master/README.md#machines-diagram)).

Eventy sa odosielajú do EventList(u), ktorý ich uchováva a zároveň je observovaný EventOperator(om) */observer/* .
EventOperator okamžite spracováva určité druhy eventov. V prípade Alert eventu požiada RepairmentPool o Repairman(a), aby ho poslal opraviť pokazený stroj */object pool/* .
Ak žiaden opravár nie je voľný, tak si tento event uloží do zoznamu nevyriešených eventov. Ak príjme event o ukončení opravy tak zaradí opravára naspäť do RepairmentPool(u) a pozrie sa do zoznamu nevybavených eventov (o eventoch detialnejšie v popise [Events diagram](https://gitlab.fel.cvut.cz/zecmarce/omo_semestralka/blob/master/README.md#events-diagram)).

Továreň má ďalej triedu FactoryTimer, ktorá má zoznam všetkých pracovníkom (interface FactryWorker) a pravidelne im bude aktualizovať čas. 

V továrni sa nachádzajú okrem strojov aj ľudia. Opravári už boli spomenutí a ďalej sú to výrobní pracovníci, inšpektor a riaditeľ. 
Riaditeľ a inšpektor budú robiť návštevy prostredníctvom iterátorov továrne (viac v popise [FactoryIterators diagram](https://gitlab.fel.cvut.cz/zecmarce/omo_semestralka/blob/master/README.md#factoryiterators-diagram)).

V aplikácií je aj trieda FileManager, ktorá načítavá konfiguráciu továrne (množstvo strojov, pracovníkov atď.)

##### Production diagram

ProductionOperator má výrobný plán továrne, v ktorom sú všetky naplánované a ukončené výrobné série.
Výrobná séria v sebe nesie informáciu o množstve výrobkov a referencie na ProductFactory a LineBuilder */factory method a builder/* .
Nové série vytvára ProductionOperator pomocou SeriesFactory, ktorá nakombinuje správnu ProductFactory s LineBuilder(om).

Výrobá sa spustí tak, že ProductionOperator postupne prechádza zoznam plánovaných výrobných sérií v produkčonom pláne.
Od LineBuilder(a) série požiada o postavenie výrobnej linky. LineBuilder pri stavaní linky si u produkčného operátora overuje dostupnosť strojov/robotov/ľudí, nastavuje
poradie strojov abz vytvoril chain of responsibility ale aj pošle prvému pracovníky výrobký na opracovanie.
Ak nebude mať dostatok pracovníkov tak linku nepostaví a produkčný operátor prechádza ďalšie naplánované série. 
Naopak ak je možné postaviť linku tak je tato linka vrátená výrobnému operátorovi a ten ju zaradí do zoznamu aktívních liniek a vyradí jej pracovníkov zo zoznamu
dostupných pracovníkov. Po prejdení celého plánu pridá linku do zoznamu aktívných liniek a pri ďalšom takte už bude táto linka produkovať. 

##### Machines diagram

Stroje po každom takte ukladajú CostStatement, kde je uvedené koľko pohonných látok za daný takt spotrebovali.
Každý stroj má na začiatku stav Working, ktorý sa po istom čase zmení na stav Broken a potom stroj vysiela AlertEvent aby ho prišli opraviť /state/.
Súčasťou eventu je aj priorita linky, ktorá je odvodená od priority výrobnej série. Podľa tejto priority bude vyhodnocovaná postupnosť opráv.

##### Events diagram

Základné fungovanie EventList(u) už bolo popísané pri hlavnom diagramu. V hlavnom diagrame kvôli prehľadnosti chýbalo prepojenie EventList(u) a ProductionOperator(a), 
preto je doplnený v tomto diagrame. ProductionOperator po spustení liniek posiela eventy o začiatku produkcie.
Ďalej bude EventList vytvárať EventIterator (pravdepodobne aj viac iterátorov), ktorý budú využívať reportéri.
Každý reportér bude zodpovedať za iný druh reportu.

##### FactoryIterators diagram

Inšpektor bude robiť inšpekcie prechádzaním strojov prostredníctom InspectorIterator(u) a bude jednotlivé stroje navštevovať */iterator a visitor/* .
Riaditeľ bude navštevovať isté entity továrne prostredníctvom DirectorIterator(u), a tak ako inšpector ich bude navštevovať.
Aby mohli byť navštevy vykonané, tak všetci pracovníci a iné vybrané entity maju interface Visitable.

#### Zoznam návrhových vzorov
* **Singleton** - využíva sa v triedach Factory, ProductionOperator, EventList, EventOperator, FactoryTimer, RepairPool
* **Chain of responsibility** - Pri stavaní produkčnej linky je pracovníkom nastavovaný "nextLineWorker". Pri simulácií chodu továrne je volaný update na prvom pracovnikovy 
linky, ak tento pracovnik nie je funkčný tak volá metodu update na ďalšom pracovníkovy v poradí aby linka nestála. 
* **Observer** - využíva sa v tride EventOperator, ktorý implementuje Observer a v triede EventList ktorého sleduje EventOperator. Ak EventList dostane event,
            notifikuje EventOperatora, ktorý začne spracovávať eventy.
* **Factory method** - využívajú triedy FactoryProductA, FactoryProductB, FactoryProductC a implementuju interface ProductFactory.Používa sa na vytváranie nových koknkrétnych 
produktov v linke, bez opracovania. 
* **Abstract factory** - využíva trieda AbstractSeriesFactory. V našom prípade ide iba o jednu implementáciu v SeriesFactory pretože máme iba jednu továreň a vyrabame iba jeden druh vyrobkov. Ak by sme mali viac tovární alebo by sme rozlišovali serie aj inak ako podľa produktu, dala by sa využiť pre viac implementácií.
* **Builder** - Produčná séria je builder director(om) a teda je na nej volaná metóda build ktorá vráti produkčná linku. Séria ma v sebe odkaz na builder(a), ktorý 
implementuje metody na správne postavenie linky. V našej implementácií je časť metód builder(ov) rovnaká, preto máme abstraktnú triedu LineBuilder a následne konkrétne
triedy doimplementúvajú konstruktor, v ktorom určujú počet pracovníkov a metódu na určenie správneho poradia.
* **Visitor** - Využívajú ho Director a Inspector pri svojich inspekciách. Navšteveju rôzné typy pracovníkov, resp. entít a vykonávajú rôzne činnosti
* **Iterator** - Využíva sa pri inšpekcii Directora a Inspectora a každý z nich má vlastný iterátor. DirectorIterator prechádza produkčného operátora, produkčný plán,
           linky a všetkých pracovníkov, stroje a roboty. InspectorIterator prechádza stroje a robotov podla miery opotrebenia.
* **State** - Stroje v linke majú na začiatku stav Working. V každom update sa volá nad stavom metóda canWork, ktorá v prípade stavu Working môže pokaziť stroj. 
Po pokazeny sa stav zmeni na Broken a ten čaká kým bude stroju nastavený opravár. Následne prejde stroj do stavu UnderRepair a v tomto stave sa simuluje prebiehajúca oprava
stroja. Po oprave stroj prejde naspäť do stavu Working.
* **Object pool** - Využívaný v triede RepairPool, kde si drží všetkých opravárov a po vyžiadaní EventOperatora poskytne voľného opravára. Keď sa opravár vráti z opravy,
tak je k dispozicií pre dalšie opravy.


Sandra Hamráková a Marcel Žec 
