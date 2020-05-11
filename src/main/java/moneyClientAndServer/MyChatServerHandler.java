package moneyClientAndServer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @program: netty-test
 * @description:
 * @author: LiuSenChuan
 * @create: 2020-05-07 10:45
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    //ChannelGroup是保存channel对象的
    private static ChannelGroup channelGroup = new
            DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //服务器端收到任何一个客户端发来的消息之后,channelRead0就会调用
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            //判断当前channel对象是否是发消息的客户端
            if (ch != channel)
                ch.writeAndFlush("[" + channel.remoteAddress() + "]" + msg + "\n");
            else
                ch.writeAndFlush("自己:" + msg + "\n");
        });
    }

    //建立连接时回调
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        //将会通知到channelGroup里面的所有channel
        channelGroup.writeAndFlush("[服务器消息]----" + channel.remoteAddress() + "加入\n");
        //将channel放入channelgroup中
        channelGroup.add(channel);
    }

    //连接断开时回调
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器消息]----" + channel.remoteAddress() + "离开\n");
        //channelGroup.remove(channel);可不写,连接断开时netty会自动处理
    }

    //连接处于活动状态
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线了");
    }

    //连接处于非活动状态
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "下线了");
    }

    //异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();;
        ctx.close();
    }
}
