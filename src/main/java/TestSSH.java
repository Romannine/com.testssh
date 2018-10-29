import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;
import test1.testDomian;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class TestSSH {
    testDomian testDomian;
    public static void main(String[] args) throws IOException, JSchException {
        // TODO Auto-generated method stub
        //String command = "hive -e \"select doctor_name from ue_doctor_sh1 limit 50 \" >> /home/result.txt";
        String command ="/opt/bigdata/hive-2.3.3/bin/hive -e \"set hive.cli.print.header=true;select * from demo001\"";
        testDomian res = exeCommand(command);
        String str1 = res.getOut();
        System.out.print(str1);
//        for (String str : str1) {
//            System.out.println(str);
//        }
        String str2 = res.getErr();
        System.out.print(str2);
    }

    private static testDomian exeCommand(String command) throws IOException, JSchException
    {
        JSch jsch = new JSch();
        Session session = jsch.getSession("root", "101.200.47.72" , 22);
        session.setConfig("StrictHostKeyChecking", "no");
        //   java.util.Properties config = new java.util.Properties();
        //   config.put("StrictHostKeyChecking", "no");

        session.setPassword("Ch791226");
        session.connect();

        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
        //System.setOut(System.err);
        channelExec.connect();
        InputStream errStream = channelExec.getErrStream();
        String ss = IOUtils.toString(errStream,"UTF-8");
        String out = IOUtils.toString(in, "UTF-8");

        channelExec.disconnect();
        session.disconnect();

        testDomian testDomian1 = new testDomian();
        testDomian1.setErr(ss);
        testDomian1.setOut(out);

        return testDomian1;
    }

}