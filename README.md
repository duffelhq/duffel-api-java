# Duffel API Java client library

A Java client library for the [Duffel API](https://duffel.com/docs/api).

## Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)

## Requirements

* Java 11+
* A Duffel API access token (get started [here](https://duffel.com/docs/guides/quick-start) ✨)

## Installation

In most cases, you'll want to add `Duffel SDK` to your project as a dependency by adding it to your `build.gradle` or `settings.xml` file.

#### Gradle
```groovy
dependencies {
    implementation 'com.duffel.api:1.0.0'
}
```

#### Maven
```xml
<dependency>
  <groupId>com.duffel</groupId>
  <artifactId>api</artifactId>
  <version>1.0.0</version>
</dependency>
```


## Usage

You can see a complete end-to-end example of searching and booking using the client library in [`example/search_and_book.java`](https://github.com/duffelhq/duffel-api-java/blob/main/examples/search_and_book.java).

### Initialising the client

The library's functionality is accessed from a `com.duffel.DuffelApiClient` instance.

To initialise a `DuffelApiClient`, all you'll need is your API access token:

```java
import com.duffel.DuffelApiClient;

DuffelApiClient client = new DuffelApiClient("api_token");
```

### Resources in the Duffel API

In this readme, we'll use the term "resources" to refer to the different Duffel concepts that you can *act on* in the API - for example airports, offers, orders and payment intents.

We'll refer to instances of each of these resources - an airport, an offer, a payment intent - as "records".

In the [Duffel API reference](https://duffel.com/docs/api/), the resources are listed in the sidebar. For each resource, you'll find:

* a schema, which describes the data attributes we expose for each record from this resource
* a list of actions you can perform related to that resource (e.g. for [Orders](https://duffel.com/docs/api/orders), you can "Get a single order", "Update a single order", "List orders" and "Create an order")

The Java client library is structured around these resources. Each resource has its own "service" which you use to perform actions. These services are accessible from your `DuffelApiClient` instance:

e.g.
```java
client.aircraftService
        ...
client.orderService
client.seatMapsService
```

__To see what actions are available for each resource, check out the definitions for the service classes [here](https://github.com/duffelhq/duffel-api-java/tree/main/src/main/java/com/duffel/service).__

### Creating a record

Most resources allow you to create a record. In fact, the most important flows in the Duffel API start with creating a record. You'll do this with the `#post` method exposed on a service.

For example, you'll search for flights by creating an offer request:

```java
OfferRequest.Slice slice = new OfferRequest.Slice();
slice.setDepartureDate("2022-06-15");
slice.setOrigin("LHR");
slice.setDestination("STR");

Passenger passenger = new Passenger();
passenger.setType(PassengerType.adult);
passenger.setGivenName("Test");
passenger.setFamilyName("User");

OfferRequest request = new OfferRequest();
request.setMaxConnections(0);
request.setCabinClass(CabinClass.economy.name());
request.setSlices(List.of(slice));
request.setPassengers(List.of(passenger));

OfferResponse offerResponse = client.offerRequestService.post(request);

LOG.info(offerResponse.getId());
```

The `#post` method returns the created record.

### Listing records

Many resources in the Duffel API (e.g. airports, orders and offers) allow you to list their records.

For example, you can get a list of all the airports that Duffel knows about - that is, a list of airport records.

For performance reasons, we [paginate](https://duffel.com/docs/api/overview/pagination) records in the API when listing. You can only see up to 200 at a time. You'll need to page through, like moving through pages of a book.

Clients can page through results by usage of the `before` and `after` parameters:

```java
# Initial page
AircraftCollection page = client.aircraftService.getPage();
# Next page
page = client.aircraftService.getPage(null, page.getAfter(), null);
# Previous page
page = client.aircraftService.getPage(page.getBefore(), null, null);
```

#### An exception: seat maps

Watch out! There is one kind of list in the Duffel API which isn't paginated: seat maps.

When you call `client.seatMapsService.getById("off_123")`, all of the seat maps will be returned at once in a single `List<SeatMap>`.

### Fetching single records

Many resources in the Duffel API allow you fetch a single record (e.g. *an* airport, *a* payment intent) if you know its ID.

You do that using the `#getById` method on a resource like this:

```java
Airline airline = client.airlineService.getById("arl_00009VME7DBKeMags5CliQ");
LOG.info(airline.getIataCode());
```

The `#getById` method returns the record.

### Updating a record

Some records in the Duffel API allow you to update them after they've been created, if you know their ID.

That works like this using the `#update` method on a resource:

```java
OrderUpdate update = new OrderUpdate();
update.setMetadata(Map.of("myKey", "myValue"));

Order order = client.orderService.update("ord_0000AKY0JiKODHllshwjaq", update);
```

The `#update` method returns the updated record.


# TODO finishing here