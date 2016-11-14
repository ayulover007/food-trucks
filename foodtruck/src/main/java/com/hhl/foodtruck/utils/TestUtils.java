package com.hhl.foodtruck.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public final class TestUtils {

    
    /**
     * Retrieve the contents of the resource as a string.
     * 
     * @param name Resource name
     * @return Resource contents as a string
     * @throws IOException Error reading the resource
     */
    public static String getResourceAsString(final String name) throws IOException {
        final String is = openResource(name);
        File file = new File(is);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
        	throw new IOException(e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        System.out.println(buffer.toString());
        return buffer.toString();
    }

    
    /**
     * Open a resource using the current threads classloader.
     * 
     * @param name Resource name
     * @return Inputput stream
     */
    private static String openResource(final String name) {
        final String is = TestUtils.class.getClassLoader().getResource(name).getFile();        
        return is;
    }
}
