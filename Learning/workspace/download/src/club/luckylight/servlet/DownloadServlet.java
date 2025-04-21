package club.luckylight.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import club.luckylight.util.DownloadUtils;

public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ��ȡ�ļ����������ñ���
        String filename = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");

        // ��ȡ�ļ���mime����
        String mimeType = getServletContext().getMimeType(filename);

        // �����ļ���mime����
        response.setContentType(mimeType);

        // �ļ�������
        String newFilename = DownloadUtils.getFilename(request, filename);

        // ��������ͷ��Ϣ
        response.setHeader("content-disposition", "attachment;filename=" + newFilename);

        // �Կ���
        InputStream is = getServletContext().getResourceAsStream("/download/" + filename);
        ServletOutputStream os = response.getOutputStream();
        int len = -1;
        byte[] bytes = new byte[1024];
        while ((len = is.read(bytes)) != -1) {
            os.write(bytes, 0, len);
        }

        // �ر���Դ
        os.close();
        is.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}