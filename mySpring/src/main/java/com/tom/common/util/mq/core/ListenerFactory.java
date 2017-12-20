package com.tom.common.util.mq.core;

import com.tom.common.util.mq.util.ListenerStatus;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

public class ListenerFactory {
	private static ListenerFactory ListenerFactory = null;
	private Map<String,SimpleMessageListenerContainer> listenerMap = new HashMap<String,SimpleMessageListenerContainer>();
	
	public static ListenerFactory instantiation(){
		if(ListenerFactory == null){
			ListenerFactory = new ListenerFactory();
			return ListenerFactory;
		}else{
			return ListenerFactory;
		}
	}

    public ListenerStatus keepListener(ConnectionFactory connectionFactory, SimpleMessageListenerContainer listenerContainer) {
        String key = getListenerMapKey(connectionFactory, listenerContainer);
        Object obj = listenerMap.get(key);
		if(obj != null){
            return ListenerStatus.isExit;
        }else{
			listenerMap.put(key, listenerContainer);
            return ListenerStatus.success;
        }
	}

    public SimpleMessageListenerContainer getListener(String key) {
        return listenerMap.get(key);
	}

    public Map<String, SimpleMessageListenerContainer> getAllListeners() {
        if(listenerMap != null){
			return listenerMap;
		}
		return null;
	}

    public String getListenerMapKey(ConnectionFactory connectionFactory, SimpleMessageListenerContainer listenerContainer) {
        String host = connectionFactory.getHost();
		String[] queueNames = listenerContainer.getQueueNames();
		StringBuffer keySb = new StringBuffer();
		keySb.append(host);
		if(queueNames != null && queueNames.length > 0){
			for(String queueName : queueNames){
				keySb.append("[").append(queueName).append("]");
			}
		}
		return keySb.toString();
	}

    public String getListenerMapKey(ConnectionFactory connectionFactory, String queueName) {
        String host = connectionFactory.getHost();
		StringBuffer keySb = new StringBuffer();
		keySb.append(host);
		keySb.append("[").append(queueName).append("]");
		return keySb.toString();
	}
}
