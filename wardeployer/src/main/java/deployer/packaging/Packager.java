package deployer.packaging;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;

public class Packager {
	
	private final static String MAVEN_HOME = "C:/Program Files/apache-maven-3.0.3";
	private final static String PROFILE = "dev";
	private final static List<String> GOALS = Arrays.asList("package");
	
	private final static boolean skipTest=true;
	
	public static void main(String[] args) {
		try {
			 
			List<String> goalsL = new ArrayList<>(Packager.GOALS);
			String mavenParameters = String.format("-Dmaven.test.skip=%b" , Packager.skipTest);
			goalsL.add(mavenParameters);
			
			//Set maven home 
			System.setProperty("maven.home", Packager.MAVEN_HOME);
			
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile(new File("C:/java/eclipse_Neon_workspace12/nextStep/pom.xml"));
			request.setGoals(goalsL);
			request.setProfiles(Collections.singletonList(Packager.PROFILE));
			
			Invoker invoker = new DefaultInvoker();
			InvocationResult result = invoker.execute( request );
			
			if(result.getExitCode() != 0){
				throw new IllegalStateException( "Build failed." );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
