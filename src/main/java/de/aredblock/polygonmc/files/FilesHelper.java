package de.aredblock.polygonmc.files;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class FilesHelper {

    public static String inputStreamToString(InputStream inputStream) throws IOException {
        var result = new ByteArrayOutputStream();
        var buffer = new byte[1024];
        for (int length; (length = inputStream.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }

        return result.toString(StandardCharsets.UTF_8);
    }

}
