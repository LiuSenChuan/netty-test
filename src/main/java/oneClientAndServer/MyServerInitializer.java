package oneClientAndServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @program: netty-test
 * @description:
 * @author: LiuSenChuan
 * @create: 2020-05-06 14:41
 */


public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel ch){

        //获取管道
        ChannelPipeline pipeline = ch.pipeline();


        //netty处理的handler
        //LengthFieldBasedFrameDecoder：最大长度为65535，Length字段占用的字节数为2，解码时跳过2个字节（length长度）
        pipeline.addLast("LengthFieldBasedFrameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
        //LengthFieldPrepender长度字段优先考虑
        pipeline.addLast("LengthFieldPrepender",new LengthFieldPrepender(4));
        pipeline.addLast("StringDecoder",new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("StringEncoder",new StringEncoder(CharsetUtil.UTF_8));

        //自己的handler
        pipeline.addLast("MyServerHandler",new MyServerHandler());

    }
}
