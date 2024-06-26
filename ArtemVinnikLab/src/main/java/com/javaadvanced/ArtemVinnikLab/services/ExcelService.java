package com.javaadvanced.ArtemVinnikLab.services;

import com.javaadvanced.ArtemVinnikLab.entities.Product;
import com.javaadvanced.ArtemVinnikLab.repositories.IProductRepository;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    private IProductRepository repository;

    private XSSFWorkbook workbook;

    private static final String[] headers = { "Code", "Title", "Link", "Description", "Price" };

    public ExcelService(IProductRepository repository) {
        this.repository = repository;
    }

    public void createWorkbook() {
        workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Products");
        int rowNum = 0;

        rowNum = setHeader(rowNum, sheet);
        setBody(rowNum, sheet);
    }

    private int setHeader(int rowNum, XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(rowNum++);
        int cellNum = 0;

        for (String column : headers) {
            XSSFCell cell = row.createCell(cellNum++);
            cell.setCellValue(column);
        }

        return rowNum;
    }

    private void setBody(int rowNum, XSSFSheet sheet) {
        List<Product> pList = repository.findAll();

        for (Product prod : pList) {
            XSSFRow row = sheet.createRow(rowNum++);
            String[] data = prod.getDataToString();

            for (int col = 0; col < data.length; col++) {
                XSSFCell cell = row.createCell(col);
                cell.setCellValue(data[col]);
            }
        }
    }

    public byte[] getWorkBookInBytes() {
        ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
        try {
            workbook.write(byteArr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArr.toByteArray();
    }

}
