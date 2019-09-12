# Bello
Advanced Delivery Controller (ADC) for Chatbots #adc #loadbalancer #webhook #crossplatform

## Problem

In the Chatbots world user traffic goes via the selected platform (DialogFlow, Chatfuel, Facebook, Skype, MS Teams, ...) therefore the traditional load balancer model is no longer effective: users cannot be tracked with cookies or IP addresses, devices no longer provide the information you might need.

Image

## A Solution

Bello is lightweight ADC which distributes incoming Webhook traffic to the available hosts supporting ***RoundRobin*** with ***Session Persistence*** (sticky sessions) parsing the payload to fetch relevant user/session informatiom.

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


