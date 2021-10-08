package javabot;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.github.badoualy.telegram.api.TelegramApp;
import com.github.badoualy.telegram.api.TelegramClient;
import com.github.badoualy.telegram.tl.api.TLAbsInputPeer;
import com.github.badoualy.telegram.tl.api.TLAbsPeer;
import com.github.badoualy.telegram.tl.api.TLChannel;
import com.github.badoualy.telegram.tl.api.TLChat;
import com.github.badoualy.telegram.tl.api.TLInputPeerChannel;
import com.github.badoualy.telegram.tl.api.TLInputPeerChat;
import com.github.badoualy.telegram.tl.api.TLInputPeerEmpty;
import com.github.badoualy.telegram.tl.api.TLInputPeerUser;
import com.github.badoualy.telegram.tl.api.TLPeerChannel;
import com.github.badoualy.telegram.tl.api.TLPeerChat;
import com.github.badoualy.telegram.tl.api.TLPeerUser;
import com.github.badoualy.telegram.tl.api.TLUser;
import com.github.badoualy.telegram.tl.api.messages.TLAbsDialogs;
import com.github.badoualy.telegram.tl.core.TLObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 *
 * ApiContextInitializer.init(); TelegramBotsApi telegramBotsApi = new
 * TelegramBotsApi(); try { telegramBotsApi.registerBot(new ZaurEduBot());
 * 
 * } catch (TelegramApiException e) { e.printStackTrace(); }
 */
public class App {
	static ConnectionString connectionString;
	static MongoClientSettings settings;
	static MongoClient mongoClient;
	static MongoDatabase database;

	public static void main(String[] args) throws TelegramApiException {
		
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new MyAmazingBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
		connectionString = new ConnectionString(
				"mongodb+srv://agahi:09029819443@cluster0.iloty.mongodb.net/test?retryWrites=true&w=majority");
		settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
		mongoClient = MongoClients.create(settings);
		database = mongoClient.getDatabase("test");
		

	}

	
}
