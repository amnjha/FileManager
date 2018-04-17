package aman.filemanager.data.repository;

import aman.filemanager.data.FileSystemObject;
import aman.filemanager.data.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface FileSystemObjectRepository extends JpaRepository<FileSystemObject, String> {

    List<FileSystemObject> findAllByNameContainingOrderByName(String name);
    List<FileSystemObject> findAllByType(ObjectType type);
}
