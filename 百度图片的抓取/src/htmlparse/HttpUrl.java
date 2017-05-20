package htmlparse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by hg_yi on 17-5-16.
 */
public class HttpUrl {
    public static String request(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String entity = null;

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml," +
                "application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch, br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Cache-Control", "max-age=0");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cookie", "BDqhfp=dog%26%26NaN%26%260%26%261;" +
                " BDIMGISLOGIN=0; BAIDUID=BF018787B936DB4DFECB09E7B90A78A6:FG=1;" +
                " BIDUPSID=BF018787B936DB4DFECB09E7B90A78A6; PSTM=1494502324;" +
                " BDRCVFR[tox4WRQ4-Km]=mk3SLVN4HKm; BDRCVFR[-pGxjrCMryR]=mk3SLVN4HKm;" +
                " BDRCVFR[CLK3Lyfkr9D]=mk3SLVN4HKm; " +
                "indexPageSugList=%5B%22bird%22%2C%22dog%22%2C%22github%22%5D; " +
                "cleanHistoryStatus=0; BDORZ=FFFB88E999055A3F8A630C64834BD6D0; " +
                "PSINO=1; H_PS_PSSID=; userFrom=null");
        httpGet.setHeader("Host", "image.baidu.com");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; " +
                "Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/58.0.3029.110 Safari/537.36");

        try {
            //客户端执行httpGet方法，返回响应
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            //得到服务响应状态码
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                entity = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }

            httpResponse.close();
            httpClient.close();
        } catch (ClientProtocolException e) {
            entity = null;
        } catch (IOException e) {
            entity = null;
        }

        return entity;
    }
}
