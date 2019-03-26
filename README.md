# PV260 Assignement 1 - Tron game

## Questions
* What is the purpose of `Animation` and why is it not used?

* Why is Core.update() empty? Does it need to be implemented?

* What is cumTime? 

* How to calculate collision for arbitrary number of players?

## Notes
* ScreenManager does not seem to need any changes.

* `moveAmount` and
  `graphics2D.fillRect(pathx.get(x), pathy.get(x), 10, 10);` (width, height = 10)
  seem to be connected.
  
* Presentation layer: 
    * ScreenManager
    * Core
    * most of YourClass

* Model layer: 
    * YourClass the logic part:
        * adding listeners ~ adding players
        * keyEvents + mouseEvents
        * checkCollision
    * future Player classes?

* Player layer? Is it part of the Model?
    * YourClass:
        * calculateCurrentPositionPlayer1
        * updatePath
        
* Inteface between Engine and Player:
    * get current position
    * get path

## TODO

* Extract KeyListener, MouseListener, MouseMotionListener implementations out of YourClass.
  These are later implemented by Player classes.

* Player classes should implement one of KeyListener, MouseListener, MouseMotionListener.

* Add logging


* Pull out the game engine

* Key controls not working properly

* Self collision not yet implemented

* Check SOLID principles

* Delete not needed classes

* Change ControlKeyBinder to be less clunky

## Feedback
Čo sa týka vašich dotazov:
2. update() by mal slúžiť na update hernej logiky v každej iterácii game loopu. Na druhej strane draw() by mal slúžiť iba na vykreslovanie a nemal by obsahovať žiadnu hernú logiku. V Core je prázdny kvôli tomu, aby si ho mohla implementovať každá hra podľa seba. Stojí za zváženie, či by metóda nemohla byť abstracť - záleží, či chcete, aby ho každé rozšírenie Core muselo implementovať povinne alebo nie.
3. cumTime sa používa na výpočet času, ktorý ubehol od poslednej iterácie game loopu. Momentálne nie je využitý, pretože súčasný Tron nepoužíva update metódu. V herných engnioch sa to ale používa k tomu, aby sa herná logika updatovala až po prekročení nejakého špecifikovaného thresholdu. Inak by logika hry bola viazaná na rýchlosť vykreslovanie(framerate), čomu sa obecne chcete vyhnúť.

Čo sa týka aktuálneho stavu riešenia, tak mám naseldovné pripomienky:
- Presentation vrstva rieši primárne vykreslovanie, 
Model rieši stav hry a základnú logiku. 
Model však nerieši update logiky. 
Herný engine využíva Presentation aj Model v rámci svojho hlavného game loopu.
- keď rozdelíte Engine od hry, tak ideálne engine aj hra by mohli byť v samostatných balíkoch.
- momentálne nemáte v game loope logiku hry oddelenú od vykreslovania. draw() by mala obsahovať iba update vykreslovania, update hernej logiky by mal byť v update().
- dávajte si pozor na dodržiavanie clean code a SOLID princípov vo všetkých triedach a metódach.
