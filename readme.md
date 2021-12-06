# Concurrency Based Game Environment

This project is a concurrency based game environment based around board games. The end goal is to have a game that can play multiple different types of board games with multiple instances of games running concurrently.

This repository contains a proof of concept implementation of a checkers, board game.

In order to run the game, you must download the eclipse project, ensure that javaFX is installed, add the JavaFX libraries to the project and run MainMenu.java, with the following VM arguments in the run configuration, '--module-path "<JavaFX path>" --add-modules=javafx.controls' where <JavaFX path> is the location of the JavaFX sdk lib file on your machine.
  
Here is a video tutorial showing how to import the libraries and run the project: https://www.youtube.com/watch?v=G5nJj-lexqw
