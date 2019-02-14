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
