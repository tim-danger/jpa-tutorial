# jpa-tutorial #
The project can be built using "mvn clean install". Before getting started, we have to set up the database. I have used MySQL in this example. The most difficult part is to set up the database and the application servers accordingly.

### Install MySQL ###
You can either use docker or check the installation guide for your operating system. This is very well documented, just google it.

Next, we need a user for our database. You can follow this article to create one: https://www.digitalocean.com/community/tutorials/how-to-create-a-new-user-and-grant-permissions-in-mysql-de. In my example I called the user *JPA*, feel free to choose your own username.

The next step is to create a database that we need to access within our application:

CREATE DATABASE jpatutorial;

Again, this is just a recommendation, feel free to use your own database-name. After that we need to guarantee the rights to the above user:

GRANT ALL PRIVILEGES ON jpatutorial. * TO 'JPA'@'localhost';

Keep in mind that **JPA** is the user-name and **jpatutorial** is the database-name. You have to adjust parameters here, if you chose another ones. If this command fails, you might have to restart your computer and try it again.

FLUSH PRIVILEGES;

And you should be fine. The next section describes the configuration of the application servers:

### Configure Glassfish 4.1.2 ### 
This requires some adjustments (the JSon deserialization does not work due to a java.lang.NoClassDefFoundError, which is a bug reported here: https://github.com/eclipse-ee4j/glassfish/issues/21440). You can use the following workaround:

"As a quick & dirty fix, one can find glassfish/modules/org.eclipse.persistence.moxy.jar and fix META-INF/MANIFEST.MF inside it. Simply append the following to Import-Package:

,org.xml.sax.helpers,javax.xml.parsers;resolution:=optional,javax.naming;resolution:=optional

and restart."

Maybe this works with future versions of Glassfish (which is actually the reference implementation, however not with this specific version).

You can start and stop Glassfish doing the following (form a terminal):

```
Start:
cd {glassfish_home}/glassfish/bin
./asadmin start-domain --debug domain1
Stop:
./asadmin stop-domain domain1
```

#### Setting up the data-source for Glassfish 4.1.2 ####
You need to place the mysql-connector (you'll need an old version) to the lib folder glassfish4/glassfish/lib/mysql-connector-java-5.1.47.jar. For the setup you can follow this excellent guide: https://javatutorial.net/configure-glassfish-mysql/. You have to add the properties he refers to via the add-Button.

I had to add one additional property to make it work (in the JDBC-Connection-Pool): useSSL is the name of the property, false is its value.

> :warning: the JNDI-Name in the JDBC Resources has to be the same as the one in the tag **jta-data-source** in your **persistence.xml**. This file is located in the src/main/resources/META-INF-folder of this project. 

### Configure Wildfly 26.1.1. Final ### 
As there is no bug as in the Glassfish-Server, you only have to configure the data-source:

#### Setting up the data-source for Wildfly 26.1.1. Final ####
First you'll again need the database-driver. In this case the **mysql-connector-java-8.0.29.jar** works fine. Maybe you'll need an Oracle account to be able to download it. Next you'll need a script with the following content:

```
embed-server --server-config=standalone.xml --std-out=echo

batch

#
# add the module
#
module add --name=com.mysql --resources=PATH_TO_YOUR_DRIVER/mysql-connector-java-8.0.29.jar --dependencies=javax.api,javax.transaction.api

#
# create the driver
#
/subsystem=datasources/jdbc-driver=mysql:add(driver-name="mysql",driver-module-name="com.mysql",driver-class-name=com.mysql.cj.jdbc.Driver)

#
# create the datasource
#
/subsystem=datasources/data-source=jpaTutorial/:add(connection-url=jdbc:mysql://localhost:3306/jpatutorial,driver-name=mysql,jndi-name=java:jboss/datasources/jpaTutorial,password=YOUR_PASSWORD,user-name=JPA)

run-batch
```
> :warning: again the JNDI-Name has to be the same as the one in the tag **jta-data-source** in your **persistence.xml**. This file is located in the src/main/resources/META-INF-folder of this project. Please adjust the path to your DB-driver and also the name of your database, user and most important your **password**. 

Once you've made the adjustments please copy the contents to a file named **create_resources.cli** and save it to {your_wildfly_home}/bin. Once done, execute the following command from a terminal (depending on your operating system, it is either bat or sh):
```
Linux:
./jboss-cli.sh --file=create_resources.cli
Windows:
jboss-cli.bat --file=create_resources.cli
```
If there is no error-message coming up and it states "The batch executed successfully" your data-source should be configured properly.

```
Start:
cd {wildfly_home}/bin
./standalone.sh
Stop:
You can just hit CTRL+C
```
