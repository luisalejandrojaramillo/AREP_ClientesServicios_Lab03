package edu.escuelaing.arep.urlreader;

import java.net.MalformedURLException;
import java.net.URL;

public class scanURL {

    public static void main (String args){
        scanURL("http://ldbn.escuelaing.edu.co/index.html%22");
        scanURL("http://ldbn.escuelaing.edu.co:80/foto.png?name=user&color=red#name%22");
    }
    public static void scanURL(String siteurl){
        try{
            URL site = new URL(siteurl);
            System.out.println(site);
            System.out.println("------------");
            System.out.println("Protocol: "+site.getProtocol());
            System.out.println("Host: "+site.getHost());
            System.out.println("Port: "+site.getPort());
            System.out.println("Path : " + site.getPath());
            System.out.println("Query : " + site.getFile());
            System.out.println("File : " + site.getFile());
            System.out.println("Ref : " + site.getRef());
            System.out.println("------------");
            System.out.println("------------");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
