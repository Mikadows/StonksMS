# StonksMS

WIP : Trading microservices platform - School

## Services
### Stonks API

Our API run on port 8081.
It is containing for the moment just a health check endpoint.

Endpoints :

``GET /actuator/health``
```json
{
  "status":"UP",
  "components": { 
    "db": {
      "status":"UP",
      "details": {
        "database":"PostgreSQL",
        "validationQuery":"isValid()"}},
    "diskSpace": {
      "status":"UP",
      "details": { 
        "total":269490393088,
        "free":232273190912,
        "threshold":10485760,
        "exists":true}
    },
  "ping": {
    "status":"UP"
    }
  }
}
```

### Postgres
Accessible on port 5432 with username ``admin`` and password ``admin``.

### Prometheus
Accessible on port 9090. 

Url : http://localhost:9090/

### Grafana
Accessible on port 3000. Default logins are username ``admin`` and password ``admin``.

Url : http://localhost:3000/

### Elasticsearch
Accessible on port 9200. 
Ui on Kibana.

### Kibana
Accessible on port 5601. 

Url : http://localhost:5601/

### Zookeeper
Accessible on port 2181.

### Kafka & Kafka-ui
#### Kafka 
Accessible on port 9092 & 9093.
Depending on Zookeper startup.

#### Kafka-ui
Accessible on port 8080.

Url : http://localhost:8080/

### Logstash
This service use ports 5044, 9600 and 5000 over tcp & udp.
Depends on elasticsearch startup.