package org.repro;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.cloudevents.jackson.JsonFormat;

@ApplicationScoped
@RegisterRestClient(configKey = "repro")
@RegisterClientHeaders
public interface ReproRestClient {

  @POST
  @Consumes(JsonFormat.CONTENT_TYPE)
  Response publishCloudEvent(@PathParam("channel") String channel, Object event);
}
