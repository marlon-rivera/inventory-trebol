package com.trebol.inventory.domain.spi;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageImagePort {

    String uploadImage(MultipartFile file) throws Exception;

}
