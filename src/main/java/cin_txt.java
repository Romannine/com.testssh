import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

public class cin_txt {
    public static void main(String args[]) {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            /* 读入TXT文件 */
            String pathname = "D:\\Roman9\\11.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            line = br.readLine();
            String op = "";
            while (line != null) {
                line = br.readLine(); // 一次读入一行数据
                System.out.println("line ="+line);
                String[] st1=line.split("\"\"");
                for (String nst1: st1) {
                    if (nst1.equals("null")){
                        nst1="0";
                    }
                    op += nst1;
                }
                System.out.println("op ="+op);
            }

            /* 写入Txt文件 */
            File writename = new File("D:\\Roman9\\output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(op); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}