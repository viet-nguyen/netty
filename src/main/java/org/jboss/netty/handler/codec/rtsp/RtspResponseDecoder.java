/*
 * Copyright 2010 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.jboss.netty.handler.codec.rtsp;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpMessage;
import org.jboss.netty.handler.codec.http.HttpMessageDecoder;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

/**
 * Decodes {@link ChannelBuffer}s into {@link HttpResponse}s whose status is
 * {@link RtspResponseStatuses} and protocol version is {@link RtspVersions}.
 * <p>
 * Please refer to {@link HttpMessageDecoder} for the detailed information on
 * how this decoder works and what parameters are available.
 *
 * @author <a href="http://www.jboss.org/netty/">The Netty Project</a>
 * @author <a href="http://amitbhayani.blogspot.com/">Amit Bhayani</a>
 * @author <a href="http://gleamynode.net/">Trustin Lee</a>
 * @version $Rev$, $Date$
 */
public class RtspResponseDecoder extends HttpMessageDecoder {

    /**
     * Creates a new instance with the default
     * {@code maxInitialLineLength (4096}}, {@code maxHeaderSize (8192)}, and
     * {@code maxChunkSize (8192)}.
     */
    public RtspResponseDecoder() {
        super();
    }

    /**
     * Creates a new instance with the specified parameters.
     */
    public RtspResponseDecoder(int maxInitialLineLength, int maxHeaderSize,
            int maxChunkSize) {
        super(maxInitialLineLength, maxHeaderSize, maxChunkSize);
    }

    @Override
    protected HttpMessage createMessage(String[] initialLine) throws Exception {
        return new DefaultHttpResponse(
                RtspVersions.valueOf(initialLine[0]),
                new HttpResponseStatus(Integer.valueOf(initialLine[1]),initialLine[2]));
    }

    @Override
    protected boolean isDecodingRequest() {
        return false;
    }

}