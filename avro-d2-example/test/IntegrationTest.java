/**
 * Copyright 2015 Thomas Feng
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import java.net.URL;

import org.apache.avro.ipc.HttpTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import controllers.protocols.Example;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;

/**
 * @author Thomas Feng (huining.feng@gmail.com)
 */
public class IntegrationTest {

  private static final int TIMEOUT = Integer.MAX_VALUE;

  @Test
  public void testD2Request() {
    running(testServer(9000), () -> {
      try {
        WSResponse response = WS.url("http://localhost:9000/proxy")
            .setQueryParameter("message", "Test Message through Client").get().get(TIMEOUT);
        assertThat(response.getBody(), is("Test Message through Client"));
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Test
  public void testDirectRequest() {
    running(testServer(9000), () -> {
      try {
        HttpTransceiver transceiver =
            new HttpTransceiver(new URL("http://localhost:9000/example"));
        Example example = SpecificRequestor.getClient(Example.class, transceiver);
        assertThat(example.echo("Test Message"), is("Test Message"));
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }
}
