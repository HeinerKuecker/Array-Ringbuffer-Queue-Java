# Array-Ringbuffer-Queue-Java
A queue based on a ringbuffer, which in turn is based on a array

Version with array access over division rest (modulo).


Wenn die Schreibposition und die Leseposition die Feldgrenzen verlassen, werden beide auf den Feldbereich reduziert.

When the writing position and the reading position leave the field boundaries, both are reduced to the field area.

If the get and the set position leafs the array bound, both values will be reduced to values within array bound.


Deshalb koennen die Indexwerte das Doppelte der Groesse erreichen.

Therefore, the index values can reach twice the size.


Deshalb muss die Groesse des Puffers kleiner als der halbe maximale Integer-Zahl sein.

Therefore, the size of the buffer must be less than half the maximum integer number.


~~~
Example: maxSize 2

+---+---+
|   |   |
+---+---+
  ^
  |
  +-- getIndex == 0, setIndex == 0

0123->number line
Set
Get


Add 1
+---+---+
| 1 |   |
+---+---+
  ^   ^
  |   |
  |   +-- setIndex == 1
  +--- getIndex == 0

0123->number line
 Set
Get


Add 2
+---+---+
| 1 | 2 |
+---+---+
  ^
  |
  +-- getIndex == 0, setIndex == 2 ( setIndex % 2 == 0 )

0123->number line
  Set
Get


Take 1
+---+---+
| 1 |   |
+---+---+
  ^   ^
  |   |
  |   +-- getIndex == 1
  +--- setIndex == 2 ( setIndex % 2 == 0 )

0123->number line
  Set
 Get


Add 3
+---+---+
| 3 | 2 |
+---+---+
      ^
      |
      +-- getIndex == 1, setIndex == 3 ( setIndex % 2 == 1 )

0123->number line
   Set
 Get


Take 2
+---+---+
| 3 |   |
+---+---+
  ^   ^
  |   |
  |   +-- setIndex == 3 ( setIndex % 2 == 1 )
  +-- getIndex == 2 ( getIndex % 2 == 0 )

0123->number line
   Set
  Get

Reduce Index
+---+---+
| 3 |   |
+---+---+
  ^   ^
  |   |
  |   +-- setIndex == 1
  +-- getIndex == 0

0123->number line
 Set
Get


Take 3
+---+---+
|   |   |
+---+---+
      ^
      |
      +- getIndex == 1, setIndex == 1

0123->number line
 Set
 Get
~~~


This repository contains an eclipse project ARRAY_RINGBUFFER_QUEUE.

The unit test used JUnit4.
