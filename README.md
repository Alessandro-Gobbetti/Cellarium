# Cellarium

Cellarium is a Spreadsheet created by Alessandro Gobbetti and Laurenz Ebi in the frame of the Programming Fundamentals 2 class of 2021, at USI.

## Cellarium TUI (Text User Interface)
![image](https://user-images.githubusercontent.com/79453106/119011744-a1c15600-b995-11eb-81e7-6aebc979b5d7.png)

### How to Run

To run the programm you have to open your terminal, go in the `project-cellarium` directory and type:   

```bash
java CellariumTui
```

### Controll the spreadsheet

| Command | Description |
| --- | --- |
| `PRINT` | Prints the enire spreadsheet |
| `PRINT NAMEOFTHECELL` | Prints one single cell |
| `SET NAMEOFTHECELL VALUE` | To set or change a cell |
| `CLEAR NAMEOFTHECELL` | To clear a certain cell |
| `CLEAR` | To clear the spreadsheet |
| `SAVE NameOfTheFile` | Saves the spreadsheet in the current directory with the given name |
| `OPEN path` | Imports a given csv file in Cellarium |
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
## Cellarium GUI (Graphics User Interface)

## More

This README will be updated bt the time new features are added to the spreadsheet.
The Spreadsheet is not finished yet.
