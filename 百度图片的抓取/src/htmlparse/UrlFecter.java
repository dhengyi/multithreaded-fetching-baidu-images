package htmlparse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

/**
 * Created by hg_yi on 17-5-16.
 * image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word=bird&
 */

public class UrlFecter {
    public static List<String> urlParse(List<String> imageUrl, List<String> list) {
        //首先对网页进行链接请求，拿到网页源码
        for(int i = 0; i < list.size(); i++) {
            String html = HttpUrl.request(list.get(i));

            out.println(i);
            //对网页源码进行解析，拿到当前页面的所有图片的链接
            imageUrl = getImageUrl(html, imageUrl);
        }

        return imageUrl;
    }

    //使用Jsoup和Fastjson对网页源码进行解析，提取出当前页的所有图片源链接
    public static List<String> getImageUrl(String html, List<String> imageUrl) {
        Document document = Jsoup.parse(html);
        String json = null;

        //首先得到图片源链接所在的json字符串，使用正则表达式
        Pattern pattern = Pattern.compile("flip.setData\\('imgData'.*\\);");
        Matcher matcher = pattern.matcher(document.toString());

        //将得到的东西转换为正确的Json格式
        while (matcher.find()) {
            json = matcher.group().toString();
        }

        int begin = json.indexOf("\"data\":");
        int last = json.indexOf("});");
        json = json.substring(begin+7, last);

        //对json进行解析，拿到当前页面所有的所有图片源链接
        JSONArray jsonArray = JSONArray.parseArray(json);
        for(int i = 0; i < jsonArray.size()-1; i++) {
            JSONObject temp =  jsonArray.getJSONObject(i);
            String url = temp.getString("objURL");

            imageUrl.add(url);
        }

        return imageUrl;
    }
}
