package HeartBeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @program: netty-test
 * @description:
 * @author: LiuSenChuan
 * @create: 2020-05-07 16:43
 */
public class MyChatClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //根据一定的分隔符,来实现一个解码器
        pipeline.addLast("delimiterBasedFrameDecoder",new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        pipeline.addLast("stringDecoder",new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("stringEncoder",new StringEncoder(CharsetUtil.UTF_8));

        pipeline.addLast("myChatClientHander",new MyChatClientHander());
    }
}
