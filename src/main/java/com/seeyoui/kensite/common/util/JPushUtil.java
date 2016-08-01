package com.seeyoui.kensite.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.examples.PushExample;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtil {
    protected static final Logger LOG = LoggerFactory.getLogger(PushExample.class);

    // demo App defined in resources/jpush-api.conf 
	private static final String appKey ="9709d31e573175630cbdf74a";
	private static final String masterSecret = "4550aec589d5e6b11df8ef1d";
	
	public static void main(String[] args) {
		try {
			sendNotificationPush("标题", "{\"num\":123}", "xxx");
//			sendMessagePush("标题", "{\"num\":123}", "xxx");
		} catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        } catch (Exception e) {
        	LOG.info("Msg: " + e.getMessage());
        }
	}
	
	public static String sendNotificationPush(String title, String alert, String... alias) throws APIRequestException, Exception {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
        PushPayload payload = sendNotification(title, alert, alias);
        PushResult result = jpushClient.sendPush(payload);
        return result.toString();
	}
	
	public static String sendMessagePush(String title, String alert, String... alias) throws APIRequestException, Exception {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
        PushPayload payload = sendMessage(title, alert, alias);
        PushResult result = jpushClient.sendPush(payload);
        return result.toString();
	}
    
    public static PushPayload sendNotification(String title, String alert, String... alias) {
    	if(alias == null || alias.length == 0) {
    		return PushPayload.newBuilder()
                    .setPlatform(Platform.all())
                    .setAudience(Audience.all())
                    .setNotification(Notification.newBuilder()
                    		.setAlert(alert)
                    		.addPlatformNotification(AndroidNotification.newBuilder()
                    				.setTitle(title).build())
                    		.addPlatformNotification(IosNotification.newBuilder()
                    				.incrBadge(1)
                    				.addExtra("title", title).build())
                    		.build())
                    .build();
    	}
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.alias(alias))
                        .build())
                .setNotification(Notification.newBuilder()
                		.setAlert(alert)
                		.addPlatformNotification(AndroidNotification.newBuilder()
                				.setTitle(title).build())
                		.addPlatformNotification(IosNotification.newBuilder()
                				.incrBadge(1)
                				.addExtra("title", title).build())
                		.build())
                .build();
    }
    
    public static PushPayload sendMessage(String title, String message, String... alias) {
    	if(alias == null || alias.length == 0) {
    		return PushPayload.newBuilder()
                    .setPlatform(Platform.all())
                    .setAudience(Audience.all())
                    .setMessage(Message.newBuilder()
                            .setMsgContent(message)
                            .addExtra("title", title)
                            .addExtra("message", message)
                            .build())
                    .build();
    	}
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.alias(alias))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(message)
                        .addExtra("title", title)
                        .addExtra("message", message)
                        .build())
                .build();
    }
    
}

