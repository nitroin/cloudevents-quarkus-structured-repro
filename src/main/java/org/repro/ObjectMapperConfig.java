package org.repro;

import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.jackson.JsonFormat;
import io.quarkus.jackson.ObjectMapperCustomizer;

@Singleton
public class ObjectMapperConfig implements ObjectMapperCustomizer {
  @Override
  public void customize(ObjectMapper objectMapper) {
    objectMapper.registerModule(JsonFormat.getCloudEventJacksonModule());
  }
}
