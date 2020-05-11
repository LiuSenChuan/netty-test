package WebSocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * @program: netty-test
 * @description:
 * @author: LiuSenChuan
 * @create: 2020-05-09 10:12
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        System.out.println("收到的消息:" + msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("成功接收到客户端发送的消息" + msg.text()));
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器端返回" + LocalDateTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //每个channel都有一个全局唯一的id值asLongText
        //asShortText不是唯一的
        System.out.println("handlerAdded" + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        System.out.println("handlerRemoved" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("发生异常");
        ctx.close();
    }
}
