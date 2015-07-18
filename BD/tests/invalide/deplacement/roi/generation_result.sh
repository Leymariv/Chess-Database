#!/bin/bash
# Pour se connecter à la BD


for x in `ls *.sql`
do
	sqlplus /nolog @$x
done

