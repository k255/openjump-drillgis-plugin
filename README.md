# openjump-drillgis-plugin

openjump-drillgis-pluigin is an experimental extension for [OpenJump GIS] that allows execution of spatial queries against Apache Drill with DrillGis contrib module.

![OpenJump DrillGIS](https://pbs.twimg.com/media/CRGThq8UwAAuAWz.png:large)

## Installation
Clone/download the sourcecode and build with maven
```sh
$ mvn clean package
```
You should have target/DrillGISPlugin-1.3.0.jar now.

Download OpenJump portable snapshot build:

http://sourceforge.net/projects/jump-pilot/files/OpenJUMP_snapshots/OpenJUMP-Portable-20151115-r4552-CORE.zip/download

then copy target/DrillGISPlugin-1.3.0.jar to OpenJump ext directory. Also ensure to copy drill-jdbc-all-1.2.0.jar in the same place.

Start Apache Zookeeper:
```sh
zookeeper/bin/zkServer.sh start
```
Start Apache Drill (with gis contrib module available as [drill-gis plugin] or included in [Apache Drill master branch]):
```sh
apache-drill-1.3.0/bin/drillbit.sh start
```
Start OpenJump
```sh
OpenJump/bin/oj_linux.sh
```

## Usage

Select 'File' -> 'Run Datastore Query'

Click 'Connection Manager' icon and click 'Add'

Choose DrillGis as driver and specify drill server name i.e. localhost

After connecting to drill you are ready to specify sample query:

```
select columns[2] as city, ST_Point(columns[4], columns[3])
    from cp.`sample-data/CA-cities.csv`
    where
        ST_Within(
            ST_Point(columns[4], columns[3]),
            ST_GeomFromText(
                'POLYGON((-121.95 37.28, -121.94 37.35, -121.84 37.35, -121.84 37.28, -121.95 37.28))'
                )
            )
```

which shows points from our dataset limited to region near San Jose as shown here:
* [Sample dataset filtered based on polygon]

## Author

Karol Potocki

## License
----

GPL 2.0 License

   [OpenJump GIS]: <http://www.openjump.org>
   [drill-gis plugin]: <https://github.com/k255/drill-gis>
   [Apache Drill master branch]: <https://github.com/apache/drill>
   [Apache Big Data]: <http://events.linuxfoundation.org/events/apache-big-data-europe>
   [my fork of Apache Drill with drill-gis]: <https://github.com/k255/drill.git>
   [Tugdual Grall's talk]: <http://events.linuxfoundation.org/sites/events/files/slides/apache_drill_budapest_2015.pdf>
   [cities of the world]: <http://www.opengeocode.org/download.php#cities>
   [PostGIS documentation]: <http://postgis.net/docs/reference.html>
   [Sample dataset visualized]: <http://bl.ocks.org/anonymous/raw/20d87dd21e936ea3d314>
   [Sample dataset filtered based on polygon]: <http://bl.ocks.org/d/ad56a1c850d03675c2d9>
   [Sample dataset filtered based on distance]: <http://bl.ocks.org/d/cc5a6d695f3a915db5ad>
