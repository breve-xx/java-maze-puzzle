if [ ! -f target/maze-1.0-SNAPSHOT-jar-with-dependencies.jar ]; then
	echo "maze-1.0-SNAPSHOT-jar-with-dependencies.jar not found, I'll build it for you."
	mvn clean package -DskipTests=true
fi

java -jar target/maze-1.0-SNAPSHOT-jar-with-dependencies.jar