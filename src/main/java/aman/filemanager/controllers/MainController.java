package aman.filemanager.controllers;

import aman.filemanager.exceptions.StorageFileNotFoundException;
import aman.filemanager.exceptions.SystemExcpetion;
import aman.filemanager.service.FileSystemService;
import aman.filemanager.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/home")
public class MainController {

    private static final String ROOT_ELEMENT = "ROOT";
    @Autowired
    private StorageService storageService;
    @Autowired
    private FileSystemService fileSystemService;

    @RequestMapping(method = RequestMethod.GET)

    public String showHomeContents(Model model, @RequestParam(defaultValue = ROOT_ELEMENT) String directoryId) {
        model.addAttribute("directoryId", directoryId);
        return "home";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exc) {
        return ResponseEntity.notFound().build();
    }
}
