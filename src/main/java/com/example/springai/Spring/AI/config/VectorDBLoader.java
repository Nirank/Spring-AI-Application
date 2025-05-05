package com.example.springai.Spring.AI.config;


import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.ai.document.Document;



import java.io.File;
import java.util.List;

//@Configuration
public class VectorDBLoader {


//    @Value("classpath:/files/System Design Interview An Insider’s Guide by Alex Xu (z-lib.org).pdf")
//    private Resource resource;
//
//
//    @Bean
//     SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel) {
//
//        SimpleVectorStore vectorStore
//                = new SimpleVectorStore(embeddingModel);
//
//        File vectorStoreFile =
//                new File("/C:/Users/a/Desktop/Dev/Spring APPs/Spring-AI/src/main/resources/vector_store.json");
//
//        if(vectorStoreFile.exists()) {
//            System.out.println("Loaded Vector Store File!");
//            vectorStore.load(vectorStoreFile);
//        } else {
//
//            System.out.println("Creating Vector Store!");
//
//            PdfDocumentReaderConfig config
//                    = PdfDocumentReaderConfig
//                    .builder()
//                    .withPagesPerDocument(1 )
//                    .build();
//
//            PagePdfDocumentReader reader
//                    = new PagePdfDocumentReader(pdfResource,config);
//
//            var textSplitter = new TokenTextSplitter();
//
//            List<Document> docs =
//                    textSplitter.apply(reader.get());
//
//            vectorStore.add(docs);
//            vectorStore.save(vectorStoreFile);
//
//            System.out.println("Vector Store Created Successfully");
//        }
//        return vectorStore;
//    }
}
