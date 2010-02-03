#!/bin/bash
# declare STRING variable
STRING="Compiling parser for JAXB"
#print variable on a screen
echo $STRING
xjc -p org.knipsX.utils.JAXB.parser project.xsd
echo "Copying..."
cp org/knipsX/utils/JAXB/parser/*.java $HOME/SOFT_PR/git_clone3/tempX/Implementierung/src/org/knipsX/utils/JAXB/parser
echo "Compiling..."
javac  $HOME/SOFT_PR/git_clone3/tempX/Implementierung/src/org/knipsX/utils/JAXB/parser/*.java 
echo "Ready."
 
