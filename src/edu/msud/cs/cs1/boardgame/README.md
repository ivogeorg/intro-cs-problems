## `Game` class and `GamePiece` hierarchy

This problem's level is _higher introductory_ to _intermediate_. It:
1. Tests the knowledge of basic object-oriented terminology and usage, including types of variables and methods, identifier categories, abstract classes, inheritance, and polymorphism. 
2. Gives just an accessible taste of _generics_ and _lambdas_. 
3. Provides a concrete and simple but nontrivial demonstration of object lifecycles. 
4. Provides a rich context in which various method implementations can be asked for. 

_Inspired by The World of Warcraft._

<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License</a>.

### Details
1. This is a simple board "game" with a rectangular board and two types of pieces, grunts and warriors. The board is represented by nested `ArrayList`-s. An outer list represents the board grid. Each position on the board holds the game pieces that currently occupy it.
2. The pieces are represented by the simple class hierarchy `GamePiece`<-`Grunt`/`Warrior`. Pieces can move and each one moves in the fashion of its species, grunts like knights in chess, and warriors like queens in chess.
3. The board has `width` and `height`, and so board positions have x-y coordinates. The outer `ArrayList` is indexed by the expression `y * width + x`.
4. All the game logic is in the `Game` class. Essentially, at each position, the pieses of the two species square off against each other. See `doBattle()` for a description of the actions. The defeated pieces are taken off the board in `buryTheDead`. The survivors keep on wandering and battling, until all pieces of one of the species are taken off the board. All pieces age by a year each round, unless they battle, in which case they age twice as fast. Grunts have a small prevalence advantage, while warriors have a small longevity advantage.
5. The `TwoDimensional` hierarchy provides simple classes for `Position`, `Move`, and `Population` (used to count grunts and warriors).
6. Many of the elements of the functionality are randomized.

### Notes
1. A problem can ask questions about the lifecycle of a `GamePiece` object in the context of the game and a tracing of its existence from construction to release to garbage collection.
2. Given the game code without `showBoard(Game g)` and a desired output, a question can ask for its implementation.
3. Similarly, given most of the code of the problem, ask for the implementation of a withheld method.
4. Questions can be created around the nested-collection implementation of the board and its indexing.
5. Ask for the reimplementation of the position `ArrayList` with a set-type collection.
6. `Game` is a sufficiently complex class so it can be used for variable and method type identification.

### Bonus
1. For creating a third species and modifying the game accordingly.
2. For creating motionless pieces that serve as, say, food or obstacles.
3. For adding polymorphic behaviors to the game pieces.
4. For creating a specific mode/strategy of motion for non-stationary pieces.

### Sample run

