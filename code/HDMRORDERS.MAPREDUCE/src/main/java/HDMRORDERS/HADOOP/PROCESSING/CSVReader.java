package HDMRORDERS.HADOOP.PROCESSING;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVReader {

	public CSVReader() {

	}

	public void ReadPairsStaging() {
		String lineText = null;
		BufferedReader lineReader = null;
		FileWriter csvWriter = null;
		String[] data = null;
		String buf = null;
		try {
			lineReader = new BufferedReader(new FileReader(GLOBAL.CSVInPath));
			csvWriter = new FileWriter(GLOBAL.CSVOUTPath);

			lineReader.readLine(); // skip header line

			while ((lineText = lineReader.readLine()) != null) {
				data = lineText.split(",");
				buf = data[0] + "," + data[1] + "," + data[2] + "," + data[3];
				csvWriter.append(String.join(",", buf));
				csvWriter.append("\n");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				lineReader.close();
				csvWriter.flush();
				csvWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
