package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

@Service
public class ReplyService {
	
	private final LineMessagingService lineMessagingService;
	
	public ReplyService(LineMessagingService lineMessagingService) {
        super();
        this.lineMessagingService = lineMessagingService;
    }

	public BotApiResponse reply(MessageEvent<TextMessageContent> event) throws IOException {

		System.out.println("creat messages");
        String receivedMessage = event.getMessage().getText();
        String replyToken = event.getReplyToken();
        
        List<Message> messages = new ArrayList<>();
        messages.add(new TextMessage("received Message is " + receivedMessage));
        messages.add(new TextMessage("it's reply message"));
        
        
        return lineMessagingService
            .replyMessage(new ReplyMessage(replyToken, messages))
            .execute()
            .body();

    }

}
