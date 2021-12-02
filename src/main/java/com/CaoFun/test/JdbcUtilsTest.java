package com.CaoFun.test;

import com.CaoFun.entity.Hjpg;
import com.CaoFun.entity.Hlabel;
import com.CaoFun.entity.Htag;
import com.CaoFun.service.HjpgService;
import com.CaoFun.service.HlabelService;
import com.CaoFun.service.HtagService;
import com.CaoFun.service.UserService;
import com.CaoFun.service.impl.HjpgServiceImpl;
import com.CaoFun.service.impl.HlabelServiceImpl;
import com.CaoFun.service.impl.HtagServiceImpl;
import com.CaoFun.service.impl.UserServiceImpl;
import com.CaoFun.utils.JdbcUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class JdbcUtilsTest {

    private HjpgService hjpgService = new HjpgServiceImpl();
    private HtagService htagService = new HtagServiceImpl();
    private HlabelService hlabelService=new HlabelServiceImpl();

    //获取随机色图功能的测试
    @Test
    public void TestGetImage(){
        Hjpg hjpg=hjpgService.getRandImage();
        System.out.println(hjpg.toString());
    }

    @Test
    public void TestTempTable(){

        List<String> words= new ArrayList<String>();
        words.add("aa");
        words.add("ab");
        words.add("aa");
        words.stream().distinct().collect(Collectors.toList()).forEach(System.out::println);
        words=words.stream().distinct().collect(Collectors.toList());//List字符串去掉重复的
        System.out.println(Integer.toString(words.size()));


        List<String> a=new ArrayList<String>();
        a.add("test3");
        List<Htag> tags=htagService.Locate(a);
        System.out.println(tags.get(0).toString());
    }


    @Test
    public void testJdbcUtils(){
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(connection);
    }

    @Test
    public void HtmlRead() throws Exception {
        URL url = new URL("http://konachan.wjcodes.com/index.php?tag=%20rating:explicit");
//        URL url = new URL("https://blog.csdn.net/u010785025/article/details/51770050");

        HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //伪装浏览器欺骗服务器

        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

//        BufferedWriter writer = new BufferedWriter(new FileWriter("g:\\Cao_HttpServiceBase\\"+"1.html"));

        String line;
        while ((line = reader.readLine()) != null) {
            if(line.contains("download_file")){
                List<String> list=GetImageUrl(line,"download_file('","'");
                for(String to_read:list){
                    String name=GetName(to_read);
                    List<String> tags=GetTags(name.substring(20));//不要开头的Konachan.com和结尾的jpg
//                    System.out.println(name);
                }
            }
//            writer.write(line);
//            writer.newLine();
        }
        reader.close();
//        writer.close();
    }

    @Test
    public void MainTest() throws Exception {

        for(int p=2;p<3;p++){
            /**
             * 第一部分！获取目标网页上的图片信息（不下载）
             */
            URL url = new URL("http://konachan.wjcodes.com/index.php?tag=%20rating:explicit%20breasts&p="+Integer.toString(5));
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //伪装浏览器欺骗服务器
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            List<Hjpg> ImageList=new ArrayList<Hjpg>();
            while ((line = reader.readLine()) != null) {
                if(line.contains("download_file")){
                    List<Hlabel> labellist=new ArrayList<Hlabel>();
                    List<Htag> taglist=new ArrayList<Htag>();
                    List<String> list=GetImageUrl(line,"download_file('","'");
                    for(String to_read:list){
                        String name=GetName(to_read);

                        List<String> tags=GetTags(name.substring(20));//不要开头的Konachan.com和结尾的jpg
                        name=GetImageUrl(name,"com%20-%","%20").get(0);//把名字缩略到数字编码方便存储


                        for(String tag:tags){
                            taglist.add(new Htag(tag));
                            labellist.add(new Hlabel(name,tag));
                        }

                        ImageList.add(new Hjpg(null,name,"g:\\Cao_HttpTest\\",to_read));
                    }
                    htagService.save(taglist);//保存标签
                    hlabelService.save(labellist);//保存关联
                }
            }
            hjpgService.save(ImageList);
            reader.close();

            /**
             * 第二部分！下载图片
             */
            for(Hjpg to_download:ImageList){
                File file=new File(to_download.getSavedir()+to_download.getName()+".jpg");
                if(!file.exists()){//如果图片还没有被下载到本地则执行
                    System.out.println("正在下载图片："+to_download.getSavedir()+to_download.getName()+".jpg");
                    DownloadByHttpClient(to_download.getOriginurl(),to_download.getSavedir()+to_download.getName()+".jpg");
                }
            }
        }


    }

    /**
     * 使用封装好的方法不加header容易被服务器拦截！！！！不推荐使用！！！！
     */
    public static void downloadHttpUrl(String url, String dir, String fileName) {
        try {
            URL httpurl = new URL(url);
            File dirfile = new File(dir);
            if (!dirfile.exists()) {
                dirfile.mkdirs();
            }
            FileUtils.copyURLToFile(httpurl, new File(dir+fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //方法1：HttpURLConnection下载图片
    @Test
    public void downloadHttpUrlTest1() throws Exception{
        try {
            URL httpurl = new URL("https://konachan.net/image/6f65a5c301f03ec0f3be0ed4db9ecde4/Konachan.com%20-%20334935%20bondage%20bronya_zaychik%20ginklaga%20honkai_impact.jpg");
            File dirfile = new File("g:\\Cao_HttpTest\\");
            if (!dirfile.exists()) {
                dirfile.mkdirs();
            }
            HttpURLConnection httpURLConnection=(HttpURLConnection)httpurl.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //伪装浏览器欺骗服务器
            copyInputStreamToFile(httpURLConnection.getInputStream(), new File("g:\\Cao_HttpTest\\"+"t3.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //方法2：通过HttpClient下载图片
    @Test
    public void downloadHttpUrlTest2() throws Exception{
        CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
        HttpGet httpGet=new HttpGet("https://konachan.net/image/6f65a5c301f03ec0f3be0ed4db9ecde4/Konachan.com%20-%20334935%20bondage%20bronya_zaychik%20ginklaga%20honkai_impact.jpg"); // 创建httpget实例
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        httpGet.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        CloseableHttpResponse response=httpClient.execute(httpGet); // 执行http get请求
        HttpEntity entity=response.getEntity(); // 获取返回实体
        if(entity!=null){
            System.out.println("ContentType:"+entity.getContentType().getValue());
            InputStream inputStream=entity.getContent();
            org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, new File("g:\\Cao_HttpTest\\"+"1.jpg"));
        }
        response.close(); // response关闭
        httpClient.close(); // httpClient关闭
    }

    /**函数：使用HttpClient下载图片*/
    public void DownloadByHttpClient(String url,String file) throws IOException {
        CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
        HttpGet httpGet=new HttpGet(url); // 创建httpget实例
        httpGet.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        CloseableHttpResponse response=httpClient.execute(httpGet); // 执行http get请求
        HttpEntity entity=response.getEntity(); // 获取返回实体
        if(entity!=null){
//            System.out.println("ContentType:"+entity.getContentType().getValue());
            InputStream inputStream=entity.getContent();
            org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, new File(file));
        }
        response.close(); // response关闭
        httpClient.close(); // httpClient关闭
    }


    /**函数：提取出下载的文件Url，left和right字符串包裹所需的字符串*/
    public List<String> GetImageUrl(String line,String left,String right)throws Exception{
        int t=0;
        List<String> list=new ArrayList<String>();
        while(line.indexOf(left,t)>=0){
            t=line.indexOf(left,t)+left.length();
            String temp=line.substring(t,line.indexOf(right,t));
            list.add(temp);
//            System.out.println(temp);
            t=line.indexOf(right,t)+1;
        }
        return list;
    }

    /**函数：解析文件名与标签，list第一项为文件名，其余为标签*/
    public String GetName(String url){
        int t=0;
        while(url.indexOf("/",t)>=0){
            t=url.indexOf("/",t)+1;
        }
        return url.substring(t);
    }

    public List<String> GetTags(String line){
        List<String> list=new ArrayList<String>();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i < line.length();i++) {
            if ((line.charAt(i) <= 'z' && line.charAt(i) >= 'a') || (line.charAt(i) == '_')) {
                sb.append(line.charAt(i));
            } else {
                if (sb.length() > 0) {
                    list.add(sb.toString());
//                    System.out.println(sb.toString());
                    sb.setLength(0);//清空字符串制造器
                }
            }
        }
        return list;
    }


//    @Test
//    public void StringScale(String left,String right) throws Exception {
//        String a="onclick=\"download_file('https://konachan.net/image/6f65a5c301f03ec0f3be0ed4db9ecde4/Konachan.com%20-%20334935%20bondage%20bronya_zaychik%20ginklaga%20honkai_impact.jpg','334935')\"><span class=\"am-ico+ick=\"download_file('https://konachan.net/image/6f65a5zaychik%20ginklaga%20honkai_impact.jpg','3";
//        System.out.println(a);
//        String[] outer=new String[]{"download_file('","'"};
//        System.out.println(outer[0]);
//        int t=0;
//        while(a.indexOf(left,t)>=0){
//            t=a.indexOf(left,t)+left.length();
//            String temp=a.substring(t,a.indexOf(right,t));
//            System.out.println(temp);
//            t=a.indexOf(right,t)+1;
//        }
//    }

}
