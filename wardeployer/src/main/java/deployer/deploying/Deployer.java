package deployer.deploying;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import deployer.help.Constants;
import deployer.serverdiscovering.pojos.TomcatServer;

@SuppressWarnings("unused")
public class Deployer {
	
	private  List<String> goals ;
	private  boolean skipTest;
	
	private Deployer() { }
	
	public Deployer(List<String> goals,boolean skipTest){
		this.goals=goals;
		this.skipTest=skipTest;
	}
	
	public void deployToThisServer(TomcatServer server) throws MavenInvocationException {
		
		List<String> goalsL = new ArrayList<>(goals);
		String mavenParameters = String.format("-Dmaven.test.skip=%b -Dparam.from.cmd.tomcat.url=%s -Dparam.from.cmd.tomcat.userName=%s -Dparam.from.cmd.tomcat.password=%s -Dparam.from.cmd.tomcat.path=%s -Dparam.from.cmd.tomcat.warDir=%s" ,
												skipTest,
												server.getUrl(),
												server.getUserName(),
												server.getPassword(),
												server.getWarPathUnderServer(),
												server.getWarDir().trim());
		goalsL.add(mavenParameters);
		
		//Set maven home 
		System.setProperty("maven.home", Constants.MAVEN_HOME);
		
		//user.dir is the project directory (user.dir+/+pom.xml=pom file path)
		String pomName = "pom.xml";
		
		StringBuffer pomPath = new StringBuffer();
		pomPath.append(Constants.userDir);
		pomPath.append(Constants.seperator);
		pomPath.append(pomName);
		
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File(pomPath.toString()));
		request.setGoals(goalsL);
		 
		Invoker invoker = new DefaultInvoker();
		InvocationResult result = invoker.execute( request );
		
		if(result.getExitCode() != 0){
			throw new MavenInvocationException( "Build failed." );
		}

	}
}
