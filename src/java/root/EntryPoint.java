package root;

import datasource.FileReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import telegramLogic.IMessageProcessor;
import io.TelegramIO;

public class EntryPoint{
    public static void main(String[] args) {
        String SUCCESS_MESSAGE = "Success!";
        String ERROR_MESSAGE = "Connection error!";
        String filename = "telegram_data";

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        IMessageProcessor processor = (IMessageProcessor) context.getBean("messageProcessor");

        try (FileReader reader = new FileReader(filename)) {
            String botName = reader.readLine();
            String botToken = reader.readLine();

            TelegramIO io = new TelegramIO(botName, botToken);

            processor.subscribe(io);
            io.subscribe(processor);

            io.init();
            System.out.println(SUCCESS_MESSAGE);

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(ERROR_MESSAGE);
        }
    }
}
