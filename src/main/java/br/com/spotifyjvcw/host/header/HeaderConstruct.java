package br.com.spotifyjvcw.host.header;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HeaderConstruct {

    public static ResponseEntity<ByteArrayResource> constructFileResponse(String fileName, ByteArrayResource file) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentLength(file.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }
}
