FROM openjdk:11-jdk

RUN mkdir -p /software/belloadc

ADD http/target/belloadc.jar /software/belloadc/belloadc.jar

CMD java -jar /software/belloadc/belloadc.jar

