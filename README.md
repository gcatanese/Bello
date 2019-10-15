# Bello
Advanced Delivery Controller (ADC) for Chatbots #adc #loadbalancer #chatbot #webhook #crossplatform

## Problem: where is my cookie?

In the Chatbots world user traffic goes via the selected platform (DialogFlow, Chatfuel, Facebook, Telegram, MS Teams, etc..) therefore the traditional load balancer model is no longer effective: users cannot be tracked with cookies or IP addresses, devices no longer provide the information you might need.

![Alt text](wiki/BT.png?raw=true "Title")

## A Solution

Bello is a lightweight ADC which distributes incoming Webhook traffic to the available hosts supporting ***RoundRobin*** with ***Session Persistence*** (sticky sessions) while parsing the payload to obtain relevant user/session information.

## Use Cases
* Load Balancing: distribute Chatbot webhook traffic ensuring ***Session Persistence***
* Unload node: distribute Chatboot webhook traffic to other hosts so an host/service can redeploy or go offline 
* Blue-Green deployment: switch some traffic to a newly deployed host for assessing production readiness


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

Ping it 
```
curl http://localhost:8888/belloadc/test
```

## Config and usage

* Customize config/hosts.json to define pool of hosts

 Direct Webhooks request to https://{server}:8888, or better configure the ADC behind a production-ready HTTP Server (NGINX, Apache HTTP Server).


## Features... to date
* ***Simple***: single Docker image without external dependencies
* Random, RoundRobin and Weighted RoundRobin balacing algorithms with ***Session Persistence***
* Host healthcheck
* Support for: DialogFlow, Chatfuel, Facebook, Microsoft Bot Framework, Telegram
* In-memory cache and Redis support

## In the pipeline

* "Least Load" distribution 
* "Least Requests" distribution
* Prometheus events
* Multi-path support
* REST API


&nbsp;&nbsp;&nbsp;
<hr>
<p align="center">
Powered by NU IT http://www.nu-it.eu
</p>

