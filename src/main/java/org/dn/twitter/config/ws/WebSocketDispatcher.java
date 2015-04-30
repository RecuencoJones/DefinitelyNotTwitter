package org.dn.twitter.config.ws;

import org.dn.twitter.model.Message;

/**
 * Created by david on 30/04/2015.
 */
public class WebSocketDispatcher {

    public void dispatch(Message message, String listener) {
        WebSocketBroadcaster b = WebSocketProvider.getListenerMap().get(listener);
        if(b != null){
            System.out.println("To: "+listener+";; Who: "+message.getWho()+";; Text: "+message.getText());
            b.broadcast(message);
        }
    }
}
