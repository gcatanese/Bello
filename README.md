# Bello
Advanced Delivery Controller (ADC) for Chatbots #adc #loadbalancer #chatbot #webhook #crossplatform

## Problem: where is my cookie?

In the Chatbots world user traffic goes via the selected platform (DialogFlow, Chatfuel, Facebook, Skype, MS Teams, ...) therefore the traditional load balancer model is no longer effective: users cannot be tracked with cookies or IP addresses, devices no longer provide the information you might need.

![Alt text](wiki/BT.png?raw=true "Title")

## A Solution

Bello is a lightweight ADC which distributes incoming Webhook traffic to the available hosts supporting ***RoundRobin*** with ***Session Persistence*** (sticky sessions), parsing the payload to fetch relevant user/session information.

## Use Cases
* Load Balancing: distribute Chatbot webhook traffic ensuring ***Session Persistence***
* Unload node: distribute Chatboot webhook traffic to other hosts so an host/service can redeploy or go offline 


## Deploy 

Run DockerImage providing the volume where to find the config folder.
```
docker login
docker pull perosa/belloadc
docker run -p 8080:8886 -v /config:/software/config perosa/Bello
```

Run from Java
```
java -jar target/belloadc.jar -Dport=8888 -Dconfig=perosa/config
```

Verify with curl http://localhost:8888/belloadc/test

## Config and usage

* Customize config/hosts.json to define pool of hosts

 Direct Webhooks request to https://{server}:8888, or better configure the ADC behind a production-ready HTTP Server (NGINX, Apache HTTP Server).


## Features... to date
* RoundRobin and Random balacing algorithms with Session Persistence
* Host healthcheck
* Support for: DialogFlow, Chatfuel, Facebook, MS Bot
* In-memory cache

## In the pipeline
* "Least Load" distribution 
* "Least Requests" distribution
* Prometheus events
* Alternative Cache solution to store Sessions and Hosts
* Multi-path support


