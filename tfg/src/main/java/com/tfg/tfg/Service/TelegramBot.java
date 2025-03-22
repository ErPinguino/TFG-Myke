package com.tfg.tfg.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.json.JSONObject;

@Service
public class TelegramBot extends TelegramLongPollingBot {

    // Token de Wit.ai y Telegram
    private static final String WIT_AI_ACCESS_TOKEN = "M3573OWT6ZMJFVNQNM2IBOVMUYJT2EAS"; // Reemplaza con tu token de Wit.ai
    private static final String TELEGRAM_BOT_TOKEN = "7626831987:AAG75vQ_R0UfEwGIB07JIhXNedKR5pUMElI"; // Reemplaza con tu token de bot de Telegram

    @Override
    public void onUpdateReceived(Update update) {
        final String messageTextReceived = update.getMessage().getText().toLowerCase(); // Lo convertimos a minúsculas para facilitar las comparaciones
        final long chatId = update.getMessage().getChatId();

        // Se crea un objeto mensaje
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        // Si el mensaje recibido es el primer mensaje, siempre responder con el saludo inicial
        if (messageTextReceived.equals("/start") || messageTextReceived.isEmpty()) {
            message.setText("Que la arconte Dendro te proteja viajero, soy AkashIA. A partir de ahora te ayudaré en lo que necesites. ¿En qué puedo ayudarte?");
        } else {
            // Usar Wit.ai para procesar el mensaje y obtener una respuesta
            String witResponse = getWitResponse(messageTextReceived);

            // Responder con el resultado de Wit.ai
            message.setText(witResponse);
        }

        try {
            // Enviar el mensaje
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // Método para enviar el mensaje de usuario a Wit.ai y obtener la respuesta
    private String getWitResponse(String userMessage) {
        RestTemplate restTemplate = new RestTemplate();
        
        // Construir la URL de la API de Wit.ai para enviar la consulta
        String url = "https://api.wit.ai/message?v=20220325&q=" + userMessage;

        // Configurar las cabeceras con el token de Wit.ai
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + WIT_AI_ACCESS_TOKEN);

        // Enviar solicitud GET a la API de Wit.ai
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Analizar la respuesta de Wit.ai y devolver la respuesta
        JSONObject jsonResponse = new JSONObject(response.getBody());
        String witMessage = jsonResponse.getJSONObject("entities").toString(); // Obtiene las entidades procesadas por Wit.ai
        
        // Si no hay respuesta, responder con un mensaje por defecto
        if (witMessage.isEmpty()) {
            return "Lo siento, no entendí la pregunta. ¿Puedes preguntar algo más específico?";
        }

        // Aquí puedes personalizar la respuesta en función de las entidades reconocidas por Wit.ai
        return "Entendido. Estoy procesando lo que necesitas.";
    }

    @Override
    public String getBotUsername() {
        return "AkashIAbot"; // Reemplaza con tu nombre de usuario del bot
    }

    @Override
    public String getBotToken() {
        return TELEGRAM_BOT_TOKEN; // Reemplaza con tu token del bot de Telegram
    }
}