package com.sheroozdrive.SheroozDrive.storage.model;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface TusFilesRepository extends MongoRepository<TusFile, String> {
}
