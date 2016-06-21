package test;

import java.io.File;
import java.util.ArrayList;

public class testSubFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String file = "Raws";
		ArrayList<String> fileName = getSubFile(file);
		
		System.out.println(fileName.size());
		for(String files : fileName)
			System.out.println(files);
	}

	public static ArrayList<String> getSubFile(String fileName) {   
		
		ArrayList<String> fileNames = new ArrayList<String>();
		
		File parentF = new File(fileName);   
		
		if (!parentF.exists()) {   
			System.out.println("unexisting file or directory");   
		    return null;   
		}   
		if (parentF.isFile()) {   
			System.out.println("it is a file");
			fileNames.add(parentF.getAbsolutePath());   
		    return fileNames;   
		}   
		
		System.out.println("it isn't  a file");
		String[] subFiles = parentF.list();   
		for (int i = 0; i < subFiles.length; i++) {   
			fileNames.add(parentF.getAbsolutePath() + "\\" + subFiles[i]); 
		}   
		
		return fileNames;
	}   
}
