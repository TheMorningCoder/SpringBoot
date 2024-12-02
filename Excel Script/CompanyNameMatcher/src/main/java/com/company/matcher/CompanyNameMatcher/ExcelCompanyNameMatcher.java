package com.company.matcher.CompanyNameMatcher;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelCompanyNameMatcher {

    public static void main(String[] args) {
        String filePath = "company_data.xlsx";
        findDuplicateDomains(filePath);
    }

    public static void findDuplicateDomains(String filePath) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {
            
            Sheet sheet = workbook.getSheetAt(0);
            Map<String, Integer> domainCountMap = new HashMap<>();

            // Loop through rows to get domain names in column C (index 2)
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                // Get the domain name in column C
                Cell domainCell = row.getCell(2);
                if (domainCell != null) {
                    String domainName = domainCell.getStringCellValue();

                    // Update count for each domain
                    domainCountMap.put(domainName, domainCountMap.getOrDefault(domainName, 0) + 1);
                }
            }

            // Print domains that have a count greater than 1
            System.out.println("Repeated domain names:");
            for (Map.Entry<String, Integer> entry : domainCountMap.entrySet()) {
                if (entry.getValue() > 1) {
                    System.out.println(entry.getKey());
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading Excel file: " + e.getMessage());
        }
    }
}
