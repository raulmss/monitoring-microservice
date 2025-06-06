# Microservice Monitoring with Prometheus, Grafana, and AlertManager

This project demonstrates how to monitor a Java-based microservice architecture using Prometheus, Grafana, and AlertManager with Docker. It includes instance health alerts and high latency detection with notifications sent to Discord and Email.

## 📦 Project Structure

```
MONITORING-MICROSERVICE/
├── CloudGateway/
├── EurekaServer1/
├── EurekaServer2/
├── ProductService/
├── StockService/
├── StockService2/
├── alert.rules.yml
├── alertmanager.yml
├── docker-compose.yml
├── prometheus.yml
└── README.md
```

## 🛠 Prerequisites

- Java 17
- Maven
- Docker & Docker Compose

## 🚀 Running the Project

### 1. Run Java Microservices

Each service is a Spring Boot application. Navigate into each folder and run with:

```bash
mvn spring-boot:run
```

> Do this for all: CloudGateway, EurekaServer1, EurekaServer2, ProductService, StockService, StockService2

### 2. Configure Monitoring

- Ensure `docker-compose.yml`, `alert.rules.yml`, `alertmanager.yml`, and `prometheus.yml` are correctly set (some of them are already pre-configured for this demo).

```yaml
# docker-compose.yml
  - name: 'email-alerts'
    email_configs:
      - to: 'test96@gmail.com'
        from: 'testalerting@gmail.com'
        smarthost: 'smtp.gmail.com:587'
        auth_username: 'testalerting@gmail.com'
        auth_identity: 'testalerting@gmail.com'
        auth_password: 'new_secure_password'  # Replace with your actual app password
        send_resolved: true
```

```yaml
# prometheus.yml
  - job_name: 'product-service' ## Replace with actual service name
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['HostAddress:<PortNumber>'] # Replace with actual host and port

```


### 3. Start Monitoring Tools

```bash
docker compose up -d
```

This will spin up:

- **Prometheus** at [http://localhost:9090](http://localhost:9090)
- **Grafana** at [http://localhost:3001](http://localhost:3001) (login with `admin/admin`)
- **AlertManager** at [http://localhost:9093](http://localhost:9093)
- **Discord Relay** at port 8086

## 🔔 Alerts Included

| Alert Name           | Description                                          | Severity |
|----------------------|------------------------------------------------------|----------|
| `InstanceDown`       | Triggered if any service is down for 10s            | Warning  |
| `TooManyInstancesDown` | Triggered if more than 30% of services are down    | Critical |
| `HighLatencyDetected` | Triggered if 95th percentile latency > 50ms for 5s | Warning  |

## 📬 Notifications

- All alerts go to Discord.
- Alerts with severity `critical` and latency alerts also trigger emails.

## 📌 Notes

- You must [create a Discord webhook](https://support.discord.com/hc/en-us/articles/228383668-Intro-to-Webhooks) and place it in `docker-compose.yml`.
- To send emails via Gmail, enable 2FA and generate an **App Password**.

---
