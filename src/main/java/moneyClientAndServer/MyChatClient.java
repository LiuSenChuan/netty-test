package moneyClientAndServer;

/**
 * @program: netty-test
 * @description:
 * @author: LiuSenChuan
 * @create: 2020-05-07 11:18
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 客户端
 */
public class MyChatClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost",8899).sync().channel();

            //不断地读取用户在控制台输出的信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true)
                channel.writeAndFlush(reader.readLine() + "\r\n");


        }finally {

            eventLoopGroup.shutdownGracefully();
        }
    }
}
