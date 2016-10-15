package com.hello.library;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by Linty on 9/11/2016.
 * 处理csv文件的读取和别的操作
 */
public class CsvUtility {
    public Iterable<CSVRecord> readCsvFile(String csvPath) {
        // 读取 csv 文件到FilerReader中
        // 用捕获异常的方式 进行文件读取，防止出现“文件不存在”的异常
        Reader reader = null;
        try {
            reader = new FileReader(csvPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 读取 csv 到 records中
        Iterable<CSVRecord> records = null;
        try {
            if (reader != null) {
                records = CSVFormat.EXCEL.parse(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;

    }
}
