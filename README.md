# Description
A simple java project for Fruchterman Reingold Graph using an input file with nodes properties.

Instructions
===
## For compile 

To compile the code just execute the compile.sh shellscript

---
## How to use the program

The compile.sh execution creates the new directory domes/
From your current path just execute 

* java domes.Domes 

If you want to test the program with preconfigured instructions for a random graph. 

**OR**

* java domes.Domes filename

If you want the program to give yours instructions for a graph. 

After you should insert the itteration number.
---
## Custom file syntax

If you want to use custom instractions for a graph just create a file with th following syntax

c Input Graph  
c  
p edge 6 5  
e 1 2  
e 2 3  
e 2 4  
e 5 1  
e 6 1  

---

## Output

The program automatically creates a output.dot file.

---
## Create a pdf with your graph

Just execute the make_pdf.sh shellscript.

You should have installed the GraphViz platform.
