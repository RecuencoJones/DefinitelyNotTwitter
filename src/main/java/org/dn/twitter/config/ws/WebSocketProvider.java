package org.dn.twitter.config.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by david on 30/04/2015.
 */
@Path("ws")
public class WebSocketProvider {
    
    private static ConcurrentHashMap<String,WebSocketBroadcaster> listenerMap = new ConcurrentHashMap<>();
    private static WebSocketDispatcher websocketDispatcher = new WebSocketDispatcher();
    
    @Path("/sub/{id}")
    @GET
    public WebSocketBroadcaster subscribe(@PathParam("id") String id){
        WebSocketBroadcaster b = new WebSocketBroadcaster();
        listenerMap.put(id,b);
        return b;
    }

    public static WebSocketDispatcher getWebsocketDispatcher() {
        return websocketDispatcher;
    }

    protected static ConcurrentHashMap<String, WebSocketBroadcaster> getListenerMap() {
        return listenerMap;
    }
}
