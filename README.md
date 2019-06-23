# Frequency Analysis Translator

Uses for translation (`trans.sh`):
[https://github.com/soimort/translate-shell](https://github.com/soimort/translate-shell)

Prerequisite: install gawk (for Mac OS: `brew install gawk`)

##### Usage:
```java
new Analyzer("marchen.txt", "de:en").analyze().printAsCsv();
```
Where `marchen.txt` is a name of a resource file with text for analysis.

Translation direction can be changed (see trans.sh).

##### Output (it will print an ordered CSV structure):
```csv
<word>; <frequency>; <transaltion>
```

##### Example:
```text
wir; 10; we
uns; 8; us
märchen; 7; fairy tale, fairy tales
und; 5; and
dass; 4; that
so; 4; so
wie; 4; as, how, like
für; 3; For
heute; 3; today
immer; 3; always
lass; 3; let
nie; 3; never
schwörn; 3; schwörn
verliern; 3; lose a, lose a single
wahr; 3; true
werden; 3; become, will, will become
der; 2; the, of the
ein; 2; on, a, one
er; 2; he
erste; 2; first
himmel; 2; sky, heaven
liebe; 2; love, dear
meine; 2; my, mine
spieln; 2; play
war; 2; was, been, had, were
wenn; 2; if, when
wieder; 2; again, once again
an; 1; on, at, to
bevor; 1; before
endlich; 1; Finally, at last
es; 1; it
fühlt; 1; feels, feel
gefiel; 1; liked, pleased
haben; 1; to have, have
herz; 1; heart
hochfliegt; 1; flying high, flies high, flies up, is lifted up, Flies Upward
ich; 1; I
ihn; 1; him, it
jahrn; 1; Year n, partent
jede; 1; each, any, every, all
jeden; 1; each, every, any, everyone, all
junge; 1; Boy
kannte; 1; knew, know, known, familiar, had known
keiner; 1; none, no, no one, neither, nobody
leben; 1; Life, live
machte; 1; made, making, did, took, went
mein; 1; my
mich; 1; me
mir; 1; me
nacht; 1; night
nah; 1; close
neues; 1; new
niemand; 1; nobody, no one
schaffte; 1; managed, succeeded, abolished, managed to
schien; 1; appeared, seemed, seemed to, looked, appeared to
schöner; 1; more beautiful, beautiful
seit; 1; since
sich; 1; yourself, themselves, herself, himself
tag; 1; Day
traurig; 1; Sad
unendlich; 1; infinitely, endless, endlessly, infinite, an infinite
verliebt; 1; in love, amorous
vielen; 1; a lot of, lot, lots of, many, lots
warn; 1; warning, cautionary, warn, alarm, of warning
zerbrach; 1; broke, racked, shattered, broke up, collapsed
zerstritten; 1; estranged, fallen out, at odds, at loggerheads, at odds with
über; 1; over, above, about, via, by
```

##### Issues:
`trans.sh` has connections/time limitations, use different proxy servers as a work around