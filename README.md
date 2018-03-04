# Chess problem

## Task description

Create a Java application that should represent an empty chessboard where the user will be able to enter a starting position and an ending position. The application should then calculate a list of all possible paths that one knight piece in the starting position could take to reach the ending position in 3 moves. Some inputs might not have a solution, in this case the program should display a message that no solution has been found. Otherwise, the shortest path (if that exists) should be returned.

## Assumptions

- Default chess board is 2-dimensional and consists of 64 squares (8 rows and 8 columns). Vertical columns are labeled using letters, and horizontal rows are labeled using numbers. A square can be identified by a unique coordinate pair - a letter and a number.
- There might be several paths of equal length that a knight can take to reach the ending position. If there are more than 1 shortest path all must be displayed.
- The application is quite simple, so it should not include external dependencies.

## Solution

A user runs the program and passes starting and ending positions as command line arguments. The application uses a BFS algorithm to find the shortest path. If such path or paths are found the application displays them using standard output. 
```
> java -jar chess-problem-1.0-SNAPSHOT.jar a1 a2
a1 -> c2 -> b4 -> a2
a1 -> b3 -> c1 -> a2
```
If no paths have been found, the application displays a message:
```
> java -jar chess-problem-1.0-SNAPSHOT.jar a1 h8
No solution has been found
```

## Possible extensions

- By default, the application finds paths that a knight piece can take to reach the ending position. One can extend the program to find path for a different piece by extending ChessLookupStrategy.
- By default, the application finds paths that are not longer than 3 moves. This behavior can be changed either by changing the default value in ChessParametersParserby or by using a different parser to read the value from the input.
- By default, the application uses a standard 8×8 board. Size of board can be adjusted either by changing the default dimension values in ChessParametersParser class or by using a different parser to read the value from the input.
- By default, the application uses a command line interface. One can implement a graphical interface and reuse current code for finding the shortest path.
