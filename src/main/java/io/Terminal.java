package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public final class Terminal {
	
	private Terminal() {}

	public static void printError(String message) {
		System.out.println("ERROR: " + message);
	}
	
	public static void printLine(String message) {
		System.out.println(message);
	}
	
	public static String readLine() throws IOException{
		String line = "";
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(isr);
		line = reader.readLine();
		return line;
	}

}
