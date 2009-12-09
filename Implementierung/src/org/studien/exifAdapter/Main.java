package org.studien.exifAdapter;

import java.io.File;
import java.util.ArrayList;

import org.studien.exifAdapter.jexifviewer.ExifAdapter;

public class Main {

	private static ArrayList<ExifAdapter> listOfFiles = new ArrayList<ExifAdapter>();;
	private static int files = 0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File root = new File(args[0]);
		
		long zstVorher;
		long zstNachher;
		
		zstVorher = System.currentTimeMillis();

		//Den Verzeichnisbaum recursive traversieren...
 		for(int i=0;i<Integer.parseInt(args[1]);++i) {
 			treeWalk(root);	
 		}		
		
		zstNachher = System.currentTimeMillis();
		
		System.out.println("Anzahl Dateien: " + files);
		System.out.println("Zeit ben�tigt: " + ((zstNachher - zstVorher)) + " milisec");
		System.out.println("Zeit ben�tigt: " + ((zstNachher - zstVorher)/1000) + " sec");
	}
	
	static void treeWalk(File file) {
 		if (file.isDirectory()) {
 			System.out.println("Verzeichnis: " + file.getAbsolutePath());
 			File[] children = file.listFiles();
 			for (int i = 0; i < children.length; i++) {
 				treeWalk(children[i]);
 			}
 		} else {
 			++files;
 			listOfFiles.add(new ExifAdapter(file.getAbsolutePath()));
 		}
 	}
}