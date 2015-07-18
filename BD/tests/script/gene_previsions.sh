#!/bin/bash

for p in `seq 1 7`
do
	for x in `seq 1 7`
	do
		for y in `seq 1 7`
		do
			#on génère les résultats attentus
			touch pion$p\_deplacement_$x\_$y.oracle
			echo 'check constraint (KOENIGR.DEPLACEMENT) violated'>>pion$p\_deplacement_$x\_$y.oracle

		done
	done
done
