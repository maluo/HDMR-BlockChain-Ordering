package HDMRORDERS.HADOOP.OPERATIONS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import HDMRORDERS.HADOOP.PROCESSING.CSVReader;
import HDMRORDERS.HADOOP.PROCESSING.GLOBAL;

/**
 * Simple Driver to read/write to hdfs
 * 
 * @author ashrith
 *
 */
public class ECHDFSOperations {

	public ECHDFSOperations() {
	}

	/**
	 * create a existing file from local filesystem to hdfs
	 * 
	 * @param source
	 * @param dest
	 * @param conf
	 * @throws IOException
	 */
	public void addFile(String source, String dest, Configuration conf) throws IOException {
		FileSystem fileSystem = FileSystem.get(conf);
		// Get the filename out of the file path
		String filename = source.substring(source.lastIndexOf(GLOBAL.FilePathBreak) + 1, source.length());
		// Create the destination path including the filename.
		if (dest.charAt(dest.length() - 1) != '/') {
			dest = dest + "/" + filename;
		} else {
			dest = dest + filename;
		}
		// System.out.println("Adding file to " + destination);
		// Check if the file already exists
		Path path = new Path(dest);
		if (fileSystem.exists(path)) {
			System.out.println("File " + dest + " already exists");
		}
		else {
			// Create a new file and write data to it.
			FSDataOutputStream out = fileSystem.create(path);
			InputStream in = new BufferedInputStream(new FileInputStream(new File(source)));
			byte[] b = new byte[1024];
			int numBytes = 0;
			while ((numBytes = in.read(b)) > 0) {
				out.write(b, 0, numBytes);
			}
			// Close all the file descriptors
			in.close();
			out.close();
			fileSystem.close();
		}
	}

	/**
	 * read a file from hdfs
	 * 
	 * @param file
	 * @param conf
	 * @throws IOException
	 */
	public void readFile(String file, Configuration conf) throws IOException {
		FileSystem fileSystem = FileSystem.get(conf);

		Path path = new Path(file);
		if (!fileSystem.exists(path)) {
			System.out.println("File " + file + " does not exists");
			return;
		} else {
			FSDataInputStream in = fileSystem.open(path);
			String filename = file.substring(file.lastIndexOf('/') + 1, file.length());
			OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filename)));
			byte[] b = new byte[1024];
			int numBytes = 0;
			while ((numBytes = in.read(b)) > 0) {
				out.write(b, 0, numBytes);
			}
			in.close();
			out.close();
			fileSystem.close();
		}
	}

	/**
	 * delete a directory in hdfs
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void deleteFile(String file, Configuration conf) throws IOException {
		FileSystem fileSystem = FileSystem.get(conf);
		Path path = new Path(file);
		if (!fileSystem.exists(path)) {
			System.out.println("File " + file + " does not exists");
		} else {
			fileSystem.delete(path, true);
			fileSystem.close();
		}
	}

	/**
	 * create directory in hdfs
	 * 
	 * @param dir
	 * @throws IOException
	 */
	public void mkdir(String dir, Configuration conf) throws IOException {
		FileSystem fileSystem = FileSystem.get(conf);
		Path path = new Path(dir);
		if (fileSystem.exists(path)) {
			System.out.println("Dir " + dir + " already not exists");
		} else {
			fileSystem.mkdirs(path);
			fileSystem.close();
		}
	}

	public static void main(String[] args) throws IOException {

		//CSVReader reader = new CSVReader();
		//reader.ReadPairsStaging();//tested
		
		ECHDFSOperations client = new ECHDFSOperations();
		Configuration conf = new Configuration();

		String hdfsPath = "hdfs://localhost:19000";
		conf.set("fs.defaultFS", hdfsPath);

	    client.addFile(GLOBAL.TESTOUTPath, "/ec", conf);

		//client.readFile("/ec/wordcount.txt", conf);
		//client.deleteFile("/ec/test.txt", conf);
		
		System.out.println("Done!");
	}
}
