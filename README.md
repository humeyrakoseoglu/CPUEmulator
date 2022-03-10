CPU EMULATOR
Programming language I used: JAVA
In the assignment, we were asked to write a CPU emulator software that supports a given basic instruction set (15 instructions). We should have assumed that the computer had 256 bytes of available memory (M) initially set to zero, but since there was overflow when I got 256 bytes, I assumed the memory (M) had 256 int.
In the program.txt document, there is an application code that can calculate the sum of numbers between 0 and 20, which is the sample code in the assignment. My emulator also runs this sample code and other different codes I've tried.
For example, in LOAD 20, we got LOAD as instruction and 20 as immediate value in the code.
Initially I set all flags to zero.
I used 2 java files in project. I have defined the memory, accumulator and flags in the CommandOperations class and defined the instructions we want to do as methods here. I called my Program Counter with object from other class.

I read the descriptions of the instructions from the assignment document and filled in the methods accordingly.

The name of the other class is Midterm_201808088015 as requested in the assignment document. In this class, I created an integer list that holds the order numbers in the txt file, a string list that holds the instructions, and an integer list for the location or the values. I created variables called start and halt of type integer to hold the number of Start and Halt.
I called M, which is a memory array of 256 int size of int type that I created in the CommandOperations class, with the getter method in this class. I defined a variable called Program Counter of type int and gave it the value 0. I created a String variable within the instructions.
I performed the file reading dynamically using the File and Scanner classes.

Using the loop and if-else structure, we called the methods in the CommandOperations class according to the values we pulled from the txt document and we performed the instructions 

