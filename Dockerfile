FROM adoptopenjdk/openjdk11:latest

RUN mkdir -p /software/belloadc

ADD target/belloadc.jar /software/belloadc/belloadc.jar

ENV config=config/
ENV port=8888
ENV healthCheckInterval=20
ENV strategy=roundrobin
ENV cacheImpl=inMemory
ENV redisHost=localhost
ENV redisPort=6379
ENV metricsHandlerPort=8889

CMD java -jar /software/belloadc/belloadc.jar

