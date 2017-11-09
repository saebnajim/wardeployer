package deployer.serverdiscovering;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import deployer.help.Constants;
import deployer.serverdiscovering.pojos.TomcatServer;
import deployer.serverdiscovering.pojos.TomcatServersList;


/**
 *  JAXB2.x (which comes with JDK6) make marshalling Java to XML and unmarshalling XML to Java a snap, almost trivial.
 * @author snajim
 *
 */
public class SearchTomcatServers {


	protected static List<TomcatServer> searchTomcatServers() {

		try {

			StringBuffer tomcatServersConfigXmlPath = new StringBuffer();
			tomcatServersConfigXmlPath.append(Constants.userDir);
			tomcatServersConfigXmlPath.append(Constants.seperator);
			tomcatServersConfigXmlPath.append(Constants.tomcatServersConfigXml);
			
			File file = new File(tomcatServersConfigXmlPath.toString());
			
			JAXBContext jaxbContext = JAXBContext.newInstance(TomcatServersList.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TomcatServersList tomcatServers = (TomcatServersList) jaxbUnmarshaller.unmarshal(file);

			final String globalUserName = tomcatServers.getUserName();
			final String globalPassowrd = tomcatServers.getPassword();
			final String globalWarDir = tomcatServers.getWarDir();
			final String globalWarPathUnderServer = tomcatServers.getWarPathUnderServer();

			List<TomcatServer> servers = tomcatServers.getTomcatServers();
			for (TomcatServer server : servers) {
				if (server.getUserName() == null) {
					server.setUserName(globalUserName);
				}
				if (server.getPassword() == null) {
					server.setPassword(globalPassowrd);
				}
				if (server.getWarDir() == null) {
					server.setWarDir(globalWarDir);
				}
				if (server.getWarPathUnderServer() == null) {
					server.setWarPathUnderServer(globalWarPathUnderServer);
				}
			}
			return servers;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}