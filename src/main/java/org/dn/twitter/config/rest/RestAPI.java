package org.dn.twitter.config.rest;

import com.google.gson.Gson;
import org.dn.twitter.config.ws.WebSocketDispatcher;
import org.dn.twitter.config.ws.WebSocketProvider;
import org.dn.twitter.model.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Created by david on 30/04/2015.
 */
@Path("/rest")
public class RestAPI {
    
    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        return "Hello world!";
    }
    
    @Path("/post")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String post(@Context UriInfo info, Message message){
        WebSocketDispatcher wsd = WebSocketProvider.getWebsocketDispatcher();
        Message nm = new Message(message.getWho(),message.getText());
        for(String s : message.getTo().split(",")){
            wsd.dispatch(nm,s);
        }
        System.out.println("======================");
        return new Gson().toJson(nm);
    }
}
