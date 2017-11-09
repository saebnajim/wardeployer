package deployer.serverdiscovering.pojos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="server")
public class TomcatServer {
	
	private String name;
	private String ip;
	private String url;
	private String userName;
	private String password;
	private String warPathUnderServer;
	private String warDir;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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

}
