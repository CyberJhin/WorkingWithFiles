package org.example;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipParsingTest {
    private final ClassLoader cl = JsonParsingTest.class.getClassLoader();

    @Test
    void zipParsingPDFTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("arc.zip");
             ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(is))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("dummy.pdf")){
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("Dummy PDF file"));
                }
            }
        }
    }

    @Test
    void zipParsingXLSTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("arc.zip");
             ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(is))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("ex.xls")){
                    XLS xls = new XLS(zis);
                    Assertions.assertEquals(
                            "Белый Владимир Михайлович",
                            xls.excel.getSheet("Василий")
                                    .getRow(1)
                                    .getCell(1)
                                    .getStringCellValue()
                    );
                }
            }
        }

    }
    @Test
    void zipParsingCSVTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("arc.zip");
             ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(is))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("Aaddresses.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(
                            new String[]{"John","Doe","120 jefferson st.","Riverside"," NJ"," 08075"}, content.get(1)
                    );
                }
            }
        }
    }
}
