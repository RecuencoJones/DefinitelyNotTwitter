package org.dn.twitter.config.ws;

import org.glassfish.jersey.media.sse.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by david on 30/04/2015.
 */
@Path("ws")
public class WebSocketProvider {
    
    private static ConcurrentHashMap<String,SseBroadcaster> listenerMap = new ConcurrentHashMap<>();
    private static WebSocketDispatcher websocketDispatcher = new WebSocketDispatcher();
    
    @Path("/sub/{id}")
    @GET
    public EventOutput subscribe(@PathParam("id") String id){
//        Client client = ClienbtBuilder.newBuilder().register(SseFeature.class).build();
        SseBroadcaster b = new SseBroadcaster();
        final EventOutput eventOutput = new EventOutput();
        b.add(eventOutput);
//        WebSocketBroadcaster b = new WebSocketBroadcaster();
        listenerMap.put(id,b);
        return eventOutput;
    }
    
    @Path("/unsub/{id}")
    @GET
    public String unsubscribe(@PathParam("id") String id){
        SseBroadcaster b = listenerMap.remove(id);
        b.closeAll();
        return "Unsubscribed from broker";
    }
    
    public static WebSocketDispatcher getWebsocketDispatcher() {
        return websocketDispatcher;
    }

    protected static ConcurrentHashMap<String, SseBroadcaster> getListenerMap() {
        return listenerMap;
    }
}
