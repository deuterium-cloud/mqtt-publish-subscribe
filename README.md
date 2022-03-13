# Spring Integration MQTT with IoT

Spring boot demo project for testing integration with Internet of Things.

HiveMQ was used as a message broker, and there are two approach:
1. HiveMQ can be spin up locally, as docker container, or
2. Use HiveMQ cloud service, provided at https://www.hivemq.com
---
## HiveMQ locally

There are two docker container versions: hivemq/hivemq4 (enterprise) and hivemq/hivemq-ce (community edition).

Enterprise edition:
```shell
docker run -p 9090:8080 -p 1884:1883 --name hivemq-test hivemq/hivemq4
```
Admin panel is available at http://localhost:9090 with default credentials:
```
username: admin
password: hivemq
```

For testing purpose and home usage, community edition should be perfect choice:

```shell
docker run -p 8884:8000 -p 1885:1883 --name hivemq-ce-test hivemq/hivemq-ce:2021.3
```
Websocket listener is already added, and can be connected at port 8884. If that is not a case, add it directly in the 
file:
```
/opt/hivemq-ce:2021.3/conf/config.xml
```

### Add security

Extensions can be found at: https://www.hivemq.com/extensions/

1. Remove hivemq-allow-all-extension from directory /opt/hivemq-ce-2021.3/extensions
```shell
cd /opt/hivemq-ce-2021.3/extensions
rm -r hivemq-allow-all-extension/
```
2. Install commands:
```shell
apt update
apt install wget
apt install unzip
apt install nano
```
3. Download and unzip hivemq-file-rbac-extension
```shell
 wget https://www.hivemq.com/releases/extensions/hivemq-file-rbac-extension-4.4.0.zip
 unzip hivemq-file-rbac-extension-4.4.0.zip
 rm hivemq-file-rbac-extension-4.4.0.zip
```
4. Users with credentials should be found in:
```
/opt/hivemq-ce-2021.3/extensions/hivemq-file-rbac-extension/credentials.xml
```
5. Create hashed password for new user=deuterium, using given tool:
```shell
java -jar hivemq-file-rbac-extension-4.4.0.jar -p ForTheEmperor!
```
6. Add credentials to file credentials.xml and pay special attention on ROLES
```shell
nano credentials.xml
```
7. Restart container

Connect to, either, tcp://localhost:1885 or ws://localhost:8884

---
## HiveMQ cloud

HiveMQ cloud service, can be found at https://www.hivemq.com. 
The free plan is available, and should be suitable for testing purpose.

---

Video: https://www.youtube.com/watch?v=p04NqXDlRE4

Security: https://www.hivemq.com/mqtt-security-fundamentals/
