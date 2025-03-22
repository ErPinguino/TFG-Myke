package com.tfg.tfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import com.tfg.tfg.Service.TelegramBot;

@SpringBootApplication
public class TfgApplication {

	public static void main(String[] args) {
		try {
			TelegramBotsApi chatBot = new TelegramBotsApi(DefaultBotSession.class);
			chatBot.registerBot(new TelegramBot());
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}

}
