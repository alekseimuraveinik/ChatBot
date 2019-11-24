package root;

import datasource.CloudStorageLoader;
import db.Database;
import logic.Callboard;
import logic.ChatLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import telegramLogic.MessageProcessor;

@Configuration
public class SpringConfiguration {
    @Bean
    public Database database(){
        return new Database("firebase_api_key.json");
    }

    @Bean
    public CloudStorageLoader cloudLoader(){
        return new CloudStorageLoader(database());
    }

    @Bean
    public Callboard callboard(){
        return new Callboard(database());
    }

    @Bean
    public ChatLogic chatLogic(){
        return new ChatLogic(cloudLoader(), callboard());
    }

    @Bean
    public MessageProcessor messageProcessor(){
        return new MessageProcessor(chatLogic(), database());
    }

}
