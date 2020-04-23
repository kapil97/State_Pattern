# CSX42: Assignment 4
## Name: Kamleshwar Ragava

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in numberPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile statePlay/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile statePlay/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant run -buildfile statePlay/src/build.xml \
-DbaseFile="<base file path>" \
-DinputFile="<input file path>" \
-DrunAvgWindowSize="<size of the window for running average calculations>" \
-DoutputFile="<output file path to which running averages are written>" \
```

-----------------------------------------------------------------------
## Description:

The program first reads the base file and stores it in a data structure.<br>
The program then reads input file and passes it to the ```Context Class``` which performs purchaseAction on the item on the current state until the input fill is empty.<br>
After the processing, the program writes the output to the output file.<br>
Number of Slack Days used: 2

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 04/xx/2020


