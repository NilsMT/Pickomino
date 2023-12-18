# Testabilité




## choiceDices

### Données de test
id: Int, key: Int, dices: List<DICE>, Status : STATUS
### Oracle
UnknownIdException
IncorrectKeyException
BadStepException
List<DICE>
### Partitionnement id

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,42[

partition nominale : [42, 2<sup>31</sup>-1]

fonctionnellement : pas de changement

### Partitionnement key

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,0[

partition nominale : ]0, 2<sup>31</sup>-1]

fonctionnellement : pas de changement

### Partitionnement de dices

type : List<DICE>

partition nominale : [D1];[D2];[D3];[D4];[D5];[worm];

### Partitionnement Status

type : STATUS

plage nominale : [GAME_FINISHED],[TAKE_PICKOMINO],[KEEP_DICE],[ROLL_DICE_OR_TAKE_PICKOMINO],[ROLL_DICE] 

### Table de décisions
||||||||
|-----------------|--------|----------------------------|---|---|---|---|
| Données de test | id     | [-2 <sup> 31 </sup> , 42[  | x | / | / |   |
|                 |        | [42, 2 <sup> 31 </sup> -1] |   | / | / | X |
|                 | key    | [-2 <sup> 31 </sup> , 0[   | / | x | / |   |
|                 |        | [0, 2 <sup> 31 </sup> -1]  | / |   | / | X |
|                 | Status | ROLL_DICE                  | / | / | X |   |
|                 |        | KEEP_DICE                  | / | / |   | X |
| Oracle          |        | UnknownIdException         | X |   |   |   |
|                 |        | IncorrectKeyException      |   | X |   |   |
|                 |        | BadStepException           |   |   | X |   |
|                 |        | List<DICE>                 |   |   |   | X |
### Cas de tests
CT1(DT1([41,100,KEEP_DICE]),UnknownIdException)<br>
CT2(DT2([42,-100,KEEP_DICE]),IncorrectKeyException)<br>
CT3(DT3([42,100,ROLL_DICE]),BadStepException)<br>
CT4(DT4([42,100,KEEP_DICE]),List<Int>)<br>

## finalScore
### Données de test
id: Int, key: Int
### Oracle
UnknownIdException<br>
IncorrectKeyException<br>
BadStepException
List<Int>
### Partitionnement id

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,42[

partition nominale : [42, 2<sup>31</sup>-1]

fonctionnellement : pas de changement

### Partitionnement key

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,0]

partition nominale : ]0, 2<sup>31</sup>-1]

fonctionnellement : pas de changement

### Partitionnement Status

type : STATUS

plage nominale : [GAME_FINISHED],[TAKE_PICKOMINO],[KEEP_DICE],[ROLL_DICE_OR_TAKE_PICKOMINO],[ROLL_DICE] 

### Table de décisions
||||||||
|-----------------|--------|----------------------------|---|---|---|---|
| Données de test | id     | [-2 <sup> 31 </sup> , 42[  | x | / | / |   |
|                 |        | [42, 2 <sup> 31 </sup> -1] |   | / | / | X |
|                 | key    | [-2 <sup> 31 </sup> , 0[   | / | x | / |   |
|                 |        | [0, 2 <sup> 31 </sup> -1]  | / |   | / | X |
|                 | Status | ROLL_DICE                  | / | / | X |   |
|                 |        | GAME_FINISHED              | / | / |   | X |
| Oracle          |        | UnknownIdException         | X |   |   |   |
|                 |        | IncorrectKeyException      |   | X |   |   |
|                 |        | BadStepException           |   |   | X |   |
|                 |        | List<Int>                  |   |   |   | X |
### Cas de tests
CT1(DT1([41,100,GAME_FINISHED]),UnknownIdException)<br>
CT2(DT2([42,-100,GAME_FINISHED]),IncorrectKeyException)<br>
CT3(DT3([42,100,ROLL_DICE]),BadStepException)<br>
CT4(DT4([42,100,GAME_FINISHED]),List<Int>)<br>

## gameState
### Données de test
id: Int, key: Int
### Oracle
UnknownIdException<br>
IncorrectKeyException<br>
GAME

### Partitionnement id

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,42[

partition nominale : [42, 2<sup>31</sup>-1]

fonctionnellement : pas de changement

### Partitionnement key

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,0]

partition nominale : ]0, 2<sup>31</sup>-1]

fonctionnellement : pas de changement


### Table de décisions
|||||||
|-----------------|-----|----------------------------|---|---|---|
| Données de test | id  | [-2 <sup> 31 </sup> , 42[  | x | / |   |
|                 |     | [42, 2 <sup> 31 </sup> -1] |   | / | x |
|                 | key | [-2 <sup> 31 </sup> , 0[   | / | x |   |
|                 |     | [0, 2 <sup> 31 </sup> -1]  | / |   | x |
| Oracle | UnknownIdException |   | X |   |
| | IncorrectKeyException     | X |   |   |
| | GAME                      |   |   | X |
### Cas de tests
CT1(DT1([-2 <sup> 31 </sup> , 42[,),UnknownIdException)<br>
CT2(DT2([-2 <sup> 31 </sup> , 0[),IncorrectKeyException)<br>
CT3(DT3([42, 2 <sup> 31 </sup> -1],[0, 2 <sup> 31 </sup> -1]),GAME)<br>

<br>


## keepDices
### Données de test
id: Int, key: Int, dice: DICE, Status : STATUS, listTaken : MutableList<DICE>, listRoll : MutableList<DICE>
### Oracle
UnknownIdException<br>
IncorrectKeyException<br>
BadStepException<br>
DiceNotInRollException<br>
DiceAlreadyKeptException<br>
True<br>
### Partitionnement id

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,42[

partition nominale : [42, 2<sup>31</sup>-1]

fonctionnellement : pas de changement

### Partitionnement key

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,0]

partition nominale : ]0, 2<sup>31</sup>-1]

fonctionnellement : pas de changement

### Partitionnement dice

type : List<DICE>

partition nominale : [D1];[D2];[D3];[D4];[D5];[worm];

### Partitionnement Status

type : STATUS

plage nominale : [GAME_FINISHED],[TAKE_PICKOMINO],[KEEP_DICE],[ROLL_DICE_OR_TAKE_PICKOMINO],[ROLL_DICE] 

### listTaken 
type : MutableList<DICE>

partition nominale : [D1];[D2];[D3];[D4];[D5];[worm];

### Partitionnement listRoll
type : MutableList<DICE>

partition nominale : [D1];[D2];[D3];[D4];[D5];[worm];

### Table de décisions
|                 |           |                            |                    |                       |                  |                          |                        |      |
|-----------------|-----------|----------------------------|--------------------|-----------------------|------------------|--------------------------|------------------------|------|
| Données de test | id        | [-2 <sup> 31 </sup> , 42[  | x                  | /                     | /                |                          |                        |      |
|                 |           | [42, 2 <sup> 31 </sup> -1] |                    | /                     | /                |                          |                        |      |
|                 | key       | [-2 <sup> 31 </sup> , 0[   | /                  | x                     | /                |                          |                        |      |
|                 |           | [0, 2 <sup> 31 </sup> -1]  | /                  |                       | /                |                          |                        |      |
|                 | dice      | [D1]                       |                    |                       |                  | X                        | X                      | X    |
|                 | Status    | TAKE_PICKOMINO             | /                  | /                     | X                |                          |                        |      |
|                 |           | KEEP_DICE                  | /                  | /                     |                  |                          |                        |      |
|                 | listTaken | [D2]                       | /                  | /                     | /                |                          |                        | X    |
|                 |           | [D1]                       | /                  | /                     | /                | X                        |                        |      |
|                 | listRoll  | [D1]                       | /                  | /                     | /                |                          |                        | X    |
|                 |           | [D2]                       | /                  | /                     | /                |                          | X                      |      |
| Oracle          | |UnknownIdException       | X                         |   |   |   |   |   | 
|                 | |IncorrectKeyException    |                           | X |   |   |   |   | 
|                 || BadStepException         |                           |   | X |   |   |   | 
|                 | |DiceAlreadyKeptException |                           |   |   | X |   |   | 
|                 | |DiceNotInRollException   |                           |   |   |   | X |   | 
|                 | |True                     |                           |   |   |   |   | X | 

### Cas de tests 
CT1(DT1([41,100,D1,KEEP_DICE,D2,D1]),UnknownIdException)<br>
CT2(DT2([42,-100,D1,KEEP_DICE,D2,D1]),IncorrectKeyException)<br>
CT3(DT3([42,100,D1,TAKE_PICKOMINO,D2,D1]),BadStepException)<br>
CT4(DT4([42,100,D1,KEEP_DICE,D1,D1]),DiceAlreadyKeptException)<br>
CT5(DT5([42,100,D1,KEEP_DICE,D2,D2]),DiceNotInRollException)<br>
CT6(DT6([42,100,D1,KEEP_DICE,D2,D1]),True)

<br>

## newGame
### Données de test
nbPlayers: Int
### Oracle
Pair(-1,-1)
Pair(]0, 2<sup>31</sup>-1],]0, 2<sup>31</sup>-1])
### Partitionnement nbPlayers
type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,2[,]4,2<sup>31</sup>-1]

partition nominale : [2,4]

fonctionnellement : pas de changement
### Table de décisions

|   |   |   |   |   |   |   |   |
|---|---|---|---|---|---|---|---|
| Données de test |nbPlayers | [-2<sup>31</sup>,2[ | X |  |  |
|   || [2,4] |  | X |  |
|   || ]4,2<sup>31</sup>-1] |  |  | X |
| Oracle |
|    || Pair(-1,-1) | X | | X |
|   || Pair(]0, 2<sup>31</sup>-1],]0, 2<sup>31</sup>-1]) | | X | |
### Cas de tests
CT1(DT1([1]),Pair(-1,-1))<br>
CT2(DT2([3]),Pair(]0, 2<sup>31</sup>-1],]0, 2<sup>31</sup>-1]))<br>
CT3(DT3([5]),Pair(-1,-1))<br>

<br>

## rollDices
### Données de test
id: Int, key: Int, Status : STATUS
### Oracle
UnknownIdException
IncorrectKeyException
BadStepException
List<DICE>

### Partitionnement id

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,42[

partition nominale : [42, 2<sup>31</sup>-1]

fonctionnellement : pas de changement

### Partitionnement key

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,0]

partition nominale : ]0, 2<sup>31</sup>-1]

