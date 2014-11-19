import com.hankcs.textrank.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * Created by hanwei on 14-11-18.
 */
public class Test {

    public static void main(String[] args) {

        File file = null;
        BufferedReader br = null;
        StringBuffer buffer = null;
        String Path ="/home/hanwei/IdeaProjects/MavenTest/src/main/resources/复旦大学中文语料库/艺术248/2220.txt";
        try {
            file = new File(Path);
            buffer = new StringBuffer();

            String codemode=new FileCharsetDetector().guessFileEncoding(file);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), codemode);


            br = new BufferedReader(isr);
            int s;
            if (codemode.equals("UTF8-8"))
                while ((s = br.read()) != -1) {
                    buffer.append((char) s);
                }
            else{

                FileOutputStream out = null;
                FileOutputStream outSTr = null;
                BufferedOutputStream Buff=null;

                FileWriter fw = null;
                out = new FileOutputStream(new File(Path));

                while ((s = br.read()) != -1) {
                    buffer.append((char) s);
                    out.write(toUTF_8(s));
                }
                out.close();
            }



            String content = buffer.toString();

            System.out.println(new TextRankKeyword().getKeyword("", content)); //关键词提取
            System.out.println(TextRankSummary.getTopSentenceList(content, 3));//摘要提取


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
