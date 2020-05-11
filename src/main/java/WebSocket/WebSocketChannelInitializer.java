package WebSocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @program: netty-test
 * @description:
 * @author: LiuSenChuan
 * @create: 2020-05-09 09:34
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        //配置链式解码器
        ChannelPipeline pipeline =ch.pipeline();

        //http编解码器处理类,只能处理像http的get请求,解码成HttpRequest
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        //http的编解码器,只能处理像http的post请求,8192聚合内容的最大长度,解码成FullHttpRequest
        pipeline.addLast("httpObjectAggregator",new HttpObjectAggregator(8192));
        //保证队列中的每一个元素是一次性发送
        pipeline.addLast("chunkedWriteHandler",new ChunkedWriteHandler());
        //处理http升级为websocket,添加websocket解码
        pipeline.addLast("webSocketServerProtocolHandler",new WebSocketServerProtocolHandler("/hll"));

        //自定义handler
        pipeline.addLast("textWebSocketFrameHandler",new TextWebSocketFrameHandler());
    }
}
