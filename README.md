# VOD System
## Running

You can run program in two ways:
- with 0 parameters - then new simulation starts 

`java -jar VODSystem.jar`

- with 1 or more parameters- simulation is loaded from file which location is first argument. 

`java -jar VODSystem.jar C:\\PathToFolder\Simulation.ser`

## Short description of program

Control Panel has to lists:
- left with all products in system
- right with all users in system

After clicking element of list new window with information about product shows up. There you can also delete it.

Menu bar has to option - File and Edit. In file there are two options:
-refresh - refreshes the view of control panel. If you want to see some changes, because you add new client, distributor
produced some new films, etc., just click it. 
-save- allows you to save simulation. Then you can load this simulation when running program by giving path to saved simulation in first argument.

In edit there are three options:
-product price - open new window where you can set new price for product. Just choose product from list, write new price
and click save
- subscription - here you can edit prices of subscriptions
- add - here you can choose what type of product/user you want to add to simulation

There is also another future which allows to seatch for products with specified phrase. You can write some text 
in text area, choose where to search- in names of actor who played in film or in product names. Click search
and all products containing the given phrase in selected element will be displayed.
