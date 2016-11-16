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
package org.kaazing.k3po.lang.internal.ast.builder;

import org.kaazing.k3po.lang.internal.ast.AstCommentNode;
import org.kaazing.k3po.lang.internal.ast.AstScriptNode;
import org.kaazing.k3po.lang.internal.ast.builder.AstCommentStreamableNodeBuilder.StreamNested;

public final class AstCommentNodeBuilder extends AbstractAstNodeBuilder<AstCommentNode, AstCommentNode> {

    public AstCommentNodeBuilder() {
        this(new AstCommentNode());
    }

    public AstCommentNodeBuilder setCommentText(String commentText) {
        node.setCommentText(commentText);
        return this;
    }

    @Override
    public AstCommentNode done() {
        return result;
    }

    private AstCommentNodeBuilder(AstCommentNode node) {
        super(node, node);
    }

    public static final class ScriptNested<R extends AbstractAstNodeBuilder<? extends AstScriptNode, ?>> extends
    AbstractAstNodeBuilder<AstCommentNode, R> {

        public ScriptNested(R builder) {
            super(new AstCommentNode(), builder);
        }

        public ScriptNested<R> setCommentText(String commentText) {
            node.setCommentText(commentText);
            return this;
        }

        @Override
        public R done() {
            AstScriptNode scriptNode = result.node;
            scriptNode.getStreams().add(node);
            return result;
        }
    }
}
