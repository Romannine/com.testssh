import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class TestSSH {
    public static void main(String[] args) throws IOException, JSchException {
        // TODO Auto-generated method stub
        String command = "hive -e \"select doctor_name from ue_doctor_sh1 limit 50 \" >> /home/result.txt";
        String res = exeCommand(command);

        System.out.println(res);
    }


    private static String exeCommand(String command) throws IOException, JSchException
    {
        JSch jsch = new JSch();
        Session session = jsch.getSession("root", "39.107.238.150", 22);
        session.setConfig("StrictHostKeyChecking", "no");
        //   java.util.Properties config = new java.util.Properties();
        //   config.put("StrictHostKeyChecking", "no");

        session.setPassword("Ch(791226");
        session.connect();

        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
        channelExec.connect();
        String out = IOUtils.toString(in, "UTF-8");

        channelExec.disconnect();
        session.disconnect();

        return out;
    }
}