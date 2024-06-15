package de.aredblock.polygonmc.files;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

public final class FilesHelper {

    public static String inputStreamToString(InputStream inputStream) throws IOException {
        var result = new ByteArrayOutputStream();
        var buffer = new byte[1024];
        for (int length; (length = inputStream.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }

        return result.toString(StandardCharsets.UTF_8);
    }

    public static String readGZipFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();

        try (var fis = new FileInputStream(filePath);
             var gis = new GZIPInputStream(fis)) {

            int nextByte;
            while ((nextByte = gis.read())!= -1) {
                contentBuilder.append((char) nextByte);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

}
