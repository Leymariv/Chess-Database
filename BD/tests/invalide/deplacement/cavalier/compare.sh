#!/bin/bash

for x in `ls *.lst`
do

	ATTENDU="ERROR"
	RES=`grep $ATTENDU $x`
	# test si la variable est vide
	if [ -z "$RES" ]
	then
		echo "/!\ Pas de chaine : $ATTENDU trouvée dans le fichier $x /!\ "
	fi
done
