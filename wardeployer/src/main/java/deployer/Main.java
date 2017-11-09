package deployer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.maven.shared.invoker.MavenInvocationException;

import deployer.deploying.Deployer;
import deployer.serverdiscovering.ServerDiscoverer;
import deployer.serverdiscovering.pojos.TomcatServer;

public class Main {
	
	private final static List<String> GOALS = Arrays.asList("tomcat7:deploy-only");
	
	private final static boolean skipTest=true;
	
	public static void main(String[] args) {
		
		Deployer deployer = new Deployer(GOALS,skipTest);
		
		try(Scanner scan = new Scanner(System.in)){
			
			List<TomcatServer> tomcatServers = ServerDiscoverer.discoverTomcatServers();
			tomcatServers.forEach(server -> {
				try {
					
					System.out.println("Do you want to deploy to the tomcat ("+server.getName()+","+server.getIp()+")");
					System.out.println("Write \"YES\" for deploying or \"NO\" to the next server (Write \"EXIT\" for exit from program)");
					
					String answer = scan.next();
					
					if("exit".equalsIgnoreCase(answer)) System.exit(0);
					
					if(!"Y".equalsIgnoreCase(answer) && !"YES".equalsIgnoreCase(answer)) return;
					
					deployer.deployToThisServer(server);
					
				} catch (MavenInvocationException e) {
					e.printStackTrace();
				}
			});
		};
		
	}
	
}
