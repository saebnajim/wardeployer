package deployer.serverdiscovering;

import java.util.List;

import deployer.serverdiscovering.pojos.TomcatServer;

public class ServerDiscoverer {
	
	public static List<TomcatServer> discoverTomcatServers() {
		return SearchTomcatServers.searchTomcatServers();
	}

}
