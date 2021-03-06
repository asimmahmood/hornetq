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
package org.hornetq.core.server.management;

import org.hornetq.api.core.management.NotificationType;
import org.hornetq.utils.TypedProperties;

/**
 * A Notification
 * @see org.hornetq.core.server.management.NotificationListener
 * @see NotificationType
 * @author <a href="mailto:tim.fox@jboss.com">Tim Fox</a> Created 22 Jan 2009 16:41:12
 */
public final class Notification
{
   private final NotificationType type;

   private final TypedProperties properties;

   private final String uid;

   public Notification(final String uid, final NotificationType type, final TypedProperties properties)
   {
      this.uid = uid;
      this.type = type;
      this.properties = properties;
   }

   public NotificationType getType()
   {
      return type;
   }

   public TypedProperties getProperties()
   {
      return properties;
   }

   public String getUID()
   {
      return uid;
   }

   @Override
   public String toString()
   {
      return "Notification[uid=" + uid + ", type=" + type + ", properties=" + properties + "]";
   }
}