fonctionnellement : pas de changement

### Partitionnement Status

type : STATUS

plage nominale : [GAME_FINISHED],[TAKE_PICKOMINO],[KEEP_DICE],[ROLL_DICE_OR_TAKE_PICKOMINO],[ROLL_DICE] 

### Table de décisions
||||||||
|-----------------|--------|----------------------------|---|---|---|---|
| Données de test | id     | [-2 <sup> 31 </sup> , 42[  | x | / | / |   |
|                 |        | [42, 2 <sup> 31 </sup> -1] |   | / | / | X |
|                 | key    | [-2 <sup> 31 </sup> , 0[   | / | x | / |   |
|                 |        | [0, 2 <sup> 31 </sup> -1]  | / |   | / | X |
|                 | Status | ROLL_DICE                  | / | / |   | X |
|                 |        | KEEP_DICE                  | / | / | X |   |
| Oracle          |        | UnknownIdException         | X |   |   |   |
|                 |        | IncorrectKeyException      |   | X |   |   |
|                 |        | BadStepException           |   |   | X |   |
|                 |        | List<DICE>                 |   |   |   | X |
### Cas de tests
CT1(DT1([41,100,ROLL_DICE,]),UnknownIdException)<br>
CT2(DT2([42,-100,ROLL_DICE]),IncorrectKeyException)<br>
CT3(DT3([42,100,KEEP_DICE]),BadStepException)<br>
CT4(DT4([42,100,ROLL_DICE]),PickominoChosenException)<br>


<br>



## takePickomino
### Données de test
id: Int, key: Int, pickomino: Int, Status : STATUS
### Oracle
UnknownIdException
IncorrectKeyException
BadStepException
PickominoChosenException
True
### Partitionnement id

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,42[

partition nominale : [42, 2<sup>31</sup>-1]

fonctionnellement : pas de changement

### Partitionnement key

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,0]

partition nominale : ]0, 2<sup>31</sup>-1]

fonctionnellement : pas de changement

### Partitionnement pickomino

type : Int

plage : [-2<sup>31</sup>, 2<sup>31</sup>-1]

partition expectionnelle : [-2<sup>31</sup>,21[,]36, 2<sup>31</sup>-1]

partition nominale : [21,36]

fonctionnellement : pas de changement

### Partitionnement Status

type : STATUS

plage nominale : [GAME_FINISHED],[TAKE_PICKOMINO],[KEEP_DICE],[ROLL_DICE_OR_TAKE_PICKOMINO],[ROLL_DICE] 

### Table de décisions
|   |   |   |   |   |   |   |   |   |
|-----------------|-----------|----------------------------|---|---|---|---|---|---|
| Données de test | id        | [-2 <sup> 31 </sup> , 42[  | X | / | / | / | / |   |
|                 |           | [42, 2 <sup> 31 </sup> -1] |   | / | / | / | / | X |
|                 | key       | [-2 <sup> 31 </sup> , 0[   | / | X | / | / | / |   |
|                 |           | [0, 2 <sup> 31 </sup> -1]  | / |   | / | / | / | X |
|                 | pickomino | [-2<sup>31</sup>,21[       | / | / | X |   | / |   |
|                 |           | [21,36]                    | / | / |   |   | / | X |
|                 |           | ]36, 2<sup> 31 </sup>-1]   | / | / |   | X | / |   |
|                 | Status    | TAKE_PICKOMINO             | / | / | / | / |   | X |
|                 |           | KEEP_DICE                  | / | / | / | / | X |   |
| Oracle          |           | UnknownIdException         | X |   |   |   |   |   |
|                 |           | IncorrectKeyException      |   | X |   |   |   |   |
|                 |           | BadStepException           |   |   |   |   | X |   |
|                 |           | PickominoChosenException   |   |   | X | X |   |   |
|                 |           | True                       |   |   |   |   |   | X |
### Cas de tests
CT1(DT1([41,100,22,TAKE_PICKOMINO,]),UnknownIdException)<br>
CT2(DT2([42,-100,22,TAKE_PICKOMINO]),IncorrectKeyException)<br>
CT3(DT3([42,100,21,TAKE_PICKOMINO]),PickominoChosenException)<br>
CT4(DT4([42,100,37,TAKE_PICKOMINO]),PickominoChosenException)<br>
CT5(DT5([42,100,22,KEEP_DICE]),BadStepException)<br>
CT6(DT6([42,100,22,TAKE_PICKOMINO]),True)<br>