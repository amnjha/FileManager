package aman.filemanager.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import aman.filemanager.data.FileSystemObject;
import aman.filemanager.exceptions.StorageFileNotFoundException;
import aman.filemanager.exceptions.SystemExcpetion;
import aman.filemanager.service.FileSystemService;
import aman.filemanager.service.StorageService;
import aman.filemanager.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private final StorageService storageService;
    private final FileSystemService fileSystemService;

    @Autowired
    public FileUploadController(StorageService storageService, FileSystemService fileSystemService) {
        this.storageService = storageService;
        this.fileSystemService=fileSystemService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam String directoryId,
            RedirectAttributes redirectAttributes) throws SystemExcpetion {
        try
        {
            FileSystemObject fileSystemObject =fileSystemService.addOrUpdateFile(file.getOriginalFilename(), directoryId);
            storageService.store(file, fileSystemObject.getId()+ CommonUtils.getFileExtensionIfExists(file.getOriginalFilename()));
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
            redirectAttributes.addAttribute("directoryId", directoryId);
        }
        catch (Exception e)
        {
            throw new SystemExcpetion(e);
        }
        return "redirect:/home/";
    }

    @ExceptionHandler(SystemExcpetion.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}