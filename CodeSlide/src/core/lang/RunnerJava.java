package core.lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunnerJava implements LangRunner {

	@Override
	public void run() {
		try {
			Process process = Runtime.getRuntime().exec("java src.slides.code.test.HelloWorld.class");
			printLines(process.getInputStream());
			printLines(process.getErrorStream());
			System.out.println("Running!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printLines(InputStream ins) {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		
		try {
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
