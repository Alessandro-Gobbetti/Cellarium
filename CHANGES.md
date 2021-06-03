# Changes

## Milestone 1: May 10
* Initialized repo
* Created README.md
* Imported code from Labs 8 and 9
* Created a plan how to procide

## Milestone 2: May 17
* Added code from Lab 10
* Added a Spreadsheet
* Added some more functionalities (Sin, Cos, Sqrt)
* Changed the way of evaluation
* Researched the packages we will use
* Added remove 
* Added a CHANGES.md

## Milestone 3: May 24
* Added the TUI
  * PRINT the spreadsheet or a single cell
  * SET cells
  * CLEAR the spreadsheet or single cells
  * HELP to get an explanation for all commands or a more detailed for a single command
  * EXPORT to save the spreadsheet in csv
  * IMPORT to open a csv file
  * EXIT to quit the program

* Added a GUI
  * Added a SpreadsheetViewTableModel to manage the part of the spreadsheet that we show on screen.
    * The idea is that we only print a part of the spreadsheet and then we can use buttons to change the visible part
  * Added a table rendere to modify the style anc colors to print.
* Improved language
  * now we support functions
    * Added function node creators to do this
  * still working to support ranges

* Added tests

* Improved several features

## Milestone 4: May 31
* Commands
  * new package
  * refactored commands to allow undo/redo
* Gui
  * Better design
  * undo, redo, clear, save, open, export, import...
  * commands can be run from the Jtextfield
  * scrollbars
* Tui
  * undo and redo

* Added tests

* Fixed the Average functionality

* Added Cellarium Icon

* Added own formatting `.cellarium` which is optimized to avoid waste of memory.
  * Added SAVE to save the cellarium as `.cellarium ` file
  * Added OPEN to open `.cellarium` files

* Added some more functionalities (Tan, Log)

* Fixed MVN Warnings, PMD Failures and CPD. 

