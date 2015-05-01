package org.dn.twitter.config.ws;

import org.dn.twitter.model.Message;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;

import javax.ws.rs.core.MediaType;

/**
 * Created by david on 30/04/2015.
 */
public class WebSocketDispatcher {

    public void dispatch(Message message, String listener) {
        SseBroadcaster b = WebSocketProvider.getListenerMap().get(listener);
        if(b != null){
            System.out.println("To: "+listener+";; Who: "+message.getWho()+";; Text: "+message.getText());
//            b.broadcast(message);
            OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
            OutboundEvent event = eventBuilder.name("message")
                    .mediaType(MediaType.APPLICATION_JSON_TYPE)
                    .data(Message.class, message)
                    .build();

            b.broadcast(event);
        }
    }
}
