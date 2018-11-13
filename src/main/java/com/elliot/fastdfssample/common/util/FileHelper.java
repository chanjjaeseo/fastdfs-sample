package com.elliot.fastdfssample.common.util;

import com.elliot.fastdfssample.common.constant.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class FileHelper {

    private static Logger logger = LoggerFactory.getLogger(FileHelper.class);

    public static boolean isSuport(String originName){
        String extension = getFileExtension(originName);
        for(FileType fileType : FileType.values()) {
            if(fileType.getDesc().equals(extension)) {
                return true;
            }
        }
        return false;
    }

    public static String getFileExtension(String originName) {
        String fileExtension = null;
        if (originName.contains(".")) {
           int indexOfPointer = originName.lastIndexOf(".");
           fileExtension = originName.substring(indexOfPointer + 1);
        } else {
            logger.error("file type is not suport, file name is {}", originName);
        }
        return fileExtension;
    }

    public static void copyStream(OutputStream os, InputStream is, int bufferSize) throws IOException {
        byte[] byteBuffer = new byte[bufferSize];
        while (is.read(byteBuffer) > 0) {
            os.write(byteBuffer);
        }
        os.flush();
        os.close();
    }

    public static void main(String[] args) {
        System.out.println(getFileExtension("aa.jpg"));
    }

}
