package org.learning.SpringStarter.util;

import java.io.File;

public class AppUtil {
    public static String get_upload_path(String fileName){
    return new File("src\\main\\resources\\static\\uploads").getAbsolutePath() + "\\"+ fileName;
    }
    public static String set_upload_path(String fileName){
        return new File("src\\main\\resources\\static\\images").getAbsolutePath() + "\\"+ fileName;
        }

}
