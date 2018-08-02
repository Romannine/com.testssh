import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Outtest {
    public static void main(String[] args) throws IOException, JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession("root", "39.105.47.8" , 22);
        session.setConfig("StrictHostKeyChecking", "no");
        //   java.util.Properties config = new java.util.Properties();
        //   config.put("StrictHostKeyChecking", "no");
        String command ="cd /root &&./selectIp.sh"+" 172.17.104.30-55";
        session.setPassword("Ch(791226");
        session.connect();

        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
        channelExec.connect();
        byte[] buf = new byte[1024];
        int len = 0;//定义一个表示用于接收文本的字节数
        while((len=in.read(buf))!=-1)
        {
            //使用这个new String(byte[],off,len)->指定输出一定数量的byte转成String
            System.out.println(new String(buf,0,len));
        }

        channelExec.disconnect();
        session.disconnect();

        System.out.print(in);
    }

}
