package com.trebol.inventory.adapters.driven.firebase;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.trebol.inventory.domain.spi.IStorageImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class FirebaseAdapter implements IStorageImagePort {

    private final Storage storage;

    private String bucket = "trebol-3c95d.appspot.com";

    @Override
    public String uploadImage(MultipartFile file) throws Exception {
        String fileName = generateFileName(file);
        System.out.println(fileName);
        System.out.println(bucket);
        BlobId blobId = BlobId.of(bucket, fileName);
        System.out.println(blobId);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();

        storage.create(blobInfo, file.getBytes());

        URL signedUrl = storage.signUrl(blobInfo, 3650, TimeUnit.DAYS);

        return signedUrl.toString();
    }

    private String generateFileName(MultipartFile file) {
        return UUID.randomUUID().toString() + getExtension(file.getOriginalFilename());
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
