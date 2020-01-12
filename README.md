# MS_SensorDataStorage 

The microservice SensorDataStorage is a part of SensorCarDataManager (SCDM) and represents the backend microservice which saves SensorData persistently.

---

## APIs

### REST
The REST API definition is generated via Swagger Tool and can be opened at Url 
http://localhost:8080/

### WebSocket

WebSocket doesn't have a Swagger description so the Endpoints described here:

BaseUrl for WebSocket: http://localhost:8080/websocket


| Request Channel Name | Response Channel Name | DataType | Description
| --- | --- | --- | --- |
|  `/app/registerVehicle` |  `/response/registerVehicle` | StaticVehicleData | Registers a new Vehicle in SensorDataStorage *No Response currently* |
| `/app/addDynamicVehicleData/{vin}` |`/response/addDynamicVehicleData` | DynamicVehicleData | Adds new DynamicVehicleData for a previous registered vin No Response currently* |
