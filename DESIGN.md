SLogo: Design Document (Team 18)
===================


----------
Introduction
-----------------------
Our team is creating an integrated development environment (IDE) that supports SLogo programs. SLogo is a simplified version of Logo, a computer programming language designed to teach programming to children. SLogo programs consist of command line prompts and user-defined functions. In this program, commands will be interpreted and executed through a graphical user interface (GUI) containing a command history and a display ground. The display ground will create a design and "draw" the user commands by moving a turtle, and the command history will display commands already run and results from executions. The primary goals of this project are to interpret/parse user commands, and execute these commands to display the results through a user interface. In terms of architecture, our program can be easily extended through adding new commands and creating user-defined functions that make new designs to be drawn by the turtle. Parsing previously defined commands, command window prints, and other menus within the program will be closed. Menus and windows can be extended but will be closed to changing previously implemented functionalities.


Design Overview
-----------------------
![UML Diagram](https://github.com/duke-compsci308-spring2016/slogo_team18/blob/master/UML_frontend.png?raw=true)

View is the master-view that keeps track of a list of projects. Projects are like workspaces—different projects that the user can work on. It is named after Eclipse IDE’s projects. A project contains a list of Turtles, a Canvas object, and History, and customCommands and possibly more. myTurtles variable is actually a List<Turtle>--UML Lab program was buggy so it’s shown as of Turtle. The back-end and the front-end communicate through the list of Turtles.



User Interface
-------------------
Filler

API Details
--------------
Filler


API Example Code
-------------------
Filler

Design Considerations 
--------------
Filler


Team Responsibilities
-----------------
Filler

























--------------------------------------
# slogo
Duke CompSci 308 Cell Society Project


###Introduction



### Overview

Possible Classes:

What needs to be extendable is:

### User Interface


### Design Overview


### User Interface


### API Details
External API 
	
	BackEnd 
*Back end sees an exception with the command and sends the Exception back to the GUI

	
	Views/GUI
*Takes a list of objects passed from the back end
*Each object will be drawn on the GUI and it contains flags and variables that determine what the object is and what it looks like
*The view takes a list of objects so that it doesn't matter what or how many objects get passed to the graphics, it can read them regardless 
*.update() --> takes the intermediary steps between the beginning state and the end state
	
Internal API

BackEnd

* LogoPiece

	This will be an abstract class that must be extended by each of our piece. This will have a .animate() method that the object calls on itself so that there is no need for a middle man or Graphics class. This way we are decreasing what needs to go on in the front with regards to the backend.

*Turtle object needs (extends our LogoPiece):

	1. It's direction
	2. towardsXY turns the turtle to face the xy coordinate
	3. setHeader turns turtle to an absolute header, returns number of degrees moved
	4. boolean penUp or penDown --> 
	5. boolean visible that determines if the turtle is visible

	
	Views/GUI
	
How the two will communicate? 


### API Example Code 


### Design Considerations


### Use Cases



### Team Responsibilities 
* Mario
* Hunter
* Aaron
* Michelle
* Adam
