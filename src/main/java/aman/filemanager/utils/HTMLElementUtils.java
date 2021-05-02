package aman.filemanager.utils;

import aman.filemanager.data.FileSystemObject;
import aman.filemanager.data.ObjectType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HTMLElementUtils {

    private static final String DIV_START="<div";
    private static final String DIV_END="</div>";
    private static final String FOLDER_ICON="glyphicon glyphicon-folder-close";
    private static final String FILE_ICON="glyphicon glyphicon-file";

    public static String buildFileSystemDivElement(List<FileSystemObject> fileSystemObjects){
        StringBuilder result= new StringBuilder();

        fileSystemObjects=fileSystemObjects.stream().sorted(Comparator.comparing(e->e.getName().toLowerCase())).collect(Collectors.toList());

        List<String> folderList = new ArrayList<>();
        List<String> fileList = new ArrayList<>();
        for(FileSystemObject fileSystemObject : fileSystemObjects){
            String iconClass = ObjectType.DIRECTORY.equals(fileSystemObject.getType())?FOLDER_ICON:FILE_ICON;

            String divString =DIV_START +" class=\"col-sm-3\" >";
                    divString+="<div class=\"well element\" id=\""+fileSystemObject.getId()+"\">";
                    divString+="<h4 id=\""+fileSystemObject.getId()+"\" class=\""+fileSystemObject.getType()+" file-system-element\" value=\""+fileSystemObject.getName()+"\">"+"<span class= \""+iconClass+"\"> </span> "+fileSystemObject.getName()+"</h4>";
                    divString+=DIV_END;
            divString+=DIV_END;

            if(ObjectType.DIRECTORY.equals(fileSystemObject.getType()))
                folderList.add(divString);
            else
                fileList.add(divString);
        }

        folderList.forEach(result::append);
        fileList.forEach(result::append);

        return result.toString();
    }
}
