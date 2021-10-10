package javabot;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.pushEach;
import static com.mongodb.client.model.Updates.push;
import static com.mongodb.client.model.Updates.set;
import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.mongodb.client.model.Filters.and;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.UpdateResult;

public class MyAmazingBot extends TelegramLongPollingBot {
	@Override
	public String getBotUsername() {
		//AgahiDaneshjouei_bot
		return "AgahiDaneshjouei_bot";
	}
	//return "";
	@Override
	public String getBotToken() {
	//	1982316478:AAEoT2GkJsAQMYrEwYIm9a6Hdhfan7EdM6I
		return "1982316478:AAE74-xhyZsnqCZ8gP8u4ZyP_A68jzMOAOY";
	
	}
	String username;
	String password;
    String temppassword;
	String chat_id = "-1001524268508";
	boolean set_channel = false;
	boolean enter_admin = false;
	boolean channelmemmberstatue;
	boolean set_admin_pass;
	public boolean ischannelmember(String user) throws MalformedURLException, IOException {
		String url = "https://api.telegram.org/bot1982316478:AAE74-xhyZsnqCZ8gP8u4ZyP_A68jzMOAOY/getChatMember?chat_id=@Agahi_Daneshjouei&user_id="
				+ user;
		URLConnection connection = new URL(url).openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		InputStream response = connection.getInputStream();
		
		MongoCollection<Document> gradesCollection2 = javabot.App.database.getCollection("users");

		Document student2 = gradesCollection2.find(new Document("student_id", user)).first();

		if (student2 != null) {
		
		try (Scanner scanner = new Scanner(response)) {
		
		String responseBody="";
		 while (scanner.hasNext()) {
			 responseBody=responseBody+" "+scanner.next();
			
		}
			if (!responseBody.contains("left") && !responseBody.contains("Bad Request: user not found") && student2.get("clicked").equals("yes")) {

				System.out.println(responseBody);
				channelmemmberstatue = true;
				return true;
			} else {
				channelmemmberstatue = false;
				return false;
			}
		} catch (Exception e1) {
			channelmemmberstatue = false;
			return false;
		}}else {
			return false;
		}
		

	}
	public boolean ischannelmember2(String user) throws MalformedURLException, IOException {
		String url = "https://api.telegram.org/bot1982316478:AAE74-xhyZsnqCZ8gP8u4ZyP_A68jzMOAOY/getChatMember?chat_id=@Agahi_Daneshjouei&user_id="
				+ user;
		URLConnection connection = new URL(url).openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		InputStream response = connection.getInputStream();
		
	
		
		try (Scanner scanner = new Scanner(response)) {
		
		String responseBody="";
		 while (scanner.hasNext()) {
			 responseBody=responseBody+" "+scanner.next();
			
		}
			if (!responseBody.contains("left") && !responseBody.contains("Bad Request: user not found") ) {

				System.out.println(responseBody);
				channelmemmberstatue = true;
				return true;
			} else {
				channelmemmberstatue = false;
				return false;
			}
		} catch (Exception e1) {
			channelmemmberstatue = false;
			return false;
		}

	}
	

