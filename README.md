# Empik
Empik task
> Written in Java 11


## How to build
Build solution:
> mvn clean install

Create image:
> docker build -t test .

## How to tun
> docker run -p 8080:8080 --it test 

## Interact
> curl -k -X GET http://localhost:8080/users/Reivals