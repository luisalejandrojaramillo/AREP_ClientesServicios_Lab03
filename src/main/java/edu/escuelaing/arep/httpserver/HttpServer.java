package edu.escuelaing.arep.httpserver;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class HttpServer {
	private Map<String, Function> rutas = new HashMap<String,Function>();
	//private MongoConnection mongo;
	public HttpServer() throws UnknownHostException {
		//mongo = new MongoConnection();
	}
	public int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 36000;
	}

	public void start() throws IOException {

		int puerto=this.getPort();
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(puerto);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 36000.");
			System.exit(1);
		}
		while(true){
			Socket clientSocket = null;
			try {
				System.out.println("Listo para recibir ...");
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("Error de Conexion :(");
				System.exit(1);
			}
			processRequest(clientSocket);
			clientSocket.close();
		}
	}
	private void processRequest(Socket clientSocket) throws IOException {
		BufferedReader in = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));
		String inputLine;
		Map<String, String> request = new HashMap<>();
		boolean requestLineReady = false;
		while ((inputLine = in.readLine()) != null) {
			System.out.println("Info: " + inputLine);
			if (!requestLineReady) {
				request.put("requestLine", inputLine);
				requestLineReady = true;
			} else {
				String[] entry = createEntry(inputLine);
				if (entry.length > 1) {
					request.put(entry[0], entry[1]);
				}
			}
			if (!in.ready()) {
				break;
			}
		}
		if(request.get("requestLine") != null) {
			System.out.println("esto          "+request.get("requestLine"));

			Request req = new Request(request.get("requestLine"));

			System.out.println("RequestLine: " + req);

			createResponse(req, new PrintWriter(
					clientSocket.getOutputStream(), true),clientSocket);
		}
		in.close();
	}

	private String[] createEntry(String rawEntry) {
		return rawEntry.split(":");
	}
	private String getFormat(Request req) {
		URI theuri = req.getTheuri();
		String formato;
		if((theuri.getPath()).equals("/favicon.ico")) {
			formato = "ico";
		}
		else {
			String[] compoPath = (theuri.getPath()).split("\\.");
			formato =  compoPath[1];
		}
		return formato;
	}

	private void createResponse(Request req, PrintWriter out,Socket clientSocket) {
		String outputLine = testResponse();
		URI theuri = req.getTheuri();
		if((theuri.getPath()).equals("/")) {
			getStaticResource("html","/html.html",out);
		}
		else if(theuri.getPath().startsWith("/Apps") &&  !(theuri.getPath().contains(".js")) ) {
			try{
				String[] compoPath = (theuri.getPath()).split("/");
				System.out.println("File name "+compoPath[2]);
				out.print(rutas.get("/"+compoPath[2]).apply(req));
			}catch (Exception e) {
				notFound(out);
			}
		}
		else if (theuri.getPath().contains("PNG") || theuri.getPath().contains("JPG")  || theuri.getPath().contains("JPEG")  || theuri.getPath().contains("png") || theuri.getPath().contains("jpg") || theuri.getPath().contains("jpeg")) {
			String formato = getFormat(req);
			getStaticImagen(formato,theuri,clientSocket,out);

		} else {
			String formato = getFormat(req);
			getStaticResource(formato,theuri.getPath(), out);
		}
		out.close();
	}

	private String testResponse() {
		String outputLine = "HTTP/1.1 200 OK\r\n"
				+ "Content-Type: text/html\r\n"
				+ "\r\n"
				+ "<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head>\n"
				+ "<meta charset=\"UTF-8\">\n"
				+ "<title>Title of the document</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "<h1>Test Test Test</h1>\n"
				+ "</body>\n"
				+ "</html>\n";
		return outputLine;
	}
	private void notFound(PrintWriter out) {

		String Error="HTTP/1.1 200 OK\r\n"
				+ "Content-Type: text/html\r\n"
				+ "\r\n"
				+ "<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head>\n"
				+ "<meta charset=\"UTF-8\">\n"
				+ "<title>Title of the document</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "<h1>404 Not Found</h1>\n"
				+ "</body>\n"
				+ "</html>\n";
		out.print(Error);

	}
	private void getStaticImagen(String type,URI theuri,Socket clientSocket,PrintWriter out) {
		File dirImg = new File("src/main/resources/"+theuri.getPath());
		System.out.println("--- Esta imagen es de tipo --- : " + type);
		BufferedImage image;
		try {
			image = ImageIO.read(dirImg);
			ByteArrayOutputStream bytesArray = new ByteArrayOutputStream();
			DataOutputStream writeimg = new DataOutputStream(clientSocket.getOutputStream());
			ImageIO.write(image, type, bytesArray);
			writeimg.writeBytes("HTTP/1.1 200 OK \r\n" + "Content-Type: image/"+type+" \r\n" + "\r\n");
			writeimg.write(bytesArray.toByteArray());
		} catch (IOException e) {
			notFound(out);
		}
	}
	private void getStaticResource(String type,String path, PrintWriter out) {
		System.out.println("Type RequestLine: " + type+path);

		if(path.contains("/Apps")) {
			path=path.replace("/Apps","");
		}

		Path file = Paths.get("src/main/resources/" + path);
		try (InputStream in = Files.newInputStream(file);
			 BufferedReader reader
					 = new BufferedReader(new InputStreamReader(in))) {
			String header = "HTTP/1.1 200 OK\r\n"
					+ "Content-Type: text/"+"html"+"\r\n"
					+ "\r\n";
			out.println(header);
			String line = null;
			while ((line = reader.readLine()) != null) {
				out.println(line);
				System.out.println(line);
			}
		} catch (IOException ex) {
			notFound(out);
		}
	}

	public void get(String endPoint,Function<Request,String>  res) {
		System.out.println("Ruta "+endPoint);
		rutas.put(endPoint , res);
	}



}
