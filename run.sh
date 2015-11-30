echo "Enter the number '1' to process largegraph1, '2' to process largegraph2,"
echo "or '3' to process smallgraph."
read choice
java -Xmx2048m -cp bin GraphColoring $choice
