package httpbrowser;

import htmlparse.UrlFecter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hg_yi on 17-5-16.
 * 本类主要构建要爬取的Url
 */

public class CreateUrl {
    //首先构建需要抓取的10页百度图片页面
    public static List<String> CreateMainUrl() {
        Scanner scanner = new Scanner(System.in);
        String urlMain = scanner.nextLine();
        String urlTemp = urlMain;
        List<String> list = new ArrayList<>();

        //构建需要爬取的10页Url
        for(int i= 0; i < 10; i++)
        {
            urlMain = "http://" + urlMain + "pn=" + i*60;
            list.add(urlMain);
            urlMain = urlTemp;
        }

        return list;
    }

    //创建每个要爬取图片的Url
    public static List<String> CreateImageUrl(List<String> list) {
        List<String> imagelist = new ArrayList<>();

        imagelist = UrlFecter.urlParse(imagelist, list);

        return imagelist;
    }
}
