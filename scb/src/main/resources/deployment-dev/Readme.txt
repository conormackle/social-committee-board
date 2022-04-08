Create a JAR file by going to Maven tools - Lifecycle - right click 'package' - Run Maven Build

This will create a JAR in the project 'target' folder

To deploy to AWS EBS create a source bundle (.zip):

zip the following files:

	1. .ebextensions
	2. .platform
	3. Procfile
	4. project JAR file

Upload the zip file to the EBS console on AWS