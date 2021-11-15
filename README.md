# Robots

## Assumptions and Restrictions
- Limited number of robots to run (if you put 10000000 that could cause issues)
- No playground for this because lack of time to finish that part.
- I assumed that the user always input the instructions in the right order. Again implementing that will take me a bit more of time.
- The functionality is not printing out just returning a Position object to be handled by another layer that will deal with this.
- Not using IoC just to simplify a bit the solution but desirable.

## Testing the cases you might have in mind

I have provided an Integration test `RobotMissionControlIntegrationTest` that connects all the 
components and by adding test cases scenario you play around with it.

There I added the sample that you described in the PDF.

just checkout the project, and within the folder run:

`./gradlew test`