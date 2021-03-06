# food-trucks

###Food Trucks###
A service that tells the user what types of food trucks might be found near a specific location on a map.

It mashes up the following APIs to achieve its goal.

-Google Maps

-Google GeoLocation

-DataSF

###Package Description###
<ol>
<li>base: place the base domain class </li>
<li>cache: The structure class of Data SF</li>
<li>controller: The web response entry</li>
<li>domain: place all persistent domain</li>
<li>schedule: define the timed task</li>
<li>service: create some service for controller invoke</li>
<li>utils: place all the util tool</li>
</ol>

###The Technology Stack###
The following technologies are used to implement Food Trucks V1.0

<ol>
<li>Bootstrap 3.0 | JS | JQuery | CSS </li>
<li>Spring | Java | Maven</li>
<li>3rd Party APIs</li>
</ol>

###How to start developing###
Clone the project:

```
  git clone https://github.com/ayulover007/food-trucks.git
```

###How to deploy###
1)You could do the following from command line

  a) mvn package 
  
  b) nohup java -jar target/food-trucks.jar &
  
2) From Eclipse 

  a) Import the project into eclipse. 
  
  b) Click on "Run" -> Run as spring boot app.

Once the web app is running, the below is an example of the rest api's available from the web app.

```
  http://localhost:8081/getFoodTrucks?lantitude=37.7267279782571&longitude=-122.388658428832
```
It will give you the result of food truck collections. The following table is the response data structure:

|param    |description    | example  |
| :------------- |:-------------| -----|
| errors        | the error info if response failed | failed get lantitude |
| result      | the json format colletions if response success      |  {"address":"2115 JENNINGS ST","applicant":"Paul's Catering"...|

  
###How to view###

When the Spring Boot Application started, you can access the site like the below
```
  http://localhost:8081/index.html
```
 ![image](http://www.ttail.cn/page.jpg)

###Unit Tests###
Under src/main/test a set of unit tests are included. Whenever a feature is implemented and/or modified please include all tests that validate those changes. Make sure all tests are passing.

