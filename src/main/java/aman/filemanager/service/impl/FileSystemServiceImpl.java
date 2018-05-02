package aman.filemanager.service.impl;

import aman.filemanager.data.FileSystemObject;
import aman.filemanager.data.ObjectType;
import aman.filemanager.data.repository.FileSystemObjectRepository;
import aman.filemanager.exceptions.SystemExcpetion;
import aman.filemanager.service.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileSystemServiceImpl implements FileSystemService {

    @Autowired
    private FileSystemObjectRepository repository;


    @Override
    public FileSystemObject addOrUpdateDirectory(String directoryName, String parentDirectoryId) throws SystemExcpetion {
        Optional<FileSystemObject> parent= repository.findById(parentDirectoryId);
        if (parent.isPresent()) {
            FileSystemObject parentObject =parent.get();
            FileSystemObject object = new FileSystemObject();
            object.setName(directoryName);
            object.setType(ObjectType.DIRECTORY);
            repository.save(object);

            List<FileSystemObject> childObjects=parentObject.getChildObjects();
            if (childObjects==null)
                childObjects=new ArrayList<>();

            childObjects.add(object);

            repository.save(parentObject);

            return object;
        }
        else
            throw new SystemExcpetion("Parent Directory not found for id : "+ parentDirectoryId);
    }

    @Override
    public FileSystemObject addOrUpdateFile(String fileName, String parentDirectoryId) throws FileNotFoundException {
        FileSystemObject parent= repository.findById(parentDirectoryId).get();
        if (parent==null || !ObjectType.DIRECTORY.equals(parent.getType()))
            throw new FileNotFoundException("Given directory not found");

        FileSystemObject file= new FileSystemObject();
        file.setName(fileName);
        file.setType(ObjectType.FILE);

        repository.save(file);

        parent.getChildObjects().add(file);
        repository.save(parent);

        return file;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<FileSystemObject> find(String name) {
        return repository.findAllByNameContainingOrderByName(name);
    }

    @Override
    public List<FileSystemObject> getAllChildren(String id) {
        Optional<FileSystemObject> fileSystemObject = repository.findById(id);
        if (fileSystemObject.isPresent())
            return repository.findById(id).get().getChildObjects();
        else
            return new ArrayList<>();
    }

    @Override
    public List<FileSystemObject> getAllDirectories() {
        return repository.findAllByType(ObjectType.DIRECTORY);
    }
}
