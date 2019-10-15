FROM adoptopenjdk/openjdk11:latest

RUN mkdir -p /software/belloadc

ADD target/belloadc.jar /software/belloadc/belloadc.jar

CMD java -jar /software/belloadc/belloadc.jar

