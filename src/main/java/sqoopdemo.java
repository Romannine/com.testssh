import org.apache.sqoop.client.SqoopClient;
import org.apache.sqoop.model.*;
import org.apache.sqoop.submission.counter.Counter;
import org.apache.sqoop.submission.counter.CounterGroup;
import org.apache.sqoop.submission.counter.Counters;
import org.apache.sqoop.validation.Status;

public class sqoopdemo {
    public static void hdfsLink() throws Exception{
        String url = "http://localhost:12000/sqoop/";
        SqoopClient client = new SqoopClient(url);
        // ==============================================
        // create a placeholder for link
        long connectorId = 1;
        MLink link = client.createLink("hdfs-connector");
        // ==============================================================================
        link.setName("HDFS");
        link.setCreationUser("root");
        MLinkConfig linkConfig = link.getConnectorLinkConfig();
        linkConfig.getStringInput("linkConfig.uri").setValue("hdfs://127.0.0.1:9000");
        // ==============================================================================
        Status status = client.saveLink(link);
        if(status.canProceed()) {
            System.out.println("Created Link with Link Id : " + link.getPersistenceId());
        } else {
            System.out.println("Something went wrong creating the link");
        }
    }

    public static void mysqlLink() throws Exception{
        String url = "http://localhost:12000/sqoop/";
        SqoopClient client = new SqoopClient(url);
        // ==============================================
        // create a placeholder for link
        long connectorId = 1;
        MLink link = client.createLink("generic-jdbc-connector");
        // ==============================================================================
        link.setName("mysql_link");
        link.setCreationUser("root");
        MLinkConfig linkConfig = link.getConnectorLinkConfig();
        linkConfig.getStringInput("linkConfig.connectionString").setValue("jdbc:mysql://localhost/test");
        linkConfig.getStringInput("linkConfig.jdbcDriver").setValue("com.mysql.jdbc.Driver");
        linkConfig.getStringInput("linkConfig.username").setValue("root");
        linkConfig.getStringInput("linkConfig.password").setValue("123456");
        // ==============================================================================
        Status status = client.saveLink(link);
        if(status.canProceed()) {
            System.out.println("Created Link with Link Id : " + link.getPersistenceId());
        } else {
            System.out.println("Something went wrong creating the link");
        }
    }

    public static void saveJob() {
        String url = "http://localhost:12000/sqoop/";
        SqoopClient client = new SqoopClient(url);
        //Creating dummy job object
        MJob job = client.createJob("mysql","HDFS");
        job.setName("rootJobs");
        job.setCreationUser("root");
        // set the "FROM" link job config values
        MFromConfig fromJobConfig = job.getFromJobConfig();
        fromJobConfig.getStringInput("fromJobConfig.schemaName").setValue("test");
        fromJobConfig.getStringInput("fromJobConfig.tableName").setValue("Persons");
        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("Id_P");
        // set the "TO" link job config values
        MToConfig toJobConfig = job.getToJobConfig();
        toJobConfig.getStringInput("toJobConfig.outputDirectory").setValue("/sqoop");
        // set the driver config values
        MDriverConfig driverConfig = job.getDriverConfig();
        //driverConfig.getStringInput("throttlingConfig.numExtractors").setValue("3");

        Status status = client.saveJob(job);
        if(status.canProceed()) {
            System.out.println("Created Job with Job Id: "+ job.getPersistenceId());
        } else {
            System.out.println("Something went wrong creating the job");
        }
    }

    public static void startJob() {
        String url = "http://localhost:12000/sqoop/";
        SqoopClient client = new SqoopClient(url);
        MJob job = client.getJob("myJobs");
        //启动任务
        long jobId = job.getPersistenceId();
        MSubmission submission = client.startJob("myJobs");
        System.out.println("JOB提交状态为 : " + submission.getStatus());
        while(submission.getStatus().isRunning() && submission.getProgress() != -1) {
            System.out.println("进度 : " + String.format("%.2f %%", submission.getProgress() * 100));
            //三秒报告一次进度
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("JOB执行结束... ...");
        System.out.println("Hadoop任务ID为 :" + submission.getExternalJobId());
        Counters counters = submission.getCounters();
        if(counters != null) {
            System.out.println("计数器:");
            for(CounterGroup group : counters) {
                System.out.print("\t");
                System.out.println(group.getName());
                for(Counter counter : group) {
                    System.out.print("\t\t");
                    System.out.print(counter.getName());
                    System.out.print(": ");
                    System.out.println(counter.getValue());
                }
            }
        }
        if(submission.getError() != null) {
            System.out.println("JOB执行异常，异常信息为 : " +submission.getError());
        }
        System.out.println("MySQL通过sqoop传输数据到HDFS统计执行完毕");
    }
}
