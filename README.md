# food-trucks

###Food Trucks###
A service that tells the user what types of food trucks might be found near a specific location on a map.

It mashes up the following APIs to achieve its goal.

-Google Maps

-Google GeoLocation

-DataSF

###The Technology Stack###
The following technologies are used to implement Food Trucks V1.0

<ol>
<li>Bootstrap 3.0 | JS | JQuery | CSS </li>
<li>Spring | Java | Maven</li>
<li>3rd Party APIs</li>
</ol>

###How to start developing###
Clone the project:

git clone https://github.com/ayulover007/food-trucks.git

###How to deploy###
Please deploy the app only when there are 0 test failures and 0 test errors. To run tests:

mvn test
```
To generate war file to be deployed:
```
mvn package -U

run the file to generate JAR.
```
nohup java -jar  food-trucks.jar  &
```

###Unit Tests###
Under src/main/test a set of unit tests are included. Whenever a feature is implemented and/or modified please include all tests that validate those changes. Make sure all tests are passing.

