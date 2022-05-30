package com.comicall.ComicallApi.controllers;


import com.comicall.ComicallApi.services.images.IImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/storage")
@CrossOrigin(origins = "*")
public class StorageController {

    @Autowired
    private IImagesService _imageService;

    @GetMapping()
    @ResponseBody
    ResponseEntity<UrlResource> load(@RequestParam String path,  HttpServletRequest request){

        UrlResource file = _imageService.load(path);
        String mimetype;

        try {
            mimetype = request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimetype = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        mimetype = mimetype == null ? MediaType.APPLICATION_OCTET_STREAM_VALUE : mimetype;

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimetype))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
