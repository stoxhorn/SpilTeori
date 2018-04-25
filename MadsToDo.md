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


_____________________________________________________________________________________________




____________________________________________________________________________________________
Laves i ThreeNode
// En værdi der forholder sig til dybde, med relevante getters og setters
  // Skal måske vente til dybde er taget højde for i træet
____________________________________________________________________________________________

Reserveret:

Mads:
// Lave noget med dybde, sådan at der kan implementers minmax, i opbygningen af træet - Mads: kunne jeg godt tænke mig, da jeg lavede træet


// En metode der laver boardet, sådan at fields har rigtige kollonner og rækker,samt eri rigtig rækkefølge

createBoard() - Mads: Jeg er i gang med den her

____________________________________________________________________________________________
Jakob:
Laves i Threegame:

// En metode der checker om spillet er vundet

checkWin()

------------------------------------

// En metode der tilføjer et træk

newMove()
____________________________________________________________________________________________



FIXED:

ThreeNode constructor tager ikke en GameNode

ThreeGame getEmptyFields() uses arrayList not []


Laves i GameBoard, med en getter i ThreeGame

// giv en liste af alle tomme felte på brættet

getEmptyFields()

laves i ThreeNode

-------------------

// en getter for index værdien for noden

getIndex()


