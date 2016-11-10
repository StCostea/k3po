/**
 * Copyright 2007-2015, Kaazing Corporation. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kaazing.k3po.lang.internal.parser;

import static org.kaazing.k3po.lang.internal.parser.ScriptParseStrategy.SCRIPT;

import java.net.URI;

import org.junit.Test;
import org.kaazing.k3po.lang.internal.ast.AstScriptNode;
import org.kaazing.k3po.lang.internal.ast.builder.AstScriptNodeBuilder;
import org.kaazing.k3po.lang.internal.ast.value.AstLocation;
import org.kaazing.k3po.lang.internal.ast.value.AstLocationLiteral;

public class CommentsTest {

    @Test
    public void shouldParseConnectScriptWithComments() throws Exception {

        // @formatter:off
        String script =
                "# tcp.client.connect-then-close\n" +
                "property reqXorRelayedAddressIp 'not used' # not used\n" +
                "connect tcp://localhost:7788 " +
                "# Comment 1\n" +
                "\t\t # Comment 2\n" +
                "connected # c3\n" +
                "write [0x00 0x0b 0x00 0x54] # write comment\n" +
                "read [0x01 0x0b 0x00 0x40] # read comment\n" +
                "close # c4\n" +
                "closed # c5\n";
        // @formatter:on

        ScriptParserImpl parser = new ScriptParserImpl();
        AstScriptNode actual = parser.parseWithStrategy(script, SCRIPT);
        AstLocation location = new AstLocationLiteral(URI.create("tcp://localhost:7788"));

        AstScriptNode expected =
                new AstScriptNodeBuilder().addConnectStream().setLocation(location)
                        .addConnectedEvent().done().addCloseCommand().done().addClosedEvent().done().done().done();

        //assertEquals(expected, actual);
        System.out.println(actual.toString());
    }
}
