package org.repro;

import java.net.URI;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.jackson.JsonCloudEventData;

@Path("/hello")
public class GreetingResource {

  private final ReproRestClient restClient;
  private final ObjectMapper objectMapper;

    @Inject
    public GreetingResource(@RestClient ReproRestClient restClient, ObjectMapper objectMapper) {
      this.restClient = restClient;
      this.objectMapper = objectMapper;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws Exception {

        var data = objectMapper.readValue("""
               { "a":111,"b":222 }
                """, JsonNode.class);

      var cloudEvent =
              CloudEventBuilder.v1()
                      .withId(UUID.randomUUID().toString())
                      .withType("ce-type")
                      .withSource(URI.create("ce-source"))
                      .withDataContentType(MediaType.APPLICATION_JSON)
                      .withData(JsonCloudEventData.wrap(data))
                      .build();

      restClient.publishCloudEvent("something", cloudEvent);

      return "Hello from RESTEasy Reactive";

    }
}
