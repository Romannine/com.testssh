import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import java.net.URI;

public class hadoopdemo {

    public static void main(String[] args) throws Exception {
        String uri = "hdfs://master:8020";
        Configuration conf = new Configuration();
        FileSystem hdfs = FileSystem.get(URI.create(uri), conf);
        FileStatus[] fs = hdfs.listStatus(new Path("/"));
        Path[] paths = FileUtil.stat2Paths(fs);

        for (Path p : paths)
            System.out.println(p);
    }
}
