package demo01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.logging.Logger;

/**
 * @Author tianxin
 * @Date 2019/2/20
 * @Description:
 */
public class test01 {
    public static void main(String[] args) throws Exception {
        int a = 18;
        int b = 23;
        try {
            String[] sss = new String[] { "python", "C:\\Users\\86132\\Desktop\\tools\\调用python\\src\\demo01\\demo.py", String.valueOf(a), String.valueOf(b) };
            Process proc = Runtime.getRuntime().exec(sss);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            String s = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
