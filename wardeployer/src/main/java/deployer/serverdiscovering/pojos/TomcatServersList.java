package deployer.serverdiscovering.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="servers")
public class TomcatServersList {
	
	private String userName;
	private String password;
	private String warPathUnderServer;
	private String warDir;
	
	private List<TomcatServer> tomcatServers;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWarPathUnderServer() {
		return warPathUnderServer;
	}

	public void setWarPathUnderServer(String warPathUnderServer) {
		this.warPathUnderServer = warPathUnderServer;
	}

	public String getWarDir() {
		return warDir;
	}

	public void setWarDir(String warDir) {
		this.warDir = warDir;
	}

	@XmlElement(name="server")
	public List<TomcatServer> getTomcatServers() {
		return tomcatServers;
	}

	public void setTomcatServers(List<TomcatServer> tomcatServers) {
		this.tomcatServers = tomcatServers;
	}

}
