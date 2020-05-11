package HeartBeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @program: netty-test
 * @description:
 * @author: LiuSenChuan
 * @create: 2020-05-07 16:22
 */
public class MyClient {
    public static void main(String[] args) throws Exception{

        EventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup).channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost",8899).sync().channel();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true)
                channel.writeAndFlush(reader.readLine() + "\r\n");
        }finally {
            loopGroup.shutdownGracefully();
        }
    }
}
