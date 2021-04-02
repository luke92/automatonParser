package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface FileUtil {
	public static List<String> getLinesFromFile(String fileName) {
		List<String> lines = new ArrayList<>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			File file = new File(fileName); // creates a new file instance
			fr = new FileReader(file); // reads the file
			br = new BufferedReader(fr); // creates a buffering character input stream
			String line = "";
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			fr.close();
			br.close();
			return lines;
		}
		catch(Exception e) {
			return null;
		}
	}
}
