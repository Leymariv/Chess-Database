#!/bin/bash
# Pour se connecter à la BD

for p in `seq 1 7`
do
	for x in `seq 1 7`
	do
		for y in `seq 1 7`
		do
			sqlplus /nolog @pion$p\_deplacement_$x\_$y\.sql
		done
	done
done

