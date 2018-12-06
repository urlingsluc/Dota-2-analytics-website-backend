FROM tomcat:9.0.13-jre8

COPY ./target/*.jar ./app.jar

CMD java -jar app.jar