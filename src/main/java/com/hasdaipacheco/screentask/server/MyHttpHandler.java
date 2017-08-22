/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hasdaipacheco.screentask.server;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 *
 * @author Ahmad
 */
public class MyHttpHandler implements HttpHandler {
	private String tempDir = System.getProperty("java.io.tmpdir");
	private HttpExchange _httpExchange;
	private OutputStream _outputStream;

	public void handle(HttpExchange httpExchange) throws IOException, MalformedURLException {
		_httpExchange = httpExchange;
		_outputStream = httpExchange.getResponseBody();

		String path = "";
		boolean isImage = false;
		boolean isFont = false;
		URI uri = httpExchange.getRequestURI();
		String uriPath = uri.getPath();
		
		if ("/".equals(uriPath)) // Route The Root Dir to the Index Page
		{
			path = "webserver/index.html";
		} else if ("/bootstrap.min.css".equals(uriPath)) {
			path = "webserver/css/bootstrap.min.css";
		} else if ("/fontawesome.min.css".equals(uriPath)) {
			path = "webserver/css/fontawesome.min.css";
		} else if (uriPath.contains("/ScreenTask.jpg")) {
			path = tempDir + "/ScreenTask/ScreenTask.jpg";
			isImage = true;
		} else if (uriPath.contains("/fonts/fontawesome-webfont")) {
			isFont = true;
			path = "webserver/fonts/fontawesome-webfont.";
			if (uriPath.contains(".woff")){
				path += "woff";
			} else if (uriPath.contains(".woff2")){
				path += "woff2";
			} if (uriPath.contains(".ttf")){
				path += "ttf";
			}
		} else {
			path = "";// uri.getPath().toString();
		}
		
		if (isFont) {
			URL resUrl = ClassLoader.getSystemClassLoader().getResource(path);
			if (null != resUrl) {
				InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
				byte[] bytes = IOUtils.toByteArray(is);
				httpExchange.sendResponseHeaders(200, bytes.length);
				_outputStream.write(bytes);
			}
		} else if (isImage) {
			File file = new File(path);
			if (file.exists()) {
				writeImage(file);
			}
		} else {
			URL resUrl = ClassLoader.getSystemClassLoader().getResource(path);
			if (null != resUrl) {
				InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
				StringWriter writer = new StringWriter();
				IOUtils.copy(is, writer);
				String content = writer.toString();

				httpExchange.sendResponseHeaders(200, content.length());
				_outputStream.write(content.getBytes());

			}
		}
		_outputStream.close();
	}

	void writeImage(File imageFile) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(imageFile);

		ByteArrayOutputStream bte = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", bte);
		byte[] imageBytes = bte.toByteArray();
		_httpExchange.sendResponseHeaders(200, imageBytes.length);
		_outputStream.write(imageBytes);
	}
}
