package core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import configuration.Configuration;
import restApi.JerseyRestConsumer;

public class BaseSetup {

  protected static Configuration conf;
  protected static ObjectMapper mapper;
  protected static ObjectMapper override_mapper;
  protected static ObjectMapper reverseMapper;
  protected static JerseyRestConsumer jerseyRestConsumer;


  static {
    conf = new Configuration();
    mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    jerseyRestConsumer = new JerseyRestConsumer();
  }

}
