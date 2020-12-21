package HDMRORDERS.HADOOP.OPERATIONS;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ECHDSysCommand {

	public static void runCommand(String cmd) {
		try {
			Process process = Runtime.getRuntime().exec(cmd);

			// Get input streams
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			// Read command standard output
			String s;
			System.out.println("Standard output: ");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}
			// Read command errors
			System.out.println("Standard error: ");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	public static void main(String args[]) {
		String cmd0 = "cmd /c hadoop fs -rm -r /output";
		String cmd1 = "cmd /c hadoop jar C:/enterprise/ec-hadoop/ec-hdmr-wc.jar ec.lab.WordCount /ec/wordcount.txt /output";
		String cmd2 = "cmd /c hadoop fs -ls /output";
		String cmd3 = "cmd /c hadoop fs -cat /output/part-r-00000";
		runCommand(cmd0);
		runCommand(cmd1);
		runCommand(cmd2);
		runCommand(cmd3);
		System.exit(0);
		
	}
}

