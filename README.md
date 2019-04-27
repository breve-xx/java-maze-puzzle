# java-maze-puzzle
This is a simple Java/Maven application that walks labyrinths looking for objects.

Please note that all the commands should be executed inside the `/maze` folder.

### Docker
A Maven docker image can be created using
```
docker build -t mytest .
```
### Scripts
#### build.sh
The `build.sh` script can be used to generate an executable `.jar` file for the application.
##### A docker container that runs the `build.sh` script can be created using
```
docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt mytest ./scripts/build.sh
```
Please note that automatic test phase is intentionally excluded from this script.
#### test.sh
The `test.sh` script can be used to run all the automatic tests.
##### A docker container that runs the `test.sh` script can be created using
```
docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt mytest ./scripts/test.sh
```
#### run.sh
The `run.sh` script can be used to run the application, if an executable `.jar` is not present it will be built.
##### A docker container that runs the `run.sh` script can be created using
```
docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt mytest ./scripts/test.sh
```
##### An additional volume that provides additional mazes to the application can be mounted using
```
docker run -v $(pwd):/mnt -v <path/to/mazes/folder>:/mazes -p 9090:9090 -w /mnt mytest ./scripts/run.sh
```
### Input format
##### Labyrinth
```
{
  "start": Integer,
  "objects": Array, // of String
  "rooms": Array // of Room
}
```
##### Room
```
{
  "id": Integer,
  "name": String,
  "north": Integer,
  "south": Integer,
  "west": Integer,
  "east": Integer,
  "objects: Array // of RoomObject
}
```
##### RoomObject
```
{
  name: String
}
```
### Behaviors
#### Paths
The application will try to *walk* all the mazes with `.json` extension found in the `/mazes` directory. If the `/mazes` directory is not present a *demo walk* is made using those in `maze/examples` folder.
#### Start
The application will start to *walk* from the room specified in the `start` field in the input object, if it's not present one of the room present in the labyrinth is chosen.
#### Obejcts to collect
A list of object to be collected during the *walk* could be provided to the application using the `objects` in the input object, if it's not present all the objects in the labyrinth will be collected.
### Output
The application will *walk* through the rooms of the provided labyrinth until all the requested objects are colleted or alternatively all the rooms have been visited; once the *walk* is completed the path taken will be shown.
