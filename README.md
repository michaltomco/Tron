# PV260 Assignement 1 - Tron game

## Notes
Please check Pull request #1

## Feedback
Čo sa týka aktuálneho stavu riešenia, tak mám naseldovné pripomienky:
- Presentation vrstva rieši primárne vykreslovanie, 
Model rieši stav hry a základnú logiku. 
Model však nerieši update logiky. 
Herný engine využíva Presentation aj Model v rámci svojho hlavného game loopu.
- keď rozdelíte Engine od hry, tak ideálne engine aj hra by mohli byť v samostatných balíkoch.
- dávajte si pozor na dodržiavanie clean code a SOLID princípov vo všetkých triedach a metódach.
