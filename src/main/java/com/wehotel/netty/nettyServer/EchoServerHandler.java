/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.wehotel.netty.nettyServer;

import com.wehotel.netty.encoder.Header;
import com.wehotel.netty.encoder.Message;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * Handler implementation for the echo server.
 * 
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	
	//接收请求后的处理类
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	
    	Message msg1=(Message)msg;
    	System.out.println(msg1.getData());
    	
    	//此处写接收到客户端请求后的业务逻辑
    	String content="hello world,this is nettyServer";  
        Header header=new Header((byte)0, (byte)1, (byte)1, (byte)1, (byte)0, "713f17ca614361fb257dc6741332caf2",content.getBytes("UTF-8").length, 1);
        Message message=new Message(header,content); 
        ctx.writeAndFlush(message);
        
        
    	//ctx.writeAndFlush(Unpooled.copiedBuffer("hello world,this is nettyServer",CharsetUtil.UTF_8));
    
    }

    //读取完成后处理方法
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("EchoServerHandler.channelReadComplete");
    	//ctx.flush();
    }

    //异常捕获处理方法
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
