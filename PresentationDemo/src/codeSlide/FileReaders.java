package codeSlide;

import java.io.*;
import java.util.*;

public class FileReaders implements Iterable<File> {
	
	public static final String TEST_FOLDER_LOCATION = "src/codeSlide/test code";

	private String folder;
	
	private Set<File> files = new HashSet<>();
	
//	public static void main(String[] args) {
//		FileReaders p = new FileReaders();
//	}
//	
	public FileReaders() {
		this(TEST_FOLDER_LOCATION);
	}
	
	public FileReaders(String folderPath) {
		this.folder = folderPath;
		
		File directory = new File(folderPath);
		
		for (File file : directory.listFiles()) {	// could use file filter for the purposes of filtering by file type/language...
			if (file.isDirectory()) continue;
			
			files.add(file);
			// this loop really doesn't do anything other than filter out directories
		}
	}
	
	public Iterator<File> iterator() {
		return files.iterator();
	}
	
	public static String readFileContents(File file) {
		FileReader fr;
		String contents = "";
		try {
			fr = new FileReader(file);
			
			int i;
		    while ((i = fr.read()) != -1) {
		    	contents += (char) i;
		    }
		      
		} catch (IOException e) {
			e.printStackTrace();
		}
			  
		return contents;
	}

}
