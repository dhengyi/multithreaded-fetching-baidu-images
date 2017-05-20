package mainmethon;

import httpbrowser.CreateUrl;
import savefile.ImageFile;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by hg_yi on 17-5-16.
 *
 * 测试数据：image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word=bird&
 *
 * 在多线程进行下载时，需要向线程中传递参数，此时有三种方法，我选择的第一种，设计构造器
 */

public class major {
    public static void main(String[] args) {
        int sum = 0;
        List<String> urlMains = new ArrayList<>();
        List<String> imageUrls = new ArrayList<>();

        //首先得到10个页面
        urlMains = CreateUrl.CreateMainUrl();

        out.println(urlMains.size());
        for(String urlMain : urlMains) {
            out.println(urlMain);
        }

        //使用Jsoup和FastJson解析出所有的图片源链接
        imageUrls = CreateUrl.CreateImageUrl(urlMains);

        for(String imageUrl : imageUrls) {
            out.println(imageUrl);
        }

        //先创建出每个图片所属的文件夹
        ImageFile.createDir();

        int average = imageUrls.size()/10;
        //对图片源链接进行下载（使用多线程进行下载）创建进程
        for(int i = 0; i < 10; i++){
            int begin = sum;
            sum += average;
            int last = sum;

            Thread image = null;
            if(i < 9) {
                image = new Thread(new ImageFile(begin, last,
                        (ArrayList<String>) imageUrls));
            } else {
                image = new Thread(new ImageFile(begin, imageUrls.size(),
                        (ArrayList<String>) imageUrls));
            }

            image.start();
        }
    }
}
