package it.sciancaleporevincenzo.gestRest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Utils {

    public static void downloadFile(HttpServletResponse response, byte[] allegato, String type) throws IOException {

        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");

        response.setHeader("Content-Disposition", "attachment;");
        response.setContentType(type);
        response.setContentLength(allegato.length);
        response.getOutputStream().write(allegato);
        response.getOutputStream().flush();
    }
}
