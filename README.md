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