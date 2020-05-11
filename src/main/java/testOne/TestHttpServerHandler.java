package testOne;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @program: netty-test
 * @description:
 * @author: LiuSenChuan
 * @create: 2020-05-06 10:20
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

   /**
   *@Description: 读取客户端发来的请求,并向客户端响应
   *@Param: 
   *@return: 
   *@Author: LiuSenChuan
   *@date: 
   */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest){
            /**
             * ByteBuf接收客户端响应的内容
             */
            ByteBuf buf = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
            //DefaultFullHttpRequest，主要参数包括HttpVersion，HttpMethod，String，即Http版本，使用的Http方法，以及url
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0,HttpResponseStatus.OK,buf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,buf.readableBytes());

            /**
             * ctx.write这个方法是内容放置缓冲区
             * ctx.writeandflush才会将内容返回
             */
            ctx.writeAndFlush(response);
        }

        }
    //channel处于活跃状态
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active");
        super.channelActive(ctx);
    }

    //channel注册
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception{
        System.out.println("channel registerer");
        super.channelActive(ctx);
    }

    //channel被添加
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception{
        System.out.println("handle add");
        super.channelActive(ctx);
    }

    //channel处于不活跃状态
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("channel inactive");
        super.channelActive(ctx);
    }

    //channel解除注册
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception{
        System.out.println("channel unregisterer");
        super.channelActive(ctx);
    }
    }

