package be.dieterdemeyer.moviedb.jetty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.plus.naming.Resource;
import org.mortbay.jetty.webapp.WebAppContext;


public class JettyServer {

    private static final String LOCALHOST = "127.0.0.1";

    static Server server;

    private int port;

    public JettyServer(int port, DataSource dataSource) {
        this.port = port;
        server = new Server(port);
        server.setHandler(createWebAppContext());
        server.setStopAtShutdown(true);

        createDataSourceResource(dataSource);
    }

    private void createDataSourceResource(DataSource dataSource) {
        try {
            new Resource("java:comp/env/jdbc/MovieDBDS", dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private WebAppContext createWebAppContext() {
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setWar("src/main/webapp");
        return webAppContext;
    }

    public void start(boolean join) {
        try {
        	System.out.println(port);
            new MonitorThread(port).start();
            server.start();

            if (join) {
                server.join();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        start(false);
    }

    public void stop() {
    	stop(port);
    }
    
    public static void stop(int port) {
        try {
            Socket s = new Socket(InetAddress.getByName(LOCALHOST), port);
            OutputStream out = s.getOutputStream();
            out.write(("\r\n").getBytes());
            out.flush();
            s.close();
        } catch (Exception e) {
        }
    }


    public static void main(String[] args) {
        JettyServer.stop(8090);
        new JettyServer(8080, createDataSource()).start(true);
    }

    private static BasicDataSource createDataSource() {
        BasicDataSource dataSource;
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:hsql://localhost/moviedb_web");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    private static class MonitorThread extends Thread {

        private ServerSocket socket;

        public MonitorThread(int port) {
            setDaemon(true);
            setName("StopMonitor");
            try {
                socket = new ServerSocket(port + 10, 1, InetAddress.getByName(LOCALHOST));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            try {
                Socket accept = socket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                reader.readLine();
                server.stop();
                accept.close();
                socket.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}