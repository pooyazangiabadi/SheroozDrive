package com.sheroozdrive.SheroozDrive.repository;

import com.sheroozdrive.SheroozDrive.model.Folder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FolderRepository extends MongoRepository<Folder, String> {
    List<Folder> findByOwnerId(String ownerId);
    List<Folder> findByOwnerIdAndParentIdNull(String ownerId);
    boolean existsByNameAndParentId(String name, String parentId);

    Optional<Folder> findByNameAndParentIdAndOwnerId(String name, String parent_id, String owner_id);

    Optional<List<Folder>> findByParentIsNullAndOwnerId(String owner_id);

}
