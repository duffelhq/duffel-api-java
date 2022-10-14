# Duffel API Java client library

[![Build and test](https://github.com/duffelhq/duffel-api-java/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/duffelhq/duffel-api-java/actions/workflows/build.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.duffel/duffel-api.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.duffel/duffel-api)
![Duffel SDK Version](https://img.shields.io/github/v/release/duffelhq/duffel-api-java)
![Duffel API Version](https://img.shields.io/badge/Duffel%20API%20Version-v1-green)
[![GitHub License](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/duffelhq/duffel-api-java/main/LICENSE)


A Java client library for the [Duffel API](https://duffel.com/docs/api).

## Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)

## Requirements

* Java 11+
* A Duffel API access token (get started [here](https://duffel.com/docs/guides/quick-start) ✨)

## Installation

In most cases, you'll want to add `Duffel SDK` to your project as a dependency by adding it to your `build.gradle` or `pom.xml` file.

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

You can see a complete end-to-end example of searching and booking using the client library in [`example/com.duffel.example.SearchAndBookIT.java`](https://github.com/duffelhq/duffel-api-java/blob/main/examples/search_and_book.java).

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
request.setCabinClass(CabinClass.economy);
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

### Performing an action on a record


Some resources allow you to perform special actions on their records - for example confirming an order cancellation or pinging a webhook.


The methods you'll use to do this aren't named consistently, because each resource has different actions. For example, you'll call `#confirm` to confirm an order cancellation but `#ping` to ping a webhook.


It'll look a bit like this:


```java
client.orderCancellationService.confirm("ore_00009qzZWzjDipIkqpaUAj")
```


In general, these action methods return the record you've acted on.

#### An exception: pinging a webhook

Watch out! There is one action in the API which doesn't return the record you've acted on.


When you ping a webhook with `client.webhookService.ping("end_0000AMRu3m28KGtNP1o01Y")`, it returns void or will throw an exception.


### Handling errors


When the Duffel API returns an error, the library will raise an exception.


We have an exception class for each of the possible `type`s of error which the API can return, documented [here](https://duffel.com/docs/api/overview/errors) in the API reference. For example, if the API returns an error with `type` `invalid_state_error`, the library will throw a `DuffelException` exception, with `errors` containing a `StandardError` with type `invalid_state_error`.



You can catch these exceptions and inspect the various fields within the `errors` list and `meta` data: `#message`, `#title`, `#code`, `#request_id`, etc.

[//]: # ()
[//]: # (### Accessing the raw API response)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (Sometimes, you might want to get lower-level details about the response you received from the Duffel API - for example the raw body or headers.)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (If an error has been raised, you can call `#api_response` on the exception, which returns a `DuffelAPI::APIResponse`. If you're looking at a `ListResponse` or any resource, you can call `#api_response` on that.)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (From the `APIResponse`, you can call `#headers`, `#status_code`, `#raw_body`, `#parsed_body`, `#meta` or `#request_id` to get key information from the response.)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (### Verifying webhooks)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (You can set up [webhooks]&#40;https://duffel.com/docs/guides/receiving-webhooks&#41; with Duffel to receive notifications about events that happen in your Duffel account - for example, when an airline has a schedule change affecting one of your orders.)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (These webhook events are signed with a shared secret. This allows you to be sure that any webhook events are genuinely sent from Duffel when you receive them.)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (When you create a webhook, you'll set a secret. With that secret in mind, you can verify that a webhook is genuine like this:)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (```ruby)

[//]: # ()
[//]: # ()
[//]: # (# In Rails, you'd get this with `request.raw_post`.)

[//]: # ()
[//]: # ()
[//]: # (request_body = '{"created_at":"2022-01-08T18:44:56.129339Z","data":{"changes":{},"object":{}},"id":"eve_0000AFEsrBKZAcKgGtZCnQ","live_mode":false,"object":"order","type":"order.updated"}')

[//]: # ()
[//]: # ()
[//]: # (# In Rails, you'd get this with `request.headers['X-Duffel-Signature']`.)

[//]: # ()
[//]: # ()
[//]: # (request_signature = "t=1641667496,v1=691f25ffb1f206c0fda5bb7b1a9d60fafe42c5f42819d44a06a7cfe09486f102")

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (# Note that this code doesn't require your access token - `DuffelAPI::WebhookEvent`)

[//]: # ()
[//]: # ()
[//]: # (# doesn't expect you to have a `Client` initialised)

[//]: # ()
[//]: # ()
[//]: # (if DuffelAPI::WebhookEvent.genuine?&#40;)

[//]: # ()
[//]: # ()
[//]: # (  request_body: request_body,)

[//]: # ()
[//]: # ()
[//]: # (  request_signature: request_signature,)

[//]: # ()
[//]: # ()
[//]: # (  webhook_secret: "a_secret")

[//]: # ()
[//]: # ()
[//]: # (&#41;)

[//]: # ()
[//]: # ()
[//]: # (  puts "This is a real webhook from Duffel 🌟")

[//]: # ()
[//]: # ()
[//]: # (else)

[//]: # ()
[//]: # ()
[//]: # (  puts "This is a fake webhook! ☠️")

[//]: # ()
[//]: # ()
[//]: # (end)

[//]: # ()
[//]: # ()
[//]: # (```)

[//]: # ()
