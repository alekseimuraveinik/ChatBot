package root;

import datasource.CloudStorageLoader;
import datasource.TestQuestionsLoader;
import db.Database;
import io.TelegramIO;
import logic.Callboard;
import logic.ChatLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import telegramLogic.MessageProcessor;

@Configuration
public class SpringConfiguration {
    @Bean
    public Database database(){
        return new Database();
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

    @Bean
    public TestQuestionsLoader testLoader(){
        return new TestQuestionsLoader();
    }

    @Bean
    public TelegramIO telegramIO(){
        return new TelegramIO(System.getenv("BOT_NAME"), System.getenv("BOT_TOKEN"));
    }

}
