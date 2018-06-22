import java.io.IOException;
import java.net.InetAddress;

public class PingTest extends Thread{

    private String ip;

    public PingTest(){}

    public PingTest(String ip) {
        this.ip = ip;
    }

    public static void main(String[] args){
        for (int x=125;x<=140;x++){
            PingTest pingTest = new PingTest("192.168.245."+x);
            pingTest.run();
        }
    }

    public void run() {
        try {
            boolean status = InetAddress.getByName(ip).isReachable(100);
            if (status) System.out.println(ip);
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}
