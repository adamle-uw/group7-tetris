# group7-tetris

## Group 7 - TCSS 305B

### Group Members:
#### Team A:
+ Aryan Damle
+ Martha Emerson
#### Team B:
+ Evan Abrahamson
+ Keegan Sanders

### Sprint 1 Contribution:
+ **Aryan:** Created the GUI panel where the game would be visible. Set 3 regions and colored them as per the Sprint1 deliverable instructions. Did some pair programming with Martha and decided that using a nested Frame would be more ideal for the 'NextPiece' panel. Instead of the 'NextPiece' panel's height  being the entire length of the GUI, it will be exactly half the height of the GUI to make the layout less cluttered. 

+ **Martha:** Created the GUI layout and arranged the visible content area of the GUI window into three main regions: the Tetris board, the next piece display area (a square), and the miscellaneous info area. Did some pair programming with Aryan and worked together on troubleshooting issues with the GUI layout. Created the initial readme template with basic formatting.

+ **Evan:** Created a temporary blank window to see the bar. Added a menu bar to the top with a few options: **Game** (contains game options), **Pause Game**, **Exit Game**, **About** (contains help and controls), and **Options** (doesn't contain anything specific yet). Added basic functionality in printing to console just to make sure it works correctly.

+ **Keegan:** Added a submenu to the new game section of the menu bar and functionality for the new game in new window button and the controls button to open new windows. Formatted and styled the readme file in GitHub.

### Sprint 1 Comments:
+ We formed two programming teams this week: Evan and Keegan worked together on the menu, and Aryan and Martha worked together on the GUI. Initially ran into GitHub issues with different group members not being able to see the colors on the GUI, but made sure to merge branches ahead of time so we had time to fix those issues.

### Sprint 2 Contribution:
+ **Aryan:** Worked with the top-most GUI class, adding an ActionListener and a KeyListener, as well as optimizing the Timer. Worked with Martha to optimize the Main class. Served as meeting scribe.

+ **Martha:** Did some pair programming with Evan and decided to create two new classes (TetrisBoard and NextPiece) to implement PropertyChangeListener. Worked with Aryan to optimize the Main class. Researched extra credit. Completed the readme for Sprint 2.

+ **Evan:** Created the Board interface. Did some pair programming with Martha and created the GUI view classes for the TetrisBoard and NextPiece to implement PropertyChangeListener. Wrote a test to ensure that the Objects respond when informed of a state change.

+ **Keegan:** Implemented the Observer Design Pattern in the Board class. Served as Project Manager and worked with Aryan to refactor and optimize the existing program.

### Sprint 2 Meetings:
+ Meeting minutes (for Wednesday and Saturday): https://docs.google.com/document/d/1CimpqTYNHXGqp6zbbmZ82j1iLlfdSAdGsUTmqnws-gU/edit?usp=sharing
 
+ We met as a group on Monday, Wednesday, Saturday, and Sunday via Zoom.

+ **Monday, February 27th:** We met on Zoom and discussed the deliverables, deciding to keep the same team arrangement as in Sprint 1, with Aryan and Martha focused on the GUI and with Evan and Keegan focused on the model. Martha nominated Keegan to act as project manager (PM) for the week, because the group recognized that it would be beneficial to have one person making sure that all parts of the program were working well together. Keegan agreed to act as PM for Sprint 2.

+ **Sunday, March 5th:** We met to complete the final merge and troubleshoot any merge problems together. The merge was successful.

+ Primary forms of communication included Discord messages, Discord calls, and Zoom meetings.

### Sprint 2 Comments:
+ No major issues this week.

### Sprint 3 Contribution:
+ **Aryan:** Added buttons to the GUI. Worked on the NextPiece implementation. Created the music player. Refactored and added Lambdas early in the week. Served as meeting scribe. Worked together with other team members to troubleshoot problems.

+ **Martha:** Created a checklist of Sprint3 deliverables and tracked the team's progress throughout the week. Refactored all the code, adding Javadocs and correcting Checkstyle errors. Fixed and improved various problems throughout the code. Completed the readme for Sprint 3. Worked together with other team members to troubleshoot problems.

+ **Evan:** Added the Tetris pieces to the board. Got the point system working. Fixed and improved various problems throughout the code. Worked together with other team members to troubleshoot problems.

+ **Keegan:** Created the Tetris game board. Finished making changes to the Board class. Fixed and improved various problems throughout the code. As the Git expert, performed the major merges from the group branches to the Sprint3 branch and then to the main branch. Worked together with other team members to troubleshoot problems.

### Sprint 3 Meetings:
+ Meeting minutes (for Monday, Wednesday, and Friday): https://docs.google.com/document/d/1CimpqTYNHXGqp6zbbmZ82j1iLlfdSAdGsUTmqnws-gU/edit?usp=sharing
 
+ We met as a group on Monday, Wednesday, Friday, Saturday, and Sunday via Zoom.

+ **Saturday, March 11th:** We met on Zoom and discussed progress and plans. Evan worked on the point system, got the game to speed up, and was working on getting the Board up but still having issues. Aryan added buttons and was almost done adding the music. He noticed that there was a bug in the GUI window with the start button creating a new JFrame. He planned to start working on the Board. Martha had been working on the board and creating the pieces. Keegan made the controls window with the GUI using ActionListener. For troubleshooting, the group discussed troubleshooting some issues with the start buttons and making the timer speed up appropriately.

+ **Sunday, March 12th:** We met on Zoom early in the afternoon and discussed final steps to complete the project. Later in the evening, around 10 p.m., we opened another Zoom meeting and worked in unison to troubleshoot and bring the project to completion.

+ Primary forms of communication included Discord messages, Discord calls, and Zoom meetings.

### Sprint 3 Comments:
+ **Music credits:** Pac-Man Intro Music from Spykee75 on YouTube (https://youtu.be/rnf0q1zOTXs)

+ **Location of single-line statement:** the single-line statement that causes the game to animate faster at higher levels can be found in the GameGUI.java class at line 99.

### Sprint 3 - Changes Made to the Provided Code in the Board.java class:
1. Imported PropertyChangeListener & PropertyChangeSupport.
2. Edited the Board class to implement BoardInterface.
3. Added constants and a PropertyChangeSupport variable.
4. Added a PropertyChangeSupport Object to the Board constructor.
5. Added notifyObserversOfNewGame() to the newGame() method.
6. Added notifyObserversOfPositionChange() to the left(), right(), down(), move(), setPoint(), and prepareNextMoveablePiece methods.
7. Added notifyObserversOfOrientationChange() to the rotateCW() and rotateCCW() methods.
8. Added notifyObserversOfCompleteRow() to the checkRows() method.
9. Added notifyObserversOfBlockChange() to prepareNextMovablePiece() method.
10. Added several property change methods.
