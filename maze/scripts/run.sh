if [ ! -f ../target/maze-1.0-SNAPSHOT-jar-with-dependencies.jar ]; then
	echo "maze-1.0-SNAPSHOT-jar-with-dependencies.jar not found, I'll build it for you."
	./build.sh
fi

cd ../target

java -jar maze-1.0-SNAPSHOT-jar-with-dependencies.jar