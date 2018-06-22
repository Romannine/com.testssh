import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

public class HadoopTest {
    static Configuration conf = new Configuration(true);
    static {
        conf.set("fs.default.name","hdfs://Roman91.hostloacl:8020");
    }

    public static void getDirectoryFromHdfs(String direPath) throws Exception {
        FileSystem fs = FileSystem.get(URI.create(direPath), conf);
        FileStatus[] filelist = fs.listStatus(new Path(direPath));
        for (int i = 0; i < filelist.length; i++) {
            System.out.println("_________________第" + i + "个文件"
                    + "____________________");
            FileStatus fileStatus = filelist[i];
            System.out.println("Name:" + fileStatus.getPath().getName());
            System.out.println("size:" + fileStatus.getLen());
            System.out.println("_________________第" + i + "个文件"
                    + "____________________");
        }
        fs.close();
    }

    public static void main(String[] args){
        try {
            getDirectoryFromHdfs("/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
