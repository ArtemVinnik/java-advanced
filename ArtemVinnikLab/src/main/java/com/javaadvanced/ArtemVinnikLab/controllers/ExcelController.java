package com.javaadvanced.ArtemVinnikLab.controllers;

import com.javaadvanced.ArtemVinnikLab.models.UrlRequest;
import com.javaadvanced.ArtemVinnikLab.repositories.IProductRepository;
import com.javaadvanced.ArtemVinnikLab.services.ExcelService;
import com.javaadvanced.ArtemVinnikLab.services.WebParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list")
public class ExcelController {
    @Autowired
    private IProductRepository pRepository;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<byte[]> getProducts() {
        ExcelService service = new ExcelService(pRepository);

        service.createWorkbook();
        byte[] excelBytes = service.getWorkBookInBytes();

        return ResponseEntity.ok()
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=products.xlsx")
                .contentLength(excelBytes.length)
                .body(excelBytes);
    }

    @PostMapping("/")
    public ResponseEntity<String> createProductsList(@RequestBody UrlRequest request) {
        WebParserService parser = new WebParserService(request.getUrl());
        pRepository.saveAll(parser.parsePage());

        return ResponseEntity.ok("List successfully added");
    }
}
