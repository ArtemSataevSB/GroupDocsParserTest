package com.groupdocs.parser.example;

import com.groupdocs.parser.Parser;
import com.groupdocs.parser.data.PageImageArea;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        Path InputFilePath = Paths.get("D:\\ASPOSE\\SampleFiles\\SampleWithImages.docx");
        Path OutputPath = Paths.get("D:\\ASPOSE\\OutFiles\\");

        // Verify if input file exists and is not a directory
        if(!Files.exists(InputFilePath) || Files.isDirectory(InputFilePath)) {
            System.out.printf("File %s does not exist or is a directory.%n", InputFilePath);
            return;
        }

        // Create an instance of Parser class
        try (Parser parser = new Parser(InputFilePath.toString())) {
            // Extract images
            Iterable<PageImageArea> images = parser.getImages();
            // Verify if images extraction is supported
            if (images == null) {
                System.out.println("Images extraction is not supported.");
                return;
            }
            // Create output directory
            try {
                Files.createDirectories(OutputPath);
            } catch (IOException e) {
                System.err.println("Failed to create directory." + e.getMessage());
                return;
            }
            int imageCounter = 0;
            // Iterate over images
            for (PageImageArea image : images)
            {
                // Save the image in its original file format
                image.save(String.format("%s\\img%d%s", OutputPath, imageCounter, image.getFileType().getExtension()));
                imageCounter++;
            }
            System.out.printf("Extracted %d images to directory %s.%n", imageCounter, OutputPath);
        }
    }
}