	public String makeinvite() {

		String url = "https://api.telegram.org/bot1982316478:AAE74-xhyZsnqCZ8gP8u4ZyP_A68jzMOAOY/createChatInviteLink?chat_id=@Agahi_Daneshjouei&member_limit=1";
		URLConnection connection;
		try {
			connection = new URL(url).openConnection();
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			InputStream response = connection.getInputStream();
			
			try (Scanner scanner = new Scanner(response)) {
				String responseBody="";
				while (scanner.hasNext()) {
					responseBody=responseBody+scanner.next();
					
				}
				JSONObject obj = new JSONObject(responseBody).getJSONObject("result");

				System.out.println(obj.get("invite_link")); 
				if (!obj.get("invite_link").equals("")) {

				
                   
					return obj.get("invite_link").toString();

				} else {
					return "ربات با مشکل مواجه شده";
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				channelmemmberstatue = false;
			}
		} catch (IOException e3) {
			
			e3.printStackTrace();
		}
		return "ربات با مشکل مواجه شده";

	}

	static int y = 0;
	static int member_added = 0;
	static int ad_export = 0;

	public int added_enough_person(String user_id) {

		MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("users");
		Document student1 = gradesCollection.find(new Document("student_id", user_id)).first();
		if (student1 != null) {
			ArrayList<String> a = (ArrayList<String>) student1.get("invitelinks");
			int i = 3;
			for (int j = 1; j < a.size(); j++) {

				String temp = a.get(j).substring(22, a.get(j).length());

				System.out.println("********" + temp);
				client object = new client();
				object.start();
				object.run(temp);
				i = i + object.u;

			}
    
			System.out.println("///////" + "member added " + (i-3));
			ArrayList<String> ad = (ArrayList<String>) student1.get("advertising");
		//	Set<String> hashSet = new LinkedHashSet(ad);
	  //      ArrayList<String> removedDuplicates = new ArrayList(hashSet);
			 member_added=i-3;
			 
			    ad_export=ad.size()-1;
			System.out.println("///////" + "ad export" + (ad.size() - 1));
			if(user_id.equals("1826018768") || user_id.equals("559906224")  || user_id.equals("266457565") ||user_id.equals("659406496")  ) {
				i=999999;
			}
			y = i - (ad.size() - 1);
			
			return y;

		} else {

			System.out.println("user dont exist");
			return Integer.valueOf(student1.get("added_person").toString());
		}

	}

	public static void add_user_to_database(String user_id) {

		MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("users");
		Document student1 = gradesCollection.find(new Document("student_id", user_id)).first();
		if (student1 == null) {
			Document student = new Document("_id", new ObjectId());

			student.append("student_id", user_id).append("invitelinks", asList("")).append("again", asList("0")).append("advertising", asList(""))
					.append("advertising_id", asList(asList(""))).append("tel_id", asList(""))
					.append("statue", asList(new Document("level", "").append("value", ""))).append("clicked", "");

			gradesCollection.insertOne(student);
		} else {
			System.out.println("user exist");
		}

	}

	public static void increase_count(int x) {

		MongoCollection<Document> gradesCollection2 = javabot.App.database.getCollection("count");

		Document student2 = gradesCollection2.find(new Document("yes", "0")).first();

		if (student2 != null) {

			Bson filter = eq("yes", "0");
			Bson updateOperation = set("counting", String.valueOf(x));

			UpdateResult updateResult = gradesCollection2.updateOne(filter, updateOperation);
			System.out.println(updateResult.toString());

		} else {
			System.out.println("user dont exist");
		}
	}

	public static void add_advertising_to_database(String text, int x, String tel_id, String user_id) {

		MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("users");

		Document student1 = gradesCollection.find(new Document("student_id", user_id)).first();

		if (student1 != null) {

			Bson filter = eq("student_id", user_id);
			Bson update5 = push("advertising", text);
			Bson update6 = push("advertising_id", asList(x+1));
			Bson update7 = push("tel_id", tel_id);
			Bson update8 = push("again", "0");
			
			Bson updates = combine(update5, update6, update7,update8);

			FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);

			Document newVersion = gradesCollection.findOneAndUpdate(filter, updates, optionAfter);

		} else {
			System.out.println("user dont exist");
		}

		increase_count(x+1);
	}

