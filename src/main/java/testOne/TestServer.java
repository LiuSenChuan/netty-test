package testOne;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: netty-test
 * @description:
 * @author: LiuSenChuan
 * @create: 2020-05-06 09:00
 */

public class TestServer {

    /**
     * 客户端发送一个请求,不带任何参数 ,服务器端返回一个helloworld
     */
    public static void main(String[] args) throws Exception{
        /**
         * 时间循环组
         * 首先定义两个EventLoogGroup
         * bossGroup相当于老板,worker相当于工人,bossGroup不断的从客户端接受请求,但不做任何处理,全交给worker进行处理
         * 不推荐使用bossGroup完成整个流程
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //ServerBootStrap是一个简化服务端启动类,关联一个处理器
            ServerBootstrap bootstrap = new ServerBootstrap();

            //定义一个我们自己的请求处理器TestServerInitializer
            bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            //bind端口
            ChannelFuture channelFuture = bootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅的关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

}
