package com.sehal.util.parser;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import jakarta.ejb.Stateless;

@Stateless
public class ParsCSV {

	 public File openFile(String file) {
	 String filString =
	 "/home/reader-01/JAVA/payara6/glassfish/domains/domain1/generated/jsp/WebApp"
	 + File.separator + file;
	 File inputFile = new File(filString);
	 return inputFile;
	 }

	public String readTitle(String file) {
		String titleString = null;
		String fileString = "/home/reader-01/JAVA/payara6/glassfish/domains/domain1/generated/jsp/WebApp"
				+ File.separator + file;
		File inputFile = new File(fileString);
		try (Scanner myReader = new Scanner(inputFile)) {
			titleString = myReader.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return titleString;
	}

	public List<String> readFile(String file) {
		List<String> fileParsed = new ArrayList<>();
		String filString = "/home/reader-01/JAVA/payara6/glassfish/domains/domain1/generated/jsp/WebApp"
				+ File.separator + file;
		File inputFile = new File(filString);

		try (Scanner myReader = new Scanner(inputFile)) {
			while (myReader.hasNext()) {
				String string = myReader.nextLine();
				fileParsed.add(string);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("FILE WAS READ! List size: " + fileParsed.size());
		return fileParsed;

	}

}
