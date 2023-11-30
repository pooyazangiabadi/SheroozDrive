package com.sheroozdrive.SheroozDrive.storage.services;

import org.springframework.core.io.Resource;
import com.sheroozdrive.SheroozDrive.storage.model.TusFile;
import java.io.InputStream;

public interface StorageService {
    int processStream(String uuid, InputStream inputStream) throws Exception;
    Resource loadResource(String uuid) throws Exception;
}
