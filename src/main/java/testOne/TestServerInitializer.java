package testOne;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import testOne.TestHttpServerHandler;

/**
 * @program: netty-test
 * @description:
 * @author: LiuSenChuan
 * @create: 2020-05-06 09:46
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    /**
     * 初始化管道,channel连接一旦被注册,这个回调方法就会被调用
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        //通过SocketChannel获取到ChannelPipeline
        /**
         * ChannelPipeline类似于一个管道,里面可以有多个channelHandler
         * channelHandler相当于拦截器一样,每个拦截器都有自己的功能
         */
        ChannelPipeline pipeline = ch.pipeline();

        //解码器
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        //定义自己的handler
        pipeline.addLast("testHttpServerHandler",new TestHttpServerHandler());

    }



}
