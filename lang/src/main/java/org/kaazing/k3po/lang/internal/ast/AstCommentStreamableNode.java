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
package org.kaazing.k3po.lang.internal.ast;

import static org.kaazing.k3po.lang.internal.ast.util.AstUtil.equivalent;

import javax.el.ELContext;

import org.kaazing.k3po.lang.internal.ast.value.AstLocation;

public class AstCommentStreamableNode extends AstStreamableNode {

    private String commentText;

    private AstLocation location;
    private ELContext environment;

    public AstCommentStreamableNode() {
    }

    public AstCommentStreamableNode(AstCommentStreamableNode acceptNode) {
        this.regionInfo = acceptNode.regionInfo;
        this.location = acceptNode.location;
        this.environment = acceptNode.environment;
        this.commentText = acceptNode.commentText;
    }

    public AstLocation getLocation() {
        return location;
    }

    public void setLocation(AstLocation location) {
        this.location = location;
    }

    public ELContext getEnvironment() {
        return environment;
    }

    public void setEnvironment(ELContext expressionContext) {
        this.environment = expressionContext;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    protected int hashTo() {
        int hashCode = getClass().hashCode();

        if (location != null) {
            hashCode <<= 4;
            hashCode ^= location.hashCode();
        }

        if (environment != null) {
            hashCode <<= 4;
            hashCode ^= environment.hashCode();
        }

        if (commentText != null) {
            hashCode <<= 4;
            hashCode ^= commentText.hashCode();
        }

        return hashCode;
    }

    @Override
    protected boolean equalTo(AstRegion that) {
        return that instanceof AstCommentStreamableNode && equalTo((AstCommentStreamableNode) that);
    }

    protected boolean equalTo(AstCommentStreamableNode that) {
        return equivalent(this.location, that.location)
                && equivalent(this.commentText, that.commentText);
 }

    @Override
    public <R, P> R accept(Visitor<R, P> visitor, P parameter) throws Exception {

//        return visitor.visit(this, parameter);
        return null;
    }

    @Override
    protected void describe(StringBuilder buf) {
        super.describe(buf);
        buf.append(commentText);
    }
}
