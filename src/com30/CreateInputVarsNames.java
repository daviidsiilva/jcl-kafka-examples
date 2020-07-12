package com30;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class CreateInputVarsNames {
	public static void main(String[] args) {
		Writer writer = null;
		Random rand = new Random();
		Integer num = null;

		try {
			writer = new FileWriter(Constants.FILE_PATH_NAME);
			for(int i = 0 ; i < Runtime.getRuntime().availableProcessors()*Constants.SIZE; i++) {
				System.out.println(i);
				num = rand.nextInt(Constants.N_VARS);
				writer.write(num.toString() + System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
