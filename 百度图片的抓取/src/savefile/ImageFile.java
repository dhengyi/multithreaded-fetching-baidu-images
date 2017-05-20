package savefile;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Created by hg_yi on 17-5-16.
 */
public class ImageFile implements Runnable {
    InputStream inputStream = null;
    FileOutputStream outputStream = null;
    static String dir = null;
    int begin = 0, last = 0;
    List<String> imageUrls = new ArrayList<>();


    //设置线程需要的参数
    public ImageFile(int begin, int last, ArrayList<String> imageUrls) {
        this.begin = begin;
        this.last = last;
        this.imageUrls = imageUrls;
    }

    //创建文件夹
    public static void createDir() {
        Scanner scanner = new Scanner(System.in);
        dir = scanner.nextLine();

        File file = new File(dir);
        if (file.exists()) {
            out.println("dir is exists");
        } else {
            file.mkdir();
        }
    }

    @Override
    public void run() {
        for (int i = begin; i < last; i++) {
            out.println(imageUrls.get(i));

            try {
                URL url = new URL(imageUrls.get(i));
                URLConnection conn = url.openConnection();
                conn.setConnectTimeout(1000);
                 conn.setReadTimeout(5000);
                conn.connect();
                inputStream = conn.getInputStream();
            } catch (Exception e) {
                continue;
            }

            out.println("success!!!!!!!!!!!!!!!!!!!!!!!!!!");

            //创建文件，以url名为文件名
            String filename = dir + '/' + imageUrls.get(i).substring(imageUrls.get(i).
                    lastIndexOf('/') + 1);
            File file1 = new File(filename);
            try {
                if (!file1.exists()) {
                    file1.createNewFile();

                    outputStream = new FileOutputStream(new File(filename));
                    byte[] buf = new byte[102400];
                    int length = 0;
                    while ((length = inputStream.read(buf, 0, buf.length)) != -1) {
                        outputStream.write(buf, 0, length);
                    }
                }
            } catch (FileNotFoundException e) {
                continue;
            } catch (IOException e) {
                continue;
            }

            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
