package club.luckylight.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

public class DownloadUtils {

    public static String getFilename(HttpServletRequest request, String filename) throws UnsupportedEncodingException {
        String userAgent = request.getHeader("user-agent");
        // �����IE����EdgeΪ�ں˵������
        if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) {
            filename = URLEncoder.encode(filename, "UTF-8");
        } else {
            // ��IE������Ĵ���
            filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        }

        return filename;
    }
}