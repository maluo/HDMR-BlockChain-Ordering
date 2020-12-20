# EC Hadoop MapReduce Word Count
Author: HBF  
Date: 2020-11-11 (update)  


This project is to get hand on Hadoop MapReduce programming


1. Export the ec-hdmr-wc to deployable jar file for Hadoop as follows.  

Right click ec-htmr-wc-mvn -> Export -> Java -> Jar -> Select the export destination 

Type file name ec-hdmr-wc.jar , Save -> Next, Next, in the JAR Manifest Specification pop-up, check **Generate the manifest** -> Finish. 

2. Use 7zip to explore ec-hdmr-wc.jar, for example, view META-INF/MANIFEST.MF you will find it only contains the following line 

~~~
Manifest-Version: 1.0
~~~

3. Deploy and run ec-hdmr-wc.jar

Assume that wordcount.txt in HDFS under directory /ec

~~~
hadoop jar ec-hdmr-wc.jar ec.lab.WordCount /ec/wordcount.txt /output
~~~

