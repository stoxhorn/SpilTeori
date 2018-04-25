# Mads' TO DO:

Enten i ThreeRowGameTree eller ThreeNode

// udregning af scoren for hvert node, skal finde ud af om det skal være i træ eller node, eller en kombi

MinMax()

-------------------

// Ved samme lejlighed måske ændre navne og tal fra chancer og procenter til score eller point

_____________________________________________________________________________________________________________

laves i ThreeRowGameTree:

// P.T. tager den et Field som parameter, hvilket ikke giver mening da et Field ikke peger til en GameNode,

// skal nok snarer tage en GameNode eller index

// Bliver den over hovedet brugt?

setCursor()

// en getter til en node i spiltræet
getNode(int)

-----------------------
____________________________________________________________________________________________
Laves i ThreeNode
// En værdi der forholder sig til dybde, med relevante getters og setters

// Brug for:
getDepth()
setDepth(int)

Depths skal være 0, ved initialization
____________________________________________________________________________________________

Laves i ThreeGame:

// En metode for newMove som kun tager et field som værdi
newMove(Field)




Reserveret:

Mads:
// Lave noget med dybde, sådan at der kan implementers minmax, i opbygningen af træet - Mads: kunne jeg godt tænke mig, da jeg lavede træet


// En metode der laver boardet, sådan at fields har rigtige kollonner og rækker,samt eri rigtig rækkefølge

createBoard() - Mads: Jeg er i gang med den her

____________________________________________________________________________________________
Jakob:
____________________________________________________________________________________________
FIXED:
ThreeNode constructor takes a gameNode now

ThreeGame getEmptyFields() now uses ArrayList for all uses

ThreeGame getEmpty works

ThreeGame checkWin added

ThreeGame makeMoveAI added

ThreeNode getIndex added

ThreeNode getField added

ThreeField getPos added

