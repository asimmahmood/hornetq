/*
 * Copyright 2005-2014 Red Hat, Inc.
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.hornetq.tests.integration.stomp;

import java.util.HashMap;
import java.util.Map;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.config.Configuration;
import org.hornetq.core.config.CoreQueueConfiguration;
import org.hornetq.core.protocol.stomp.StompProtocolManagerFactory;
import org.hornetq.core.remoting.impl.invm.InVMAcceptorFactory;
import org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory;
import org.hornetq.core.remoting.impl.netty.TransportConstants;
import org.hornetq.core.server.HornetQServer;
import org.hornetq.core.server.HornetQServers;
import org.hornetq.jms.server.JMSServerManager;
import org.hornetq.jms.server.config.JMSConfiguration;
import org.hornetq.jms.server.config.impl.JMSConfigurationImpl;
import org.hornetq.jms.server.impl.JMSServerManagerImpl;
import org.hornetq.tests.util.UnitTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StompWebSocketTest extends UnitTestCase
{
   private JMSServerManager server;

   /**
    * to test the Stomp over Web Sockets protocol,
    * uncomment the sleep call and run the stomp-websockets Javascript test suite
    * from http://github.com/jmesnil/stomp-websocket
    */
   @Test
   public void testConnect() throws Exception
   {
      //Thread.sleep(10000000);
   }

   // Implementation methods
   //-------------------------------------------------------------------------
   @Override
   @Before
   public void setUp() throws Exception
   {
      server = createServer();
      server.start();
   }

   /**
    * @return
    * @throws Exception
    */
   private JMSServerManager createServer() throws Exception
   {
      Configuration config = createBasicConfig();
      config.setSecurityEnabled(false);
      config.setPersistenceEnabled(false);

      Map<String, Object> params = new HashMap<String, Object>();
      params.put(TransportConstants.PROTOCOL_PROP_NAME, StompProtocolManagerFactory.STOMP_PROTOCOL_NAME);
      params.put(TransportConstants.PORT_PROP_NAME, TransportConstants.DEFAULT_STOMP_PORT + 1);
      TransportConfiguration stompTransport = new TransportConfiguration(NettyAcceptorFactory.class.getName(), params);
      config.getAcceptorConfigurations().add(stompTransport);
      config.getAcceptorConfigurations().add(new TransportConfiguration(InVMAcceptorFactory.class.getName()));
      config.getQueueConfigurations().add(new CoreQueueConfiguration(getQueueName(), getQueueName(), null, false));
      HornetQServer hornetQServer = addServer(HornetQServers.newHornetQServer(config));

      JMSConfiguration jmsConfig = new JMSConfigurationImpl();
      server = new JMSServerManagerImpl(hornetQServer, jmsConfig);
      server.setContext(null);
      return server;
   }

   @Override
   @After
   public void tearDown() throws Exception
   {
      server.stop();
   }

   protected String getQueueName()
   {
      return "/queue/test";
   }
}
