package root;

import logic.BackupWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import telegramLogic.IMessageProcessor;
import io.TelegramIO;
import telegramLogic.MessageProcessor;

public class EntryPoint{

    public static void main(String[] args) {

        String SUCCESS_MESSAGE = "Success!";
        String ERROR_MESSAGE = "Connection error!";

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        IMessageProcessor processor = context.getBean(MessageProcessor.class);
        
        try {
            TelegramIO io = context.getBean(TelegramIO.class);

            io.subscribe(processor);

            io.init();
            System.out.println(SUCCESS_MESSAGE);

            //BackupWorker worker = context.getBean(BackupWorker.class);
            //new Thread(worker).start();

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(ERROR_MESSAGE);
        }
    }
}
