package com.elliot.fastdfssample.common.util;

import com.elliot.fastdfssample.common.constant.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    public static void main(String[] args) {
        System.out.println(getFileExtension("aa.jpg"));
    }

}
