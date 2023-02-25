package com.sheroozdrive.SheroozDrive.repository;

import com.sheroozdrive.SheroozDrive.model.File;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends MongoRepository<File, String> {
    List<File> findByOwnerId(String ownerId);

    Optional<List<File>> findByFolderIsNullAndOwnerId(String owner_id);
}
