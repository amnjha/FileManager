package aman.filemanager.controllers;

import aman.filemanager.data.FileSystemRestResponse;
import aman.filemanager.data.constants.HttpResponseCodes;
import aman.filemanager.exceptions.SystemExcpetion;
import aman.filemanager.service.FileSystemService;
import aman.filemanager.service.StorageService;
import aman.filemanager.utils.CommonUtils;
import aman.filemanager.utils.HTMLElementUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/fileSystem", produces = MediaType.APPLICATION_JSON)
public class FileSystemRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemRestController.class);

    @Autowired
    private FileSystemService fileSystemService;
    @Autowired
    private StorageService storageService;

    @RequestMapping(path = "/dir/open/{directoryId}", method = RequestMethod.GET)
    public FileSystemRestResponse openDirectory(@PathVariable("directoryId") String directoryId) {
        FileSystemRestResponse restResponse = new FileSystemRestResponse();

        String htmlResult = HTMLElementUtils.buildFileSystemDiveElement(fileSystemService.getAllChildren(directoryId));
        Map<String, Object> result= new HashMap<>();
        result.put("html", htmlResult);
        result.put("id", directoryId);

        restResponse.setResult(result);
        restResponse.setStatus(true);
        restResponse.setResponseCode(HttpResponseCodes.SUCCESS);
        restResponse.setMessage("Done");
        return restResponse;
    }

    @RequestMapping(path = "/dir/create", method = RequestMethod.POST)
    public FileSystemRestResponse createDirectory(@RequestParam String directoryName, @RequestParam(defaultValue = "ROOT") String parentDirectoryId) throws SystemExcpetion {
        FileSystemRestResponse restResponse = new FileSystemRestResponse();
        restResponse.setResponseCode(HttpResponseCodes.SUCCESS);
        restResponse.setStatus(true);
        restResponse.setMessage("Done!");
        restResponse.setResult(fileSystemService.addOrUpdateDirectory(directoryName, parentDirectoryId));

        return restResponse;
    }

    @RequestMapping(path = "/file/delete", method = RequestMethod.DELETE)
    public FileSystemRestResponse deleteDirectory(@RequestParam("id") String fileId, @RequestParam String fileName) throws SystemExcpetion {

       FileSystemRestResponse restResponse = new FileSystemRestResponse();
       try {
           restResponse.setResponseCode(HttpResponseCodes.SUCCESS);
           restResponse.setStatus(true);
           restResponse.setMessage("Done!");
           fileSystemService.delete(fileId);
           storageService.delete(fileId+ CommonUtils.getFileExtensionIfExists(fileName));
       }catch (Exception e){
           throw new SystemExcpetion(e);
       }
        return restResponse;
    }

    @RequestMapping(path = "/dir/delete", method = RequestMethod.DELETE)
    public FileSystemRestResponse deleteFile(@RequestParam("id") String directoryId) throws SystemExcpetion {

        FileSystemRestResponse restResponse = new FileSystemRestResponse();
        try {
            restResponse.setResponseCode(HttpResponseCodes.SUCCESS);
            restResponse.setStatus(true);
            restResponse.setMessage("Done!");
            fileSystemService.delete(directoryId);
        }catch (Exception e){
            throw new SystemExcpetion(e);
        }
        return restResponse;
    }

    @RequestMapping(path = "/find/name/{name}", method = RequestMethod.GET)
    public FileSystemRestResponse findAllByName(@PathVariable("name") String name) {
        FileSystemRestResponse restResponse = new FileSystemRestResponse();
        restResponse.setResponseCode(HttpResponseCodes.SUCCESS);
        restResponse.setStatus(true);
        restResponse.setResult(fileSystemService.find(name));
        restResponse.setMessage("Done");
        return restResponse;
    }

    @RequestMapping(path = "/dir/all", method = RequestMethod.GET)
    public FileSystemRestResponse getAllDirectories(){
        FileSystemRestResponse restResponse = new FileSystemRestResponse();
        restResponse.setResult(fileSystemService.getAllDirectories());
        restResponse.setStatus(true);
        restResponse.setResponseCode(HttpResponseCodes.SUCCESS);
        restResponse.setMessage("Done!");
        return restResponse;
    }

    @ExceptionHandler(Exception.class)
    public FileSystemRestResponse handleException(Exception logException) {
        LOGGER.error("Error while processing rest call", logException);
        return FileSystemRestResponse.EXCEPTION_RESPONSE;
    }
}
