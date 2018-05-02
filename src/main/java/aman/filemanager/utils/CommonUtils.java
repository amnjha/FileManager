package aman.filemanager.utils;

public class CommonUtils {
    public static String getFileExtensionIfExists(String fileName){
        String ext="";
        if(fileName.contains(".")){
            ext=fileName.substring(fileName.lastIndexOf("."));
        }
        return  ext;
    }
}
