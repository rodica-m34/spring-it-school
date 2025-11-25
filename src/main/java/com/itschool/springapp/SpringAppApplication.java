package com.itschool.springapp;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;

@SpringBootApplication
public class SpringAppApplication {

	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		SpringApplication.run(SpringAppApplication.class, args);
	}

}
