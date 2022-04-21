package com.cnblogs.hoojo.sensitivewords;

import com.cnblogs.hoojo.sensitivewords.business.model.SensitiveWords;
import com.cnblogs.hoojo.sensitivewords.filter.dfa.executor.DfaFilterExecutor;
import junit.framework.TestCase;

import java.io.*;
import java.util.Set;

/**
 * @author ybf
 * @create 2022-04-18 13:53
 * @desc
 **/
public class Test extends TestCase {

    public void test1(){
        try {
            DfaFilterExecutor.getInstance().init();
            DfaFilterExecutor commonWords = DfaFilterExecutor.getInstance();
            commonWords.put(new SensitiveWords("美国"));
//            commonWords.put(new SensitiveWords("岳飞"));
//            commonWords.put(new SensitiveWords("朋友"));
            String content = getContent();
            Set<String> words = commonWords.getWords(false, content);
            boolean contains = commonWords.contains(false, content);
            System.out.println(words);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getContent() throws IOException {
        File file = new File("D:\\岳飞传.txt");
        InputStreamReader in = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader br = new BufferedReader(in);
        StringBuffer sb = new StringBuffer();
        String s = "";
        while ((s = br.readLine()) != null) {
            sb = sb.append(s);
        }
        return sb.toString();
    }

    public void test2(){
        try {
            DfaFilterExecutor common = DfaFilterExecutor.getInstanceV2();
            DfaFilterExecutor strong = DfaFilterExecutor.getInstanceV2();
            DfaFilterExecutor supper = DfaFilterExecutor.getInstanceV2();
            common.init();
            strong.init();
            supper.init();
            common.put(new SensitiveWords("岳飞"));
            strong.put(new SensitiveWords("美国"));
            supper.put(new SensitiveWords("金朝"));
            String content = getContent();
            Set<String> words = common.getWords(false, content);
            Set<String> words1 = strong.getWords(false, content);
            Set<String> words2 = supper.getWords(false, content);
            System.out.println("=========");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}