# project-sysdes



## IMPORTANT NOTICE

We experienced CORS problems when using the front-end. 

To be able to use the frontend, you must start your browser with security checks disabled, to disable a security policy. 

For chrome, you can execute the following commands: 

### Windows

`"[PATH_TO_CHROME]\chrome.exe" --disable-web-security --disable-gpu --user-data-dir=~/chromeTemp`

### MacOS

`open -n -a /Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --args --user-data-dir="/tmp/chrome_dev_test" --disable-web-security`

### Linux

`google-chrome --disable-web-security -–allow-file-access-from-files`



## Deploying

You can find the script to build all jars in the "deploy" folder, along with the docker-compose.yml file and the kubernetes resource files. 



## ReadMe Ligplaats:

Ship Arriving: 
wordt behandeld door event handler, deze gaat de ligplaats op status ready zetten. Daarna wordt er een dock_ready event opgestuurd.
-	Params: vesselId

Dock_ready:
send event, wordt opgestuurd wanneer ship arriving event ontvangen wordt.
-	Params: berthId, berthNumber
load containers:
receive command, deze moet de dock werker op status busy zetten, wacht 5 seconden en daarna stuurt een event ship_ready 
-	Params: berthId

UnloadContainers:
receive command is analoog met load containers.
Containers_ready_at_dock: 
Consume event, als dit event ontvangen is wordt de status van de werker op busy gezet.
Hierna moet men wachten op de command load containers? 

reserveBerth: 
receive command, maak reservatie van een berth. Deze moet de status van de ligplaats veranderen  naar RESERVED en dan vesselId invullen.
-	Params: vesselId, vesselSize


berthReserved:  
send reply, wordt onmiddellijk terug gestuurd wanneer reserve berth behandeld is met dan de status van reservatie.
-	Params: vesselId.
undoReservation: 
receive command, zet de ligplaats en dok werker op status AVAILABLE en zet de vesselId van ligplaats op lege string.
-	Params: vesselId


getLocationOfBoat: 
wanneer deze query wordt ontvangen via REST, wordt als reply het nummer van ligplaats waar de schip staat, teruggegeven. Zie BerthController.
-	Params: vesselId
ShipReady: 
send Event, hiermee wordt de dock-werker en de ligplaats op status AVAILABLE gezet, vesselId van de ligplaats wordt dan ook lege string.
-	Params: vesselId, berthNumber



# Operatie:
Eerste wordt de reservatie gemaakt op basis van de command reserve berth, hiermee wordt de vesselId in de ligplaats gevuld en de status van de ligplaats op RESERVED gezet. Berth reserved wordt automatisch teruggestuurd naar de kapiteinsdienst met de status van de reservatie. Wanneer het event ship arriving ontvangen wordt, verandert de status van de ligplaats naar READY en wordt het event dock ready gestuurd naar VTC met dan de vesselId.
Daarna wordt er gewacht op het event containers ready at dock.
Load containers gebeurt dan ofwel op basis van het event containers ready at dock, ofwel via de REST API. 
De dok werker heeft fractie van seonde nodig (science fiction, we  hebben  popeye aangewerft in onze zeehaven)  om de containers op te laden (in methode handelShipArriving in event handler) en daarna wordt het event ship ready naar VTC gestuurd, methode ship ready gaat dan de status van ligplaats en dok werker op AVAILABLE zetten en vesselId van klasse Berth wordt dan lege string.

Wanneer undo reservation command ontvangen wordt, wordt de status van de ligplaats en dockerwerken onmeddillijk op available gezet ne vesselId wordt dan ook weer een lege string.
Andere diensten kunnen de ligplaats waar een bepaalde schip staat opvragen op basis van de vesselId, hiervoor dient er een REST oproep opgestturd te worden naar de BerthController.