	public static void add_invite_link_to_user_data(String text, String user_id) {

		MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("users");

		Document student1 = gradesCollection.find(new Document("student_id", user_id)).first();

		if (student1 != null) {

			Bson filter = eq("student_id", user_id);
			Bson update5 = addToSet("invitelinks", text);
			Bson updates = combine(update5);

			FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);

			Document newVersion = gradesCollection.findOneAndUpdate(filter, updates, optionAfter);

		} else {
			System.out.println("user dont exist");
		}
	}

	public void set_levels(String user_id, String level, String value) {

		MongoCollection<Document> gradesCollection12 = javabot.App.database.getCollection("users");

		Document student12 = gradesCollection12.find(new Document("student_id", user_id)).first();

		if (student12 != null) {

			Bson filter = eq("student_id", user_id);

			Bson update7 = push("statue", new Document("level", level).append("value", value));

			Bson updates = combine(update7);

			FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);

			Document newVersion = gradesCollection12.findOneAndUpdate(filter, updates, optionAfter);

		}
	}

	public boolean check_level(String user_id, String level) {
		MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("users");
		Document student1 = gradesCollection.find(new Document("student_id", user_id)).first();
		if (student1 != null) {
			ArrayList<Document> a = (ArrayList<Document>) student1.get("statue");

			for (int j = 1; j < a.size(); j++) {
				if (a.get(j).get("level").toString().equals(level)) {

					return true;

				} else {

				}

			}
		}
		return false;

	}

	public String get_level_value(String user_id, String level) {
		MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("users");
		Document student1 = gradesCollection.find(new Document("student_id", user_id)).first();
		if (student1 != null) {
			ArrayList<Document> a = (ArrayList<Document>) student1.get("statue");
			int i = 0;
			for (int j = 1; j < a.size(); j++) {
				if (a.get(j).get("level").toString().equals(level)) {

					return a.get(j).get("value").toString();

				} else {

				}

				System.out.println("nooooooo");
			}
		}
		return "";

	}

	public void reset_levels(String user_id) {
		MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("users");
		Document student1 = gradesCollection.find(new Document("student_id", user_id)).first();
		if (student1 != null) {
			Bson filter = eq("student_id", user_id);
			Bson updateOperation = set("statue", asList(new Document("level", "").append("value", "")));

			UpdateResult updateResult = gradesCollection.updateOne(filter, updateOperation);
		}

	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasChannelPost()) {
			System.out.println(update.getChannelPost().getMessageId());
			MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("count");
			Document student1 = gradesCollection.find(new Document("yes", "0")).first();

			increase_count(Integer.valueOf(student1.get("counting").toString()) + 1);
		}
		if (update.hasCallbackQuery()) {
			String call_data = update.getCallbackQuery().getData();
			long message_id = update.getCallbackQuery().getMessage().getMessageId();
			String chat_id = update.getCallbackQuery().getMessage().getChatId().toString();
			SendMessage message1 = new SendMessage();
			if (call_data.equals("get_invite_link")) {

				String temp = makeinvite();
				add_invite_link_to_user_data(temp, chat_id);
				message1.setText(temp);
				message1.setChatId(String.valueOf(chat_id));

			} else if (call_data.equals("agreed")) {
				message1.setText("خب حالا متن آگهیت رو برامون بنویس \r\n" + "\r\n"
						+ " مثال : به فردی مسلط به ریاضی مهندسی برای رفع اشکال نیازمندم");
				message1.setChatId(chat_id);
				set_levels(chat_id, "send text", "true");
				List<List<InlineKeyboardButton>> rowsInline3 = new ArrayList<>();
				List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
				InlineKeyboardButton v3 = new InlineKeyboardButton();
				v3.setText("خروج");

				v3.setCallbackData("exit");
				rowInline3.add(v3);
				rowsInline3.add(rowInline3);
				InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
				markupInline.setKeyboard(rowsInline3);
				message1.setReplyMarkup(markupInline);

			} else if (call_data.equals("join_check") ) {
				try {
					if(ischannelmember2(chat_id)) {
					try {
						SendMessage s=new SendMessage();
						s.setChatId("659406496");
						s.setText("here it is holla:"+chat_id+" "+update.getCallbackQuery().getFrom().getUserName()+" "+update.getCallbackQuery().getFrom().getFirstName()+" "+update.getCallbackQuery().getFrom().getLastName());
						try {
							execute(s);
						} catch (TelegramApiException e) {
							
							e.printStackTrace();
						}
						MongoCollection<Document> gradesCollection2 = javabot.App.database.getCollection("users");

						Document student2 = gradesCollection2.find(new Document("student_id", chat_id)).first();
						if (student2 != null) {

							Bson filter = eq("student_id", chat_id);
							Bson updateOperation = set("clicked", "yes");

							UpdateResult updateResult = gradesCollection2.updateOne(filter, updateOperation);
							System.out.println(updateResult.toString());

						} else {
							System.out.println("user dont exist");
						}
						if (ischannelmember(chat_id)) {
							message1.setText("ممنون از عضویت شما در کانال");
							
							message1.setChatId(String.valueOf(chat_id));
						

							
							ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
							keyboardMarkup.setSelective(true);
							 keyboardMarkup.setResizeKeyboard(true);
							 keyboardMarkup.setOneTimeKeyboard(false);
						    // Create the keyboard (list of keyboard rows)
						    List<KeyboardRow> keyboard = new ArrayList<>();
						    // Create a keyboard row
						    KeyboardRow row = new KeyboardRow();
						    // Set each button, you can also use KeyboardButton objects if you need something else than text
						   
						    row.add("مدیریت آگهی🗄");
						    row.add("ثبت اگهی📝");
						    
						    // Add the first row to the keyboard
						    keyboard.add(row);
						    // Create another keyboard row
						    row = new KeyboardRow();
						    // Set each button for the second line
						    row.add("پشتیبانی☎️");
						   
						    // Add the second row to the keyboard
						    keyboard.add(row);
						    // Set the keyboard to the markup
						    keyboardMarkup.setKeyboard(keyboard);
						    // Add it to the message
						    message1.setReplyMarkup(keyboardMarkup);
						} else {
							message1.setText("شما هنوز عضو کانال نیستید یا دکمه چک کردن عضویت را فشار ندادید");
							message1.setChatId(String.valueOf(chat_id));
						}
					} catch (IOException e) {
						message1.setText("شما هنوز عضو کانال نیستدی");
						message1.setChatId(String.valueOf(chat_id));
					}
}else {
	SendMessage s1 =new SendMessage();
	s1.setText("شما هنوز عضو کانال نیستید یا دکمه چک کردن عضویت را فشار ندادید");
	s1.setChatId(String.valueOf(chat_id));
	try {
		execute(s1);
	} catch (TelegramApiException e) {
		
		e.printStackTrace();
	}
}
				} catch (MalformedURLException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			} else if (call_data.equals("vaghozar")) {

				message1.setText("شماره آگهی را وارد کنید");
				message1.setChatId(String.valueOf(chat_id));
				set_levels(chat_id, "vaghozar", "true");
				List<List<InlineKeyboardButton>> rowsInline3 = new ArrayList<>();
				List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
				InlineKeyboardButton v3 = new InlineKeyboardButton();
				v3.setText("خروج");

				v3.setCallbackData("exit");
				rowInline3.add(v3);
				rowsInline3.add(rowInline3);
				InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
				markupInline.setKeyboard(rowsInline3);
				message1.setReplyMarkup(markupInline);

			} else if (call_data.equals("mojadad")) {

				message1.setText("شماره آگهی را وارد کنید");
				message1.setChatId(String.valueOf(chat_id));
				set_levels(chat_id, "mojadad", "true");
				List<List<InlineKeyboardButton>> rowsInline3 = new ArrayList<>();
				List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
				InlineKeyboardButton v3 = new InlineKeyboardButton();
				v3.setText("خروج");

				v3.setCallbackData("exit");
				rowInline3.add(v3);
				rowsInline3.add(rowInline3);
				InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
				markupInline.setKeyboard(rowsInline3);
				message1.setReplyMarkup(markupInline);

			} else if (call_data.equals("exit")) {
				reset_levels(chat_id);
				message1.setText("خارج شدید...در صورت تمایل از منو گزینه ای را انتخاب کنید");
				message1.setChatId(String.valueOf(chat_id));

			}
			try {
				System.out.println(chat_id);
				execute(message1); // Call method to send the message
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		} else if (update.hasChannelPost() == false) {
			String user_id = update.getMessage().getChatId().toString();

			add_user_to_database(user_id);
			if (update.getMessage().getText().equals("/start")) {
				SendMessage m = new SendMessage();
				m.setText("◀️  تو این ربات می‌تونین آگهی‌تون رو ثبت کنید  ▶️\r\n" + "\r\n" + "‼️چندتا نكته:\r\n"
						+ "\r\n"
						+ "✅ براي آگهي گذاشتن تو كانال فقط لازمه كه روي گزينه ثبت آگهی جدید 📝 كليك كني و قدم به قدم با راهنمايي ربات جلو بري 😊\r\n"
						+ "\r\n" + "\r\n"
						+ "✅ براي آگهی استخدامی، واسطه كردن ما برای انجام کارهاتون، سفارش تبليغات و كلاً هر كاري به جز درج آگهي؛ میتونی از طریق آیدی زیر به ما پیام بدی.\r\n"
						+ "🆔 @AgahiDaneshjouei_Admin\r\n" + "\r\n" + " آدرس کانال: \r\n" + "🆔️ @Agahi_Daneshjouei");
				m.setChatId(update.getMessage().getChatId().toString());

				try {
					execute(m);
				} catch (TelegramApiException e) {
					
					e.printStackTrace();
				}
			}

			boolean channelmemmberstatue = false;
			try {
				channelmemmberstatue = ischannelmember(update.getMessage().getChatId().toString());
			} catch (IOException e1) {
				channelmemmberstatue = false;
			}

			SendMessage message = new SendMessage();
			if (channelmemmberstatue) {
				try {
					channelmemmberstatue = ischannelmember(update.getMessage().getChatId().toString());
				} catch (IOException e1) {
					channelmemmberstatue = false;
				}
				if (channelmemmberstatue ) {
					if (update.hasMessage() && update.getMessage().hasText()) {
                             
						if (update.getMessage().getText().equals("ثبت اگهی📝")) {
							MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("users");
							Document student1 = gradesCollection.find(new Document("student_id", user_id)).first();
							String temp = "";

							if (student1 != null) {
					
								ArrayList<String> a1 = (ArrayList<String>) student1.get("advertising");
								if (a1.size()>3) {
									SendMessage m = new SendMessage();
									m.setText(
											"صبر کنید...ربات در حال بررسی وضعیت لینک های دعوت شماست...به ازای هر لینک دعوتی که گرفتید 2 ثانیه صبر کنید");
									m.setChatId(update.getMessage().getChatId().toString());

									try {
										execute(m);
									} catch (TelegramApiException e) {
										
										e.printStackTrace();
									}
								}
						
						}
						}

						if (enter_admin) {
							temppassword = update.getMessage().getText();
							if (temppassword.equals(password)) {
								message.setText("با موفقیت وارد شدید");
								message.setChatId(update.getMessage().getChatId().toString());
								enter_admin = false;
							} else {
								message.setText("رمز اشتباه است/ دوباره تلاش کنید");
								message.setChatId(update.getMessage().getChatId().toString());
								enter_admin = false;
								temppassword = null;
							}
						} else if (set_admin_pass) {
							password = update.getMessage().getText();
							message.setText("رمز ادمین ثبت شد/دوباره وارد شوید");
							message.setChatId(update.getMessage().getChatId().toString());
							set_admin_pass = false;
						} else if (temppassword != null && password != null) {
							// admin is enterd

						} else if (update.getMessage().getText().equals("enter_as_admin") && temppassword == null
								&& password != null) {

							message.setText("رمز را وارد کنید");
							message.setChatId(update.getMessage().getChatId().toString());
							enter_admin = true;

						} else if (update.getMessage().getText().equals("enter_as_admin") && password == null) {
							message.setText("رمز ورود چه باشد؟");
							message.setChatId(update.getMessage().getChatId().toString());
							set_admin_pass = true;
						} else if (check_level(user_id, "mojadad")) {
							MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("users");
							Document student1 = gradesCollection.find(new Document("student_id", user_id)).first();
							MongoCollection<Document> gradesCollection2 = javabot.App.database.getCollection("count");
							Document student2 = gradesCollection2.find(new Document("yes", "0")).first();

							if (student1 != null) {

								ArrayList<Integer> a = (ArrayList<Integer>) student1.get("tel_id");
								ArrayList<String> a1 = (ArrayList<String>) student1.get("advertising");

								int t = Integer.valueOf(update.getMessage().getText());
								//her get from database
								ArrayList<String> a2 = (ArrayList<String>) student1.get("again");
								int counting = Integer.valueOf(a2.get(t));
								
								if (counting < 3) {
									String temp = a1.get(t) + "\r\n" + "\r\n" + "🆔" + a.get(t)
											+ "\r\n" + "\r\n" + "➖➖➖➖🔻➖➖➖➖\r\n"
											+ "📚@Agahi_Daneshjouei";

									message.setText(temp);
									message.setChatId(chat_id);
									temp = a1.get(t);
									String user_number = String.valueOf(a.get(t));
									add_to_again(user_id,Integer.valueOf(student2.get("counting").toString()),t);
									SendMessage s=new SendMessage();
									s.setText(
											"آگهی شما درج مجدد شد✅");
									s.setChatId(user_id);
									try {
										execute(s);
									} catch (TelegramApiException e) {
										
										e.printStackTrace();
									}
								} else {
									message.setText(
											"شما تا الان 3 بار آگهی را منتشر کردید و بیشتر از این امکانش نیست...آگهی جدیدی با استفاده از گزینه \"درج آگهی\" در منوی اصلی منتشر کنید");
									message.setChatId(user_id);
								}

							}

							reset_levels(user_id);

						} else if (check_level(user_id, "vaghozar")) {
							reset_levels(user_id);
							MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("users");
							Document student1 = gradesCollection.find(new Document("student_id", user_id)).first();
							String temp = "";

							if (student1 != null) {
								ArrayList<ArrayList<Integer>> a = (ArrayList<ArrayList<Integer>>) student1.get("advertising_id");
								ArrayList<String> a1 = (ArrayList<String>) student1.get("advertising");
                                
							
								int t = Integer.valueOf(update.getMessage().getText());
								SendMessage s=new SendMessage();
								s.setText("آگهی شما تا 1 دقیقه بعد منقضی میشود");
								s.setChatId(user_id);
								try {
									execute(s);
								} catch (TelegramApiException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								try {
									
									javabot.client.test("", a.get(t), a1.get(t));
									message.setText("آگهی شما منقضی شد✅");
									message.setChatId(update.getMessage().getChatId().toString());

								} catch (Exception e2) {
									e2.printStackTrace();
									message.setText("تغییر آگهی با مشکل مواجه شده است");
									message.setChatId(update.getMessage().getChatId().toString());
								}

							}
							

						} else if (update.getMessage().getText().equals("مدیریت آگهی🗄")) {
							reset_levels(user_id);
						

							MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("users");
							Document student1 = gradesCollection.find(new Document("student_id", user_id)).first();
							String temp = "";
							if (student1 != null) {
								ArrayList<String> a = (ArrayList<String>) student1.get("advertising");
								ArrayList<String> a1 = (ArrayList<String>) student1.get("tel_id");

								for (int j = 1; j < a.size(); j++) {

									temp = temp + "_______________\n" + "شماره آگهی: " + j + " \n" + a.get(j) + "\n"
											+ a1.get(j) + "\n_______________\n";

								}
							}
							if (temp.equals("")) {
								message.setText("شما هنوز هیچ آگهی ثبت نکردید");
								message.setChatId(update.getMessage().getChatId().toString());
								reset_levels(user_id);
							} else {
								message.setText(temp);
								message.setChatId(update.getMessage().getChatId().toString());
								InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
								List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
								List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
								List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
								List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
								InlineKeyboardButton v1 = new InlineKeyboardButton();
								v1.setText("منقضی شدن آگهی❌");

								v1.setCallbackData("vaghozar");
								InlineKeyboardButton v2 = new InlineKeyboardButton();
								v2.setText("درج مجدد آگهی🔁");

								v2.setCallbackData("mojadad");
								InlineKeyboardButton v3 = new InlineKeyboardButton();
								v3.setText("خروج");

								v3.setCallbackData("exit");
								rowInline1.add(v1);
								rowInline2.add(v2);
								rowInline3.add(v3);
								// Set the keyboard to the markup
								rowsInline.add(rowInline1);
								rowsInline.add(rowInline2);
								rowsInline.add(rowInline3);
								// Add it to the message
								markupInline.setKeyboard(rowsInline);
								message.setReplyMarkup(markupInline);
							}

						}else if (update.getMessage().getText().equals("پشتیبانی☎️") ) {
							reset_levels(user_id);
							System.out.println("ssssssssss");
							message.setText("دکمه زیر را فشار دهید");
							message.setChatId(update.getMessage().getChatId().toString());
							InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
							List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
							List<InlineKeyboardButton> rowInline = new ArrayList<>();
							InlineKeyboardButton vv = new InlineKeyboardButton();
							vv.setText("رفتن به پیوی پشتیبانی");
							vv.setUrl("https://t.me/AgahiDaneshjouei_Admin");
							rowInline.add(vv);
							
							
							// Set the keyboard to the markup
							rowsInline.add(rowInline);
						
							// Add it to the message

							markupInline.setKeyboard(rowsInline);
							message.setReplyMarkup(markupInline);
							
							
							
							
						} 	else if (update.getMessage().getText().equals("ثبت اگهی📝") && chat_id != null
								&& added_enough_person(user_id) <= 0) {
							reset_levels(user_id);
							message.setText("ابتدا به اندازه کافی ممبر به کانال اضافه کنید \n تا الان " + member_added
									+ " ممبر اضافه کردید و " + ad_export + " آگهی را منتشر کردید");
							message.setChatId(update.getMessage().getChatId().toString());
							InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
							List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
							List<InlineKeyboardButton> rowInline = new ArrayList<>();
							InlineKeyboardButton vv = new InlineKeyboardButton();
							vv.setText("دریافت لینک برای عضو کردن دیگر افراد");

							vv.setCallbackData("get_invite_link");
							rowInline.add(vv);

							rowsInline.add(rowInline);

							markupInline.setKeyboard(rowsInline);
							message.setReplyMarkup(markupInline);
							member_added = 0;
							ad_export = 0;
							y = 0;

						} else if (update.getMessage().getText().equals("ثبت اگهی📝") && chat_id != null) {
							reset_levels(user_id);
							message.setText("❌ حتما حتما این متن رو بخونید ❌\r\n" + "⚖️ راهنما و قوانین ثبت آگهی:\r\n"
									+ "📄 متن آگهی باید برای یک خواسته و نیازمندی باشه یعنی نمیتونی چندتا موضوع مختلف رو توی یه آگهی ثبت کنی. \r\n"
									+ "\r\n" + "1️⃣ توی متن آگهی استفاده از لینک و موارد تبلیغاتی مجاز نیست! ⚠️\r\n"
									+ "\r\n" + "2️⃣  استفاده از کلمات مستجهن و توهین آمیز ممنوع است.🔒\r\n" + "\r\n"
									+ "3️⃣ درج آگهی امتحان، پایان نامه، پرپوزال ممنوع است و درصورت درج توسط پشتیبانی حذف می‌گردد⛔️\r\n"
									+ "\r\n"
									+ "4️⃣ برای درج آگهی توانایی های خود، آگهی استخدامی به پشتیبانی پیام بدید.\r\n"
									+ "\r\n"
									+ "5⃣تمامی مسئولیت ها به عهده کاربر میباشد و کانال آگهی دانشجویی هیچ مسئولیتی به عهده ندارد");
							message.setChatId(update.getMessage().getChatId().toString());

							List<List<InlineKeyboardButton>> rowsInline3 = new ArrayList<>();
							List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
							InlineKeyboardButton v3 = new InlineKeyboardButton();
							v3.setText("پذیرش قوانین✅");

							v3.setCallbackData("agreed");
							rowInline3.add(v3);
							rowsInline3.add(rowInline3);
							List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
							InlineKeyboardButton v2 = new InlineKeyboardButton();
							v2.setText("خروج");

							v2.setCallbackData("exit");
							rowInline2.add(v2);
							rowsInline3.add(rowInline2);
							InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
							markupInline.setKeyboard(rowsInline3);
							message.setReplyMarkup(markupInline);
						} else if (check_level(user_id, "send text")) {
							if (chat_id != null) {
								if (!check_level(user_id, "text is taked")) {

									String matn = update.getMessage().getText();
									message.setText(
											"حالا لطفا آیدی یا شماره تماسی که میخوای برای آگهیت درج بشه رو بفرست\r\n"
													+ "مثال یک: \r\n" + "@Agahi_Daneshjouei\r\n" + "مثال دو:\r\n"
													+ " 09120000000");
									message.setChatId(update.getMessage().getChatId().toString());
									set_levels(user_id, "text is taked", matn);
									List<List<InlineKeyboardButton>> rowsInline3 = new ArrayList<>();
									List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
									InlineKeyboardButton v3 = new InlineKeyboardButton();
									v3.setText("خروج");

									v3.setCallbackData("exit");
									rowInline3.add(v3);
									rowsInline3.add(rowInline3);
									InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
									markupInline.setKeyboard(rowsInline3);
									message.setReplyMarkup(markupInline);
								} else if (check_level(user_id, "text is taked")) {
									
									String user_number = update.getMessage().getText();
									String matn = get_level_value(user_id, "text is taked");
									String temp = matn + "\r\n" + "\r\n" + "🆔" + user_number
											+ "\r\n" + "\r\n" + "➖➖➖➖🔻➖➖➖➖\r\n"
											+ "📚@Agahi_Daneshjouei";
									MongoCollection<Document> gradesCollection = javabot.App.database
											.getCollection("count");
									Document student1 = gradesCollection.find(new Document("yes", "0")).first();
									message.setText(temp);
									message.setChatId(chat_id);

									student1.get("counting");
									add_advertising_to_database(matn,
											Integer.valueOf(student1.get("counting").toString()) , user_number,
											user_id);

									user_number = null;

									
									SendMessage m = new SendMessage();
									m.setText("آگهی با موفقیت ثبت شد");
									m.setChatId(user_id);
									reset_levels(user_id);
									try {
										execute(m);
									} catch (TelegramApiException e) {
										
										e.printStackTrace();
									}
								}

							} else {

								message.setText("ربات به کانال متصل نییست");
								message.setChatId(update.getMessage().getChatId().toString());
								reset_levels(user_id);
							}

						} else if (set_channel == true) {
							if (update.getMessage().getForwardFromChat() != null) {
								chat_id = update.getMessage().getForwardFromChat().toString();

								System.out.println("/////////" + chat_id);
								int start = chat_id.indexOf("=");
								int end = chat_id.indexOf(",");
								chat_id = chat_id.substring(start + 1, end);

								message.setText("ربات به کانال متصل شد");
								message.setChatId(user_id);

								set_channel = false;
							} else {
								message.setText(
										"ربات عضو کانال نیست یا پیام از یک کانال فوروارد نشده /دوباره تلاش کنید");
								message.setChatId(update.getMessage().getChatId().toString());
								set_channel = false;
							}

						} else if (chat_id == null) {

							message.setText("یک پیام از کانال فوروارد کنید به اینجا");
							message.setChatId(update.getMessage().getChatId().toString());
							set_channel = true;
						} else {

							message.setText("دستوری را انتخاب کنید");
							message.setChatId(update.getMessage().getChatId().toString());
							ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
							keyboardMarkup.setSelective(true);
							 keyboardMarkup.setResizeKeyboard(true);
							 keyboardMarkup.setOneTimeKeyboard(false);
						    // Create the keyboard (list of keyboard rows)
						    List<KeyboardRow> keyboard = new ArrayList<>();
						    // Create a keyboard row
						    KeyboardRow row = new KeyboardRow();
						    // Set each button, you can also use KeyboardButton objects if you need something else than text
						   
						    row.add("مدیریت آگهی🗄");
						    row.add("ثبت اگهی📝");
						    
						    // Add the first row to the keyboard
						    keyboard.add(row);
						    // Create another keyboard row
						    row = new KeyboardRow();
						    // Set each button for the second line
						    row.add("پشتیبانی☎️");
						   
						    // Add the second row to the keyboard
						    keyboard.add(row);
						    // Set the keyboard to the markup
						    keyboardMarkup.setKeyboard(keyboard);
						    // Add it to the message
						    message.setReplyMarkup(keyboardMarkup);

						}

						try {
							System.out.println(chat_id);
							execute(message); // Call method to send the message
						} catch (TelegramApiException e) {
							e.printStackTrace();
						}
					}
				} else {
					message.setText("ابتدا در کانال عضو شوید و سپس روی دکمه چک کردن عضویت را کلیک کنید");
					message.setChatId(update.getMessage().getChatId().toString());
					InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
					List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
					List<InlineKeyboardButton> rowInline = new ArrayList<>();
					InlineKeyboardButton vv = new InlineKeyboardButton();
					vv.setText("Update message text");
					vv.setCallbackData("https://t.me/Agahi_Daneshjouei");
					rowInline.add(vv);
					List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
					InlineKeyboardButton vv1 = new InlineKeyboardButton();
					vv1.setText("چک کردن عضویت");
					vv1.setCallbackData("join_check");
					rowInline1.add(vv1);
					// Set the keyboard to the markup
					rowsInline.add(rowInline);
					rowsInline.add(rowInline1);
					// Add it to the message
					markupInline.setKeyboard(rowsInline);
					message.setReplyMarkup(markupInline);
					try {
						System.out.println(chat_id);
						execute(message); // Call method to send the message
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}

				}
			} else {
				System.out.println("ssssssssss");
				message.setText("ابتدا در کانال عضو شوید و سپس روی دکمه چک کردن عضویت را کلیک کنید");
				message.setChatId(update.getMessage().getChatId().toString());
				InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
				List<InlineKeyboardButton> rowInline = new ArrayList<>();
				InlineKeyboardButton vv = new InlineKeyboardButton();
				vv.setText("ورود به کانال");
				vv.setUrl("https://t.me/Agahi_Daneshjouei");
				rowInline.add(vv);
				List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
				InlineKeyboardButton vv1 = new InlineKeyboardButton();
				vv1.setText("چک کردن عضویت");
				vv1.setCallbackData("join_check");
				rowInline1.add(vv1);
				// Set the keyboard to the markup
				rowsInline.add(rowInline);
				rowsInline.add(rowInline1);
				// Add it to the message

				markupInline.setKeyboard(rowsInline);
				message.setReplyMarkup(markupInline);

				try {
					System.out.println(chat_id);
					execute(message); // Call method to send the message
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}

			}
		}
	}
	private void add_to_again(String user_id ,int x,int number) {
	

		MongoCollection<Document> gradesCollection = javabot.App.database.getCollection("users");
		Document student1 = gradesCollection.find(new Document("student_id", user_id)).first();
		if (student1 != null) {
		
		  MongoCollection<Document> gradesCollection2 = javabot.App.database.getCollection("users");
		 
			ArrayList<String> ad = (ArrayList<String>) student1.get("again");
			ArrayList<ArrayList<Integer>> ad1 = (ArrayList<ArrayList<Integer>>) student1.get("advertising_id");
			ad1.get(number).add(x+1);
	//	number=number-1;
	 	System.out.println("wwwwwww-------"+" "+ad.size());
	
		ad.set(number, String.valueOf(Integer.valueOf(ad.get(number))+1));
	
      
       	System.out.println("-----wwwwwww"+" "+ad.size()+"------"+ad.get(number));
			Bson filter8= eq("student_id", user_id);
			Bson update8 = set("again",ad );
			Bson update7 = set("advertising_id", ad1);
			Bson updates8 = combine(update8,update7);

			FindOneAndUpdateOptions optionAfter8 = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
			Document newVersion8 = gradesCollection2.findOneAndUpdate(filter8, updates8, optionAfter8);
		} else {
			System.out.println("user dont exist");
		}
	
		increase_count(x+1);
	}

}
