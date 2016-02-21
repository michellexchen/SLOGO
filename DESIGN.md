SLogo: Design Document (Team 18)
===================


----------
Introduction
-----------------------
Our team is creating an integrated development environment (IDE) that supports SLogo programs. SLogo is a simplified version of Logo, a computer programming language designed to teach programming to children. SLogo programs consist of command line prompts and user-defined functions. In this program, commands will be interpreted and executed through a graphical user interface (GUI) containing a command history and a display ground. The display ground will create a design and "draw" the user commands by moving a turtle, and the command history will display commands already run and results from executions. The primary goals of this project are to interpret/parse user commands, and execute these commands to display the results through a user interface. In terms of architecture, our program can be easily extended through adding new commands and creating user-defined functions that make new designs to be drawn by the turtle. Parsing previously defined commands, command window prints, and other menus within the program will be closed. Menus and windows can be extended but will be closed to changing previously implemented functionalities.


Design Overview
-----------------------
Front-End:
![UML Diagram](https://github.com/duke-compsci308-spring2016/slogo_team18/blob/master/UML_backend.png?raw=true)

View is the master-view that keeps track of a list of projects. Projects are like workspaces—different projects that the user can work on. It is named after Eclipse IDE’s projects. A project contains a list of Turtles, a Canvas object, and History, and customCommands and possibly more. myTurtles variable is actually a List<Turtle>--UML Lab program was buggy so it’s shown as of Turtle. The back-end and the front-end communicate through the list of Turtles.

Back-End:
![UML Diagram](https://github.com/duke-compsci308-spring2016/slogo_team18/blob/master/UML_frontend.png?raw=true)

User Interface
-------------------
When the user runs the program, the first screen they see will have a dropdown menu for them to choose their language, and a button for them to upload their turtle image. 

![firstscreen](https://lh3.googleusercontent.com/-DyZAk5ID44o/Vsol7HHxAaI/AAAAAAAABys/RaWrU5YPAsY/s0/IMG_0357.JPG "select")

Once they've selected a language and uploaded their turtle image, they are taken to GUI. They can select the project that they're working on from the dropdown menu (all others are hidden), see their command history, click the help button to direct them to an HTML page with a resource guide, and enter in their command. Once they hit run the program will interpret their command and draw on the screen. To the left of the screen the user will be able to track his/her variables, and customize their environment (background color, pen color).

![UI](https://lh3.googleusercontent.com/-YX-AUE2tDiY/Vsok9Fac7PI/AAAAAAAAByU/QZDjQ_GdrqM/s0/IMG_0356.JPG "UI")

In the case that the user enters a command that cannot be interpreted, a popup will show up notifying the user of their faulty input. The command will be saved in the command history and displayed in red text, and the user will be able to edit their command. The UI protects against empty data as the "Run" button will not be clickable until there is text in the command box.

API Details
--------------
Another API that the front-end is going to build is the History API. This API would be classified as an external API because it interacts with both the front-end and the back-end. This API supports the specification that the SLogo IDE should enable the user to see commands previously run in the environment (even better, make them directly clickable to execute). The resources it is going to use, which will be a parameter to this API, is a list of nodes (command objects) from the back-end. It returns a pane that has JavaFX Text elements inside it so that each line of test could be clicked to generate an ActionEvent that executes the same command again by sending the same command to the back-end. Because the main feature of this API is to visualize a list of things and to take in mouse click ActionEvents to notify the back-end, other additions that want to use this feature can easily extend this API. This API belongs to the front-end, and it satisfies one of our key goals of separating the back-end and the front-end. This abstracts out the idea of taking what the back-end has in data and turning it into visual JavaFX elements. Also, it aims to emulate the black-box model of hiding how things are implemented in the inside. Because it fulfills a simple purpose, it is hard to misuse, easy to use, and sufficiently powerful. So this API follows the principles of abstraction, encapsulation, and possibly inheritance (this functionality could be used by others).


API Example Code
-------------------
Filler

Design Considerations 
--------------
Filler


Team Responsibilities
-----------------
For Front-End, Hunter will be building View and Projects. View is the master-view class where projects, and additional visualization features are housed. The Project class contains turtles, canvas, history, and possibly new commands. Michelle will be in charge of creating subclass elements such as Turtle, Canvas, History, HTML Help, and Languages.
