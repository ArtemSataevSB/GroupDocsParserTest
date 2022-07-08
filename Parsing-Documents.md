# Parsing documents with GroupDocs.Parser

Retrieving data from documents is one of the most essential tasks when processing documents programmatically. The complexity of this task depends significantly on the format of the original document. Plain-text or comma-separated files are relatively simple to handle in almost any programming language. Whereas, PDF, word processing or spreadsheets would, most likely, require special third-party libraries which simplify working with these formats.    

Most of the free or commercial libraries typically support one or only a few document formats. Some work with PDF only (PDFBox, JPedal), others work with word processing documents only (Spire.Doc, JODF) and so on. To support a broader range of formats, you either have to combine several libraries or use enterprise-level libraries (Spire.Office, GroupDocs).      
  
## Why GroupDocs.Parser?
**Runs in your favorite environment**

The GroupDocs.Parser API is available for the following popular development environments:
- [.NET](https://products.groupdocs.com/parser/net/)
- [Java](https://products.groupdocs.com/parser/java/)
- [cURL](https://products.groupdocs.cloud/parser/curl)
- [PHP](https://products.groupdocs.cloud/parser/php/)
- [Python](https://products.groupdocs.cloud/parser/python/)
- [Ruby](https://products.groupdocs.cloud/parser/ruby/)
- [Node.js](https://products.groupdocs.cloud/parser/nodejs/)

**One API for multiple formats**

The API boasts numerous supported formats. It can easily process:
- **Text documents:** Plain text, PDF, RTF, Markdown, HTML, XML, Microsoft Office and OpenOffice formats. 
- **Spreadsheets:**  Microsoft Office, OpenOffice and Apple iWork formats.
- **Presentations:** Microsoft Office and OpenOffice formats.
- **Ebooks:** EPUB, FB2 and CHM formats.
- **Email messages:** Microsoft Outlook and Apple Mail formats.
- **Archives:** ZIP, RAR, TAR, GZ and BZ2 formats.
- And much more...


**Rich features**

With the help of GroupDocs.Parser you can perform various tasks:
- Retrieve raw, structured or formatted text.
- Search document content by plain-text or by regular expressions.
- Parse documents by template.
- Extract tables, barcodes, forms, images and metadata.
- Extract data from archives, containers, databases and e-mail attachments.
- Work with password-protected files.
- Generate document preview images.


## Extracting images from documents
Extracting embedded images is one of the special cases of document parsing. This section shows how to extract images that are embedded into a document and save them to a local folder.

To perform this task for multiple document formats, we will use the Java version of GroupDocs.Parser.
### Prerequisites
The GroupDocs.Parser library requires Java 7 or later.
### Adding to the project
Before using the library, we need to add it to a Java project as a dependency.
For maven-based projects, just add the following configuration to `pom.xml`:
```xml
<repositories>
    <repository>
        <id>GroupDocsJavaAPI</id>
        <name>GroupDocs Java API</name>
        <url>https://repository.groupdocs.com/repo/</url>
    </repository>
</repositories>
```
```xml
<dependencies>
    <dependency>
        <groupId>com.groupdocs</groupId>
        <artifactId>groupdocs-parser</artifactId>
        <version>22.6</version>
    </dependency>
</dependencies>
```
**_Note:_** Update library version if outdated.

For non-maven projects, download [GroupDocs.Parser for Java](https://downloads.groupdocs.com/parser/java) and include .jar files from the `lib` folder to Java project.

### Extracting images
Once the library is ready, we can parse any of the supported documents. To extract all images from the whole document, we need to perform the following actions:
1. Open the document as an instance of the [Parser](https://apireference.groupdocs.com/java/parser/com.groupdocs.parser/Parser) class.
2. Call the [Parser.getImages()](https://apireference.groupdocs.com/java/parser/com.groupdocs.parser/Parser#getImages()) method to obtain a collection of image objects from the loaded document.
3. Iterate through the collection and save the images to a local folder using the [PageImageArea.save()](https://apireference.groupdocs.com/parser/java/com.groupdocs.parser.data/PageImageArea#save(java.lang.String)) method.

To reduce possible errors, we should also perform several check-ups:
1. Check if the input file exists and is not a directory.
2. Check if images extraction is supported.
3. Check if an output directory exists and if not, create it.

The following code performs the described actions and check-ups:
```java
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
                System.err.println("Failed to create a directory." + e.getMessage());
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
            System.out.printf("Extracted %d images to the directory %s.%n", imageCounter, OutputPath);
        }
    }
}
```


## Download Source Code
The code of the sample project is available on GitHub:
```shell
git clone https://github.com/ArtemSataevSB/GroupDocsParserTest.git
```