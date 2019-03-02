# translation-methods

## Lab 1
Calculating simple expression language with the following grammar:
```
expr:
  term +- term +- ...
;
  
term:
  factor */ factor */ ...
;

factor:
  power ^ factor | power
;

power:
  atom | -atom
;

atom:
  number | (expr)
;
```
Test results with coverage can be found in `lab1/doc`.

## Lab 2
Construction of a finite state machine according to a given configuration and line recognition.

Input configuration must have the following structure:
```
<states-number>
<list of finite states>
<state-from> <symbol> <state-to>
<state-from> <symbol> <state-to>
...
<state-from> <symbol> <state-to>
```

Input configuration example:
```
5
2 3
1 a 2
1 b 3
2 a 2
2 b 5
3 a 4
3 b 3
4 a 4
4 b 3
5 a 2
5 b 5
```
Example describes the following FSM:
