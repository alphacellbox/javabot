package javabot;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.github.badoualy.telegram.api.Kotlogram;
import com.github.badoualy.telegram.api.TelegramApp;
import com.github.badoualy.telegram.api.TelegramClient;
import com.github.badoualy.telegram.api.UpdateCallback;
import com.github.badoualy.telegram.mtproto.model.DataCenter;
import com.github.badoualy.telegram.tl.api.TLAbsChatInvite;
import com.github.badoualy.telegram.tl.api.TLAbsInputPeer;
import com.github.badoualy.telegram.tl.api.TLAbsPeer;
import com.github.badoualy.telegram.tl.api.TLAbsUpdates;
import com.github.badoualy.telegram.tl.api.TLChannel;
import com.github.badoualy.telegram.tl.api.TLChat;
import com.github.badoualy.telegram.tl.api.TLInputPeerChannel;
import com.github.badoualy.telegram.tl.api.TLInputPeerChat;
import com.github.badoualy.telegram.tl.api.TLInputPeerEmpty;
import com.github.badoualy.telegram.tl.api.TLInputPeerUser;
import com.github.badoualy.telegram.tl.api.TLPeerChannel;
import com.github.badoualy.telegram.tl.api.TLPeerChat;
import com.github.badoualy.telegram.tl.api.TLPeerUser;
import com.github.badoualy.telegram.tl.api.TLUpdateShort;
import com.github.badoualy.telegram.tl.api.TLUpdateShortChatMessage;
import com.github.badoualy.telegram.tl.api.TLUpdateShortMessage;
import com.github.badoualy.telegram.tl.api.TLUpdateShortSentMessage;
import com.github.badoualy.telegram.tl.api.TLUpdates;
import com.github.badoualy.telegram.tl.api.TLUpdatesCombined;
import com.github.badoualy.telegram.tl.api.TLUser;
import com.github.badoualy.telegram.tl.api.auth.TLAuthorization;
import com.github.badoualy.telegram.tl.api.auth.TLSentCode;
import com.github.badoualy.telegram.tl.api.messages.TLAbsDialogs;
import com.github.badoualy.telegram.tl.core.TLObject;
import com.github.badoualy.telegram.tl.exception.RpcErrorException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

public class client extends Thread {
	                                
	public static final int API_ID =   8384841  ;
	public static final String API_HASH = "341bc352c7e22649684c28e9055c9f6b";
	

	// What you want to appear in the "all sessions" screen
	public static final String APP_VERSION = "Android";
	public static final String MODEL = "Model";
	public static final String SYSTEM_VERSION = "11.0";
	public static final String LANG_CODE = "en";
	public static TelegramClient client;
	public static final String PHONE_NUMBER = "+989029819443"; // International format
	public static TelegramApp application = new TelegramApp(API_ID, API_HASH, MODEL, SYSTEM_VERSION, APP_VERSION,
			LANG_CODE);

	// public static TelegramApp application = new TelegramApp(API_ID, API_HASH,
	// MODEL, SYSTEM_VERSION, APP_VERSION, LANG_CODE);
	public static int test(String a, ArrayList<Integer> user_number, String matn) throws InterruptedException {
     // DataCenter prodDC = new DataCenter("149.154.167.50", 443);
	       DataCenter prodDC = new DataCenter("149.154.167.50", 443);
     //  DataCenter prodDC = new DataCenter("149.154.175.100", 443);
//        DataCenter prodDC = new DataCenter("149.154.175.50", 443);
//        DataCenter prodDC = new DataCenter("149.154.167.91", 443);
        //DataCenter prodDC = new DataCenter("149.154.171.5", 443);
		//  client = Kotlogram.getDefaultClient(application, new ApiStorage(),null,prodDC4);

		client = Kotlogram.getDefaultClient(application, new ApiStorage(), prodDC, new UpdateCallback() {

			@Override
			public void onShortChatMessage(TelegramClient arg0, TLUpdateShortChatMessage arg1) {

			}

			@Override
			public void onShortMessage(TelegramClient arg0, TLUpdateShortMessage arg1) {

			
			}

			@Override
			public void onShortSentMessage(TelegramClient arg0, TLUpdateShortSentMessage arg1) {

			}

			@Override
			public void onUpdateShort(TelegramClient arg0, TLUpdateShort arg1) {
				
			}

			@Override
			public void onUpdateTooLong(TelegramClient arg0) {
				
			}

			@Override
			public void onUpdates(TelegramClient arg0, TLUpdates arg1) {
			

			}

			@Override
			public void onUpdatesCombined(TelegramClient arg0, TLUpdatesCombined arg1) {

			}
         
		});
		MongoCollection<Document> gradesCollection2 = javabot.App.database.getCollection("Enterd?");

		Document student2 = gradesCollection2.find(new Document("What", "")).first();
		if(student2!=null) {
		try {
		    // Send code to account
		    TLSentCode sentCode = client.authSendCode(false, PHONE_NUMBER, true);
		    System.out.println("Authentication code: ");
		    try {
				Thread.currentThread();
				Thread.sleep(60000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    MongoCollection<Document> gradesCollection3 = javabot.App.database.getCollection("Enterd?");

			Document student3 = gradesCollection2.find(new Document("in", "yes")).first();
			System.out.println(student3.getString("What"));
		    String code = student3.getString("What");
		    

		    // Auth with the received code
		    TLAuthorization authorization = client.authSignIn(PHONE_NUMBER, sentCode.getPhoneCodeHash(), code);
		   
		    TLUser self = authorization.getUser().getAsUser();
		    System.out.println("You are now signed in as " + self.getFirstName() + " " + self.getLastName() + " @" + self.getUsername());
		    Bson filter = eq("What", "");
			Bson updateOperation = set("What", "yes");

			UpdateResult updateResult = gradesCollection2.updateOne(filter, updateOperation);
			System.out.println(updateResult.toString());
		} catch (RpcErrorException | IOException e) {
		    e.printStackTrace();
		    System.out.println("cathhhhhh ");
		}}
		else {
			 System.out.println("neahhhhhhhhhhhhh ");
		}
		
		if (!a.equals("")) {
			try {

				TLAbsChatInvite xx = client.messagesCheckChatInvite(a);
				System.out.println("//////////" + xx.hashCode());
				return 0;

			} catch (IOException | RpcErrorException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				return 1;

			}
		} else if (user_number!=null && !matn.equals("")) {
			System.out.println(user_number);
			System.out.println("user id"+user_number);
			Thread.currentThread();
			
			for (int i = 0; i < user_number.size(); i++) {
			try {
				
				
					String temp = matn + "\r\n" + "\r\n" + "🆔" + "واگذار شده" + "\r\n" + "\r\n"
							+ "➖➖➖➖🔻➖➖➖➖\r\n" + "📚@Agahi_Daneshjouei";
					TLAbsDialogs tlAbsDialogs = client.messagesGetDialogs(false, 0, 0, new TLInputPeerEmpty(), 5);
					Thread.currentThread();
					Thread.sleep(6000);
					TLAbsInputPeer inputPeer = getInputPeer(tlAbsDialogs);
					TLAbsUpdates tlAbsUpdates = client.messagesEditMessage(true, inputPeer, user_number.get(i),
							temp, null, null);
				
				

				
			} catch (RpcErrorException | IOException e) {
				e.printStackTrace();
			}
			}
		}
		return 0;

	}

	public static TLAbsInputPeer getInputPeer(TLAbsDialogs tlAbsDialogs) {
		TLAbsPeer tlAbsPeer = tlAbsDialogs.getDialogs().get(0).getPeer();
		int peerId = getId(tlAbsPeer);
		TLObject peer = tlAbsPeer instanceof TLPeerUser
				? tlAbsDialogs.getUsers().stream().filter(user -> user.getId() == peerId).findFirst().get()
				: tlAbsDialogs.getChats().stream().filter(chat -> chat.getId() == peerId).findFirst().get();

		if (peer instanceof TLChannel)
			return new TLInputPeerChannel(((TLChannel) peer).getId(), ((TLChannel) peer).getAccessHash());
		if (peer instanceof TLChat)
			return new TLInputPeerChat(((TLChat) peer).getId());
		if (peer instanceof TLUser)
			return new TLInputPeerUser(((TLUser) peer).getId(), ((TLUser) peer).getAccessHash());

		return new TLInputPeerEmpty();
	}

	public static int getId(TLAbsPeer peer) {
		if (peer instanceof TLPeerUser)
			return ((TLPeerUser) peer).getUserId();
		if (peer instanceof TLPeerChat)
			return ((TLPeerChat) peer).getChatId();

		return ((TLPeerChannel) peer).getChannelId();
	}

	

	static int u = 0;
	
	
	public void run(String s) {
		try {
			// Displaying the thread that is running
			System.out.println("Thread " + Thread.currentThread().getId() + " is running");
			u = test(s, null, "");
			Thread.currentThread();
			Thread.sleep(2000);
		} catch (Exception e) {
			// Throwing an exception
			System.out.println("Exception is caught");
		}

	}
}
