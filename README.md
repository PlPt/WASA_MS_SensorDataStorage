# MS_SensorDataStorage

The SensorDataStorage backend microservice is a part of SensorCarDataManager (SCDM) and stores SensorData in an in-memory database.

---

## APIs

### REST

The REST API specification is generated with the tool Swagger. The URL for Swagger UI is http://localhost:8080/.

### WebSocket

Websocket has no swagger specification, so the endpoints are described here:

Base URL for WebSocket: http://localhost:8080/websocket

| Request Channel Name               | Response Channel Name             | DataType           | Description                                                                       |
| ---------------------------------- | --------------------------------- | ------------------ | --------------------------------------------------------------------------------- |
| `/app/registerVehicle`             | `/response/registerVehicle`       | StaticVehicleData  | Registers a new Vehicle in SensorDataStorage _Currently no response_              |
| `/app/addDynamicVehicleData/{vin}` | `/response/addDynamicVehicleData` | DynamicVehicleData | Adds new DynamicVehicleData for a previous registered vin _Currently no response_ |
