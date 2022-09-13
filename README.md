# IoT_Manager-ANDROID-APP
Android application created to visualize esp8266 measurments on charts. Retrofit is used to fetch data from PHP server REST API. Project created in accordance with MVP design pattern. <br>
This is app part of my IoT Manager project https://github.com/HomoLupus/IoT_Manager-WEBAPP-AND-SERVER

![image](https://user-images.githubusercontent.com/83671766/189610323-67265cc6-a4c6-4a5e-93c3-8f73e59ff2c1.png)

<h2>LOGIN ACTIVITY </h2>
Open if user isn't logged in. Validation take place on a server, android app sends login and password to server and wait for response, If credentials were valid, Bearer token is retrived.
Token is required to fetch data from server.

![image](https://user-images.githubusercontent.com/83671766/189605828-dc567900-7468-4184-aa9b-189e0852d08b.png)

<h2>REGISTER ACTIVITY </h2>

![image](https://user-images.githubusercontent.com/83671766/189537188-6c7afd9d-51b6-4084-99d2-527bfea523c4.png)

<h2>HOME ACTIVITY </h2>

![image](https://user-images.githubusercontent.com/83671766/189537248-abd97a55-6ec7-4897-8242-24bc44bc6ea0.png)

<h2>GRAPH ACTIVITY </h2>

In a request 3 parameters are sent
<ul>
  <li><b>PERIOD:</b> HOUR/DAY/WEEK/MONTH</li>
  <li><b>ID:</b> SENSOR_ID</li>
  <li><b>TYPE:</b> TEMP/HUM/PRES/ALL</li>
</ul>
The bearer token is sent in the authorization header. In database, token is assigned to specific user, server check if this user is 
associated with sensor_id sent in parameter
<br><br>

![image](https://user-images.githubusercontent.com/83671766/189606734-acc3faf0-7083-410e-9a06-427d96e4bd97.png)
