package org.dn.twitter.config;

import org.dn.twitter.config.rest.RestAPI;
import org.dn.twitter.config.ws.WebSocketProvider;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by david on 30/04/2015.
 */
public class AppConfig extends ResourceConfig {

    public AppConfig() {
        register(CrossDomainFilter.class);
        register(MOXyJsonProvider.class);
        register(RestAPI.class);
        register(WebSocketProvider.class);
    }
    
}
