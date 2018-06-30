package com.youzan.nsq.client.core.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * @author <a href="mailto:my_email@email.exmaple.com">zhaoxi (linzuxiong)</a>
 *
 * 
 */
public class Finish implements NSQCommand {
    private static final Logger logger = LoggerFactory.getLogger(Finish.class);

    private final byte[] data;

    public Finish(byte[] messageID) {
        if (messageID == null || messageID.length <= 0) {
            throw new IllegalArgumentException("Your input messageID is empty!");
        }
        final byte[] cmd = "FIN ".getBytes(UTF8);
        final ByteBuffer bb = ByteBuffer.allocate(cmd.length + messageID.length + 1);
        // FIN <message_id>\n
        bb.put(cmd).put(messageID).put(LINE_SEPARATOR);
        this.data = bb.array();
    }

    @Override
    public byte[] getBytes() {
        return data;
    }

    @Override
    public String getHeader() {
        return "";
    }

    @Override
    public List<byte[]> getBody() {
        return EMPTY_BODY;
    }
}
