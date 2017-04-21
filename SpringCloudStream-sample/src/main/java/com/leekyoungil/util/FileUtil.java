package com.leekyoungil.util;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Created by leekyoungil (leekyoungil@gmail.com) on 4/14/2017.
 *
 * filer 관련 유틸 class
 */
@Log4j2
public class FileUtil {

    public static boolean writeFileByString (String path, String data) throws IOException {
        try {
            File file = new File(path+"_temp");
            file.getParentFile().mkdirs();

            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();

            Files.move(file.toPath(), new File(path).toPath(), StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException ex) {
            log.error("파일을 기록하던 도중 오류가 발생 했습니다.");
            throw ex;
        }

        return true;
    }
}
