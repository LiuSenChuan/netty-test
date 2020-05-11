package HeartBeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;

/**
 * @program: netty-test
 * @description:
 * @author: LiuSenChuan
 * @create: 2020-05-07 15:23
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        /**
         * 空闲状态监测的一个处理器：当一个channel在一定时间内没有进行读或者写或者读写操作时，就会
         * 触发IdleStateHandler
         * 第一个参数：readerIdleTime 读秒数
         * 第二个参数：writerIdleTime 写秒数
         * 第三个参数：allIdleTime 读写秒数
         */
        pipeline.addLast("idleStateHandler", new IdleStateHandler(5, 7, 3, TimeUnit.SECONDS));

        pipeline.addLast("myServerHandler",new MyServerHandler());
    }

}
