package com.iot.device.codec;

import com.iot.device.msi.Content;
import com.iot.device.msi.Type;
import com.iot.device.msi.Water;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Auther: lxr
 * @Date: 2018/10/30 13:40
 * @Description:
 */
public class DeviceDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes()<8){
            return;
        }
        int i = byteBuf.readerIndex();
        byte[] bytes=new byte[2];
        byteBuf.readBytes(bytes);
        short open = parseShort(bytes);
        Content content =new Content();
        if("bj".equals(new String(bytes))){
            content.setType(Type.BJ);
            byte[] length=new byte[2];
            byteBuf.readBytes(length);
            Short len =Short.parseShort(new String(length));
            if(byteBuf.readableBytes()<len){
                byteBuf.readerIndex(i);
                return;
            }
            byte[] body=new byte[len];
            byteBuf.readBytes(body);
            String bodyStr=new String(body);
            String[] params =bodyStr.split(",");
            Water water = Water.toWater(params);
            content.setWater(water);
            // parse
        }
        else if((short)0x0103 == open ){
            content.setType(Type._0103);
            setMSXE(byteBuf,content);
        }
        else if( (short)0x0106 == open){
            content.setType(Type._0106);
            setMSXE(byteBuf,content);
        }
        list.add(content);
    }

    private void setMSXE(ByteBuf byteBuf,Content content ){
        byte[] length=new byte[2];
        byteBuf.readBytes(length);
        content.setRegister(parseShort(length));
        length=new byte[2];
        byteBuf.readBytes(length);
        content.setValue(parseShort(length));
        length=new byte[2];
        byteBuf.readBytes(length);
        content.setCrc(parseShort(length));
    }


    private short parseShort( byte[] length){
        Assert.isTrue(length.length==2,()->"byte length should be 2");
        return  (short)(length[1]<<8 & 0xff00 | length[0] &0xff);
    }

}
