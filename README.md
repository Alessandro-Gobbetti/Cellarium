# Cellarium


<img src="https://github.com/usi-pf2-2021/project-cellarium/blob/main/CellariumIcon.png" width="250" height="250" align="right">

<br />
<br />
<br />
<br />
<p align="center">
Cellarium is a Spreadsheet created by Alessandro Gobbetti and Laurenz Ebi in the frame of the Programming Fundamentals 2 class of 2021, at USI.
</p>
<br />
<br />
<br />
<br />




## Content
1. [Informations](#inforamtions) 
   1. [Supported formats](#formats) 
   2. [Supported operations](#operations)
2. [Cellarium TUI](#cellarium-tui) 
   1. [How to run](#run-tui)
   2. [Controll the TUI](#controll-tui)
3. [Cellarium GUI](#cellarium-gui)
   1. [How to run](#run-gui)
   2. [Controll the GUI](#controll-gui)
4. [Status of the repo](#status) 

<a name="inforamtions"></a>
## Informations

Cellarium was build during the second semester of the bachelor of inforamtics at USI.

We build our spreadsheet such that we have a [TUI](#cellarium-tui) (text user interface) as well as a [GUI](#cellarium-gui) (graphical user interface).

<a name="formats"></a>
### Supported formats

Its IMPORTANT to point out that we have a own representation to back up our Cellarium spreadsheet. We created an optimized formatting named `.cellarium` where you can store your build spreadsheet in. It only stores the Cells who have a content and are essential for the calculations, thus we avoid storing unnecessary Cells and we save save memory. 

Nevertheless it is also possible to import and export CSV files. 

<a name="operations"></a>
### Supported Operations

Here we listed the operations you can compute with our spreadsheet.

#### Unary
| Operation | Example |
| --- | --- |
| SQUARE ROOT | `SQRT(9)` |
| SINE | `SIN(9)` |
| COSINE | `COS(9)` |
| TANGENT | `TAN(9)` |
| LOGARITHM | `LOG(9)` |
| NEGATION | `-A1` |



#### Binary
| Operation | Example |
| --- | --- |
| ADDITION | `A1 + A2` |
| SUBTRACTION | `A1 - A2` |
| MULTIPLICATION | `A1 * A2` |
| DIVISION | `A1 / A2` |



#### Ranges

| Operation | Example |
| --- | --- |
| SUM | `SUM(A1:C10)` |
| AVERAGE | `AVERAGE(A1:C10)` |
| MAXIMUM | `MAX(A1:C10)` |
| MINIMUM | `MIN(A1:C10)` |
| COUNT | `COUNT(A1:C10)` |

&nbsp;

<a name="cellarium-tui"></a>
## Cellarium TUI (Text User Interface)
![image](https://user-images.githubusercontent.com/79453106/119011744-a1c15600-b995-11eb-81e7-6aebc979b5d7.png)

<a name="run-tui"></a>
### How to Run

To run the programm you have to open your terminal, go in the `project-cellarium` directory and type:   

```bash
java tui/Main
```
<a name="controll-tui"></a>
### Controll the TUI
No matter capitalization.

| Command | Description |
| --- | --- |
| `PRINT` | Prints the enire spreadsheet |
| `PRINT NAMEOFTHECELL` | Prints one single cell |
| `SET NAMEOFTHECELL VALUE` | To set or change a cell |
| `CLEAR NAMEOFTHECELL` | To clear a certain cell |
| `CLEAR` | To clear the spreadsheet |
| `UNDO` | Undos the last command |
| `REDO` | Redos the last undone command |
| `SAVE NameOfTheFile` | Saves the spreadsheet in the current directory with the given name, as a `.cellarium` file |
| `OPEN path` | Imports a given `.cellarium` file in Cellarium |
| `EXPORT NameOfTheFile` | Saves the spreadsheet in the current directory with the given name, as a `.csv` file |
| `IMPORT path` | Imports a given `.csv` file in Cellarium |
| `EXIT` | Closes the programm |
| `HELP` | To open Help terminal with the commands |


#### Examples

To change a Cell:          

```bash
SET C2 7
```
or
```bash
SET A1 = C2 + G7
```

To cancel one cell:
```bash
CLEAR C2
```

&nbsp;

<a name="cellarium-gui"></a>
## Cellarium GUI (Graphical User Interface)

![GUI](https://user-images.githubusercontent.com/79566028/120549334-c1c03300-c3f3-11eb-97e8-10c5ab7d5928.png)

<a name="run-gui"></a>
### How to Run
To run the programm you have to open your terminal, go in the `project-cellarium` directory and type:   

```bash
java gui/Main
```
if the size of the window and the font is to small you can handle it by adding the following command:

```bash
GDK_SCALE=2 java gui/Main
```
you can change the scale as you prefer.

<a name="controll-gui"></a>
### Controll the GUI
Select a cell and edit it.

You can edit a cell by selecting the cell with the mouse or you can select a cell and than edit the formula in the execution bar at top of the window.

Use scrollbars to move the Spreadsheet.

Use the menu bar to use some additional features.

&nbsp;

| Features | Hotkey | Description |
| --- | --- | --- |
| `CLEAR ALL` | ALT + X | To clear the entire spreadsheet |
| `UNDO` | STRG + Z | Undos the last command |
| `REDO` | STRG + Y |Redos the last undone command |
| `SAVE` | STRG + S | Saves the spreadsheet in the current directory with the given name, as a `.cellarium` file |
| `OPEN` | STRG + O |Imports a given `.cellarium` file in Cellarium |
| `EXPORT` | STRG + E |Saves the spreadsheet in the current directory with the given name, as a `.csv` file |
| `IMPORT` | STRG + I | Imports a given `.csv` file in Cellarium |
| `QUIT` | STRG + Q | Closes the programm |

&nbsp;

<a name="status"></a>
## Status of the repo

Cellarium is finished and ready to use.

Have fun!


