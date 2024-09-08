package api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	Properties prop;

	public String getPostUrl() throws IOException {

		FileInputStream fin = new FileInputStream(".\\src\\test\\resources\\routes.properties");

		prop = new Properties();
		prop.load(fin);

		String posturl = prop.getProperty("post_url");

		return posturl;

	}

	public String getGetUrl() throws IOException {

		FileInputStream fin = new FileInputStream(".\\src\\test\\resources\\routes.properties");

		prop = new Properties();
		prop.load(fin);

		String geturl = prop.getProperty("get_url");

		return geturl;

	}

	public String getPutUrl() throws IOException {

		FileInputStream fin = new FileInputStream(".\\src\\test\\resources\\routes.properties");

		prop = new Properties();
		prop.load(fin);

		String puturl = prop.getProperty("put_url");

		return puturl;

	}
	
	public String getDeleteUrl() throws IOException {

		FileInputStream fin = new FileInputStream(".\\src\\test\\resources\\routes.properties");

		prop = new Properties();
		prop.load(fin);

		String deleteurl = prop.getProperty("delete_url");

		return deleteurl;

	}
}