```text
/Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home/bin/java "-javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=64400:/Applications/IntelliJ IDEA.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath /Users/ivogeorg/git-repos/intro-cs-problems/out/production/intro-cs-problems edu.msud.cs.cs1.boardgame.Game

Initial population: Grunts-30, Warriors-30
|-------|-------|-------|-------|-------|
|  2-  1|  2-  2|  3-  4|  0-  1|  2-  0|
|-------|-------|-------|-------|-------|
|  0-  0|  0-  3|  2-  1|  2-  0|  1-  3|
|-------|-------|-------|-------|-------|
|  2-  1|  1-  0|  1-  0|  3-  1|  0-  1|
|-------|-------|-------|-------|-------|
|  1-  0|  0-  0|  0-  0|  0-  4|  0-  2|
|-------|-------|-------|-------|-------|
|  2-  1|  1-  2|  2-  2|  1-  0|  2-  1|
|-------|-------|-------|-------|-------|

Survivors: Grunts-25, Warriors-24
|-------|-------|-------|-------|-------|
|  1-  1|  0-  2|  1-  2|  0-  1|  0-  0|
|-------|-------|-------|-------|-------|
|  0-  2|  1-  2|  2-  2|  1-  0|  0-  1|
|-------|-------|-------|-------|-------|
|  7-  0|  0-  0|  0-  0|  1-  0|  0-  0|
|-------|-------|-------|-------|-------|
|  4-  1|  2-  1|  0-  1|  1-  3|  0-  1|
|-------|-------|-------|-------|-------|
|  1-  0|  2-  2|  1-  1|  0-  1|  0-  0|
|-------|-------|-------|-------|-------|

Survivors: Grunts-22, Warriors-22
|-------|-------|-------|-------|-------|
|  2-  1|  3-  2|  0-  3|  2-  0|  0-  0|
|-------|-------|-------|-------|-------|
|  1-  1|  2-  0|  1-  1|  0-  0|  0-  2|
|-------|-------|-------|-------|-------|
|  3-  1|  1-  0|  0-  0|  0-  0|  0-  0|
|-------|-------|-------|-------|-------|
|  1-  2|  2-  3|  1-  0|  0-  1|  0-  1|
|-------|-------|-------|-------|-------|
|  0-  1|  0-  0|  2-  3|  1-  0|  0-  0|
|-------|-------|-------|-------|-------|

Survivors: Grunts-17, Warriors-18
|-------|-------|-------|-------|-------|
|  0-  0|  2-  0|  0-  2|  0-  0|  0-  0|
|-------|-------|-------|-------|-------|
|  2-  3|  3-  0|  2-  0|  0-  0|  0-  0|
|-------|-------|-------|-------|-------|
|  2-  0|  0-  0|  0-  1|  0-  0|  0-  1|
|-------|-------|-------|-------|-------|
|  2-  0|  0-  1|  2-  0|  1-  2|  0-  1|
|-------|-------|-------|-------|-------|
|  1-  0|  0-  4|  0-  2|  0-  0|  0-  1|
|-------|-------|-------|-------|-------|

Survivors: Grunts-14, Warriors-18
|-------|-------|-------|-------|-------|
|  1-  0|  0-  1|  0-  1|  0-  1|  0-  0|
|-------|-------|-------|-------|-------|
|  0-  0|  0-  0|  0-  0|  0-  3|  0-  0|
|-------|-------|-------|-------|-------|
|  3-  0|  2-  0|  1-  3|  0-  0|  0-  2|
|-------|-------|-------|-------|-------|
|  2-  0|  1-  1|  0-  1|  0-  1|  0-  1|
|-------|-------|-------|-------|-------|
|  3-  0|  0-  2|  1-  1|  0-  0|  0-  0|
|-------|-------|-------|-------|-------|

Survivors: Grunts-13, Warriors-18
|-------|-------|-------|-------|-------|
|  1-  1|  0-  0|  0-  0|  0-  0|  0-  0|
|-------|-------|-------|-------|-------|
|  1-  0|  0-  0|  0-  0|  0-  3|  0-  1|
|-------|-------|-------|-------|-------|
|  1-  1|  1-  2|  0-  2|  1-  1|  0-  0|
|-------|-------|-------|-------|-------|
|  0-  0|  3-  1|  1-  0|  0-  2|  0-  1|
|-------|-------|-------|-------|-------|
|  2-  2|  1-  0|  1-  1|  0-  0|  0-  0|
|-------|-------|-------|-------|-------|

Survivors: Grunts-12, Warriors-17
|-------|-------|-------|-------|-------|
|  1-  1|  0-  0|  0-  0|  0-  0|  0-  1|
|-------|-------|-------|-------|-------|
|  2-  1|  3-  2|  1-  0|  0-  0|  0-  1|
|-------|-------|-------|-------|-------|
|  1-  1|  1-  1|  0-  0|  0-  1|  0-  0|
|-------|-------|-------|-------|-------|
|  1-  0|  0-  1|  1-  0|  0-  3|  0-  1|
|-------|-------|-------|-------|-------|
|  0-  3|  0-  0|  1-  0|  0-  0|  0-  0|
|-------|-------|-------|-------|-------|

Survivors: Grunts-12, Warriors-14
|-------|-------|-------|-------|-------|
|  0-  2|  0-  0|  0-  0|  1-  1|  0-  2|
|-------|-------|-------|-------|-------|
|  0-  1|  3-  0|  0-  0|  0-  1|  0-  2|
|-------|-------|-------|-------|-------|
|  0-  0|  0-  0|  3-  1|  0-  0|  0-  1|
|-------|-------|-------|-------|-------|
|  2-  0|  0-  0|  0-  0|  0-  1|  0-  0|
|-------|-------|-------|-------|-------|
|  3-  1|  0-  0|  0-  1|  0-  0|  0-  0|
|-------|-------|-------|-------|-------|

Survivors: Grunts-12, Warriors-12
|-------|-------|-------|-------|-------|
|  0-  2|  3-  0|  0-  0|  0-  1|  0-  2|
|-------|-------|-------|-------|-------|
|  1-  0|  0-  1|  1-  0|  0-  0|  0-  0|
|-------|-------|-------|-------|-------|
|  2-  0|  1-  1|  0-  0|  0-  0|  0-  1|
|-------|-------|-------|-------|-------|
|  1-  1|  2-  0|  0-  1|  0-  1|  0-  0|
|-------|-------|-------|-------|-------|
|  0-  0|  0-  0|  1-  1|  0-  0|  0-  0|
|-------|-------|-------|-------|-------|

Survivors: Grunts-12, Warriors-12
|-------|-------|-------|-------|-------|
|  2-  0|  0-  0|  0-  0|  0-  1|  0-  0|
|-------|-------|-------|-------|-------|
|  1-  1|  0-  0|  0-  0|  0-  0|  0-  1|
|-------|-------|-------|-------|-------|
|  1-  2|  1-  0|  1-  0|  0-  0|  0-  0|
|-------|-------|-------|-------|-------|
|  1-  0|  1-  1|  0-  0|  1-  1|  0-  0|
|-------|-------|-------|-------|-------|
|  0-  1|  1-  2|  1-  0|  1-  2|  0-  0|
|-------|-------|-------|-------|-------|

Survivors: Grunts-9, Warriors-12
|-------|-------|-------|-------|-------|
|  1-  0|  2-  0|  0-  1|  0-  1|  0-  0|
|-------|-------|-------|-------|-------|
|  0-  0|  0-  0|  2-  0|  0-  0|  0-  1|
|-------|-------|-------|-------|-------|
|  0-  1|  1-  0|  0-  0|  0-  2|  0-  0|
|-------|-------|-------|-------|-------|
|  1-  0|  1-  2|  0-  1|  0-  1|  0-  0|
|-------|-------|-------|-------|-------|
|  1-  0|  0-  2|  0-  0|  0-  0|  0-  0|
|-------|-------|-------|-------|-------|

Survivors: Grunts-0, Warriors-12
Game over! Game took 11 rounds.
Warriors win!

Process finished with exit code 0
```