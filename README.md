<h3>Microservice Assignment</h3>

In the is project I try to create microservice application using spring boot with event driven architecture
for that I choose 2 use-cases for my previous assignment such as create course , send email notification after creating course by acknowledging to the user

the code repository I have created two independent microservices (course & notification microservice) and communicate those microservices with Apache Kafka
for storing data in database I have chosen MongoDB local database use can see the URL in course service application properties (mongodb://localhost:27017/DC_COURSE)

for event driven communication I have installed kafka & zookeeper locally , to run kafka 

In your terminal run this command -  .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
to run kafka run this command - .\bin\windows\kafka-server-start.bat .\config\server.properties

To create new course in post man client use this API end point (POST) - http://localhost:8081/api/v1/course

in the body pass this data 

{
    "number":"32131",
    "trainingPartnerLastname":"Safras",
    "trainingPartnerName":"Cell-Ed",
    "name":"Microservice and Cloud Computing",
    "trainingPartnerFirstName":"Mohamed",
    "trainingPartnerEmail":"your email",
    "approvalStatus":"ADD"
}

after successfully create course the notification microservice consume kafka topic and dispatch email to respective given email address (which you provide in the body)

to view all created course use this API end point (GET) - http://localhost:8081/api/v1/course

