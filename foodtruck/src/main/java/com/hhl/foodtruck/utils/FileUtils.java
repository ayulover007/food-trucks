package com.hhl.foodtruck.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public final class FileUtils {

	public static String getJsonFromFile(final String filePath)
			throws FileNotFoundException {
		Scanner fileScanner = null;
		final StringBuilder foodTruckJson = new StringBuilder();
		try {
			fileScanner = new Scanner(new BufferedReader(new FileReader(
					filePath)));

			while (fileScanner.hasNextLine()) {
				final String line = fileScanner.nextLine();
				foodTruckJson.append(line);
			}
		} finally {
			fileScanner.close();
		}

		return foodTruckJson.toString();
	}
}
