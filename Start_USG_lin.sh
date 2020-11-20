#!/bin/bash
direct="$PWD"
verjava=$(java -version 2>&1 >/dev/null | grep 'java version' | awk '{print $3}')
if [ -n "$verjava" ]; then
	java -jar "$direct"/USG.jar
else
	chmod -R 755 "$direct"/bin
	chmod 755 "$direct"/USG.jar
	"$direct"/bin/jre_lin/bin/java -jar "$direct"/USG.jar
fi