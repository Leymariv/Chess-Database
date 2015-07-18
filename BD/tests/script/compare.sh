#!/bin/bash

for p in `seq 1 7`
do
	for x in `seq 1 7`
	do
		for y in `seq 1 7`
		do

			ATTENDU="ERROR"
			RES=`grep $ATTENDU pion$p\_deplacement_$x\_$y.lst`
			# test si la variable est vide
			if [ -z $RES ]
			then
				echo "!!!!! Problème dans le fichier pion$p\_deplacement_$x\_$y"
			fi
		done
	done
done

