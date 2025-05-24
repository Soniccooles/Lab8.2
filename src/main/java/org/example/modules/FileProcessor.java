package org.example.modules;

public interface FileProcessor {
    boolean supports(String filePath);
    String getDescription();
    void process(String filePath) throws Exception;
}