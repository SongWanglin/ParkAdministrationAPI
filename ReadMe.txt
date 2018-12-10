Project Description: http://www.cs.iit.edu/~virgil/cs445/mail.fall2018/project/project.html

Project API requirement: http://www.cs.iit.edu/~virgil/cs445/mail.fall2018/project/project-api.html

About environment set up:

sudo apt-get update

sudo apt-get install default-jdk

sudo apt-get install -y junit4

sudo apt-get install -y tomcat8

sudo service tomcat8 start


About Build And Run:

cd to the project directory

cd src

javac -cp ../WebContent/WEB-INF/lib/*:. edu/iit/cs445/StateParking/Objects/*.java edu/iit/cs445/StateParking/ObjectsEnum/*.java edu/iit/cs445/StateParking/REST/*.java;

cd ..

mkdir WebContent/WEB-INF/classes

cp -R src/edu WebContent/WEB-INF/classes

cd WebContent/WEB-INF/classes

find . -name "*.java" -type f -delete

cd ../../../src

find . -name "*.class" -type f -delete

cd ../WebContent

jar cvf ParkAdmin.war WEB-INF

sudo cp ParkAdmin.war /var/lib/tomcat8/webapps

sudo service tomcat8 restart

Now you can make http requests through http://localhost:8080/ParkAdmin/parkpay/...


sudo apt-get -y install python3 python-pip python-dev
sudo apt-get -y install python3-pip
pip3 install jupyter
cd
jupyter notebook

Run ParkPay URL request.ipynb under StatePark directory to make some http request 




About Unit Test:
cd to the project directory

cd src

javac -cp ../WebContent/WEB-INF/lib/*:/usr/share/java/junit4-4.12.jar:. edu/iit/cs445/StateParking/UnitTest/*.java

java -cp /usr/share/java/junit4-4.12.jar:. org.junit.runner.JUnitCore edu.iit.cs445.StateParking.UnitTest.TestSuit

find . -name "*.class" -type f -delete





For testing and debugging it under eclipse IDE:

1. $ sudo apt-get install default-jdk
2. Download and install Eclipse Photon
3. install:
    Eclipse Java EE Developer Tools
    Eclipse Java Web Developer Tools
    Eclipse Web Developer Tools
    JST Server Adapters
    JST Server Adapters Extensions
4. import the StatePark.zip
5.   $ cd ~/Downloads
  $ wget http://apache.cs.utah.edu/tomcat/tomcat-8/v8.5.34/bin/apache-tomcat-8.5.34.zip
  $ unzip apache-tomcat-8.5.34.zip
  $ sudo mv apache-tomcat-8.5.34 /opt/tomcat
  $ cd /opt/tomcat/bin
  $ chmod 744 *sh
  $ ./startup.sh
	$ ./shutdown.sh
6. Build Path->Configure Build Path, Select JRE System Library->Workspace Default JRE
7. 
    Go to Window > Preferences then select Server > Runtime Environments
    Click on Add to create a new Runtime, then select "Apache Tomcat v8.5" and leave the "Create a new local server" box unchecked. Click on Next.
    Type /opt/tomcat in the "Tomcat installation path" text box. Click Finish.
    Dismiss the Preferences view by clicking on "Apply and Close".
    Right click on the project name and select Properties then select "Targeted Runtimes". Uncheck the box for "Apache Tomcat v8.0" and check the box for "Apache Tomcat v8.5" then "Apply and Close".
8. Create "Apache Tomcat v8.5 Server" and deploy the project
9. apt-get -y install python 3
   apt-get -y install python-pip
   apt-get -y install python-dev
   apt-get -y install ipython ipython-notebook
   pip install jupyter
10. jupyter notebook
    Run the "ParkPay URL request.ipynb"

