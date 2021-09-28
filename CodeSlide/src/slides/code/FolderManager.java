package slides.code;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FolderManager implements Iterable<String> {
	
	/* The folder manager stores everything as type File.
	 * Other windows store information as type String..
	 * 
	 * The conversion should be done directly to and from this class.
	 */
	
	private String folder;
	
	/*
	 * In the File class, there is no distinction between actual files and directories
	 */
	private List<File> files = new ArrayList<>();
	
	public static final String TEST_FOLDER = "src/slides/code";
	
//	public static void main(String[] args) {
//		System.out.println(Arrays.toString(new File(TEST_FOLDER).listFiles()));
//	}
	
	public FolderManager() {
		this(TEST_FOLDER);
	}
	
	public FolderManager(String folder) {
		if (folder == null) throw new NullPointerException("No folder specified");
		this.folder = folder;
		
		/* Imports the folder specified in folder and populates the set of files
		 * (for the files that are in the folder)  with it
		 */
		File directory = new File(folder);
		
		File[] folderFiles = directory.listFiles();
		System.out.println(folderFiles);
		System.out.println(folder);
		
		try {
		for (int i = 0; i < folderFiles.length; i++) {	
			// could use file filter for the purposes of filtering by file type/language...
			if (folderFiles[i].isDirectory()) continue;
			
			files.add(folderFiles[i]);
			// this loop really doesn't do anything other than filter out directories
		}
		} catch (NullPointerException e) {
			System.out.println("WWW");//TODO java bullshit
		}
	}
	
	private String readFile(File file) {
		try (FileReader fr = new FileReader(file)) {
			String contents = "";			
			int i;
			
		    while ((i = fr.read()) != -1) {
		    	contents += (char) i;
		    }
		    
		    return contents;		      
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			
			private final int limit = files.size() * 2;
			private int i = -1;

			@Override
			public boolean hasNext() {
				return i + 1 < limit;
			}

			@Override
			public String next() {
				try {
				if (++i % 2 == 0) {
					return (files.get(i / 2).getName());
				} else {
					return (readFile(files.get(i / 2)));
				}
				} catch (IndexOutOfBoundsException e) {
					return "e";//TODO bullshit
				}
			}
			
		};
	}

}
