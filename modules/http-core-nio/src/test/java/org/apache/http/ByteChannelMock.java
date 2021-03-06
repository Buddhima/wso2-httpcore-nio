/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public class ByteChannelMock implements ByteChannel {

    private final ReadableByteChannelMock readableChannel;
    private final WritableByteChannelMock writableChannel;
    private boolean closed;

    public ByteChannelMock(
        final ReadableByteChannelMock readableChannel,
        final WritableByteChannelMock writableChannel) {
        super();
        this.readableChannel = readableChannel;
        this.writableChannel = writableChannel;
    }

    public int read(final ByteBuffer dst) throws IOException {
        return this.readableChannel.read(dst);
    }

    public int write(final ByteBuffer src) throws IOException {
        return this.writableChannel.write(src);
    }

    public boolean isOpen() {
        return !this.closed;
    }

    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.readableChannel.close();
        this.writableChannel.close();
    }

}
