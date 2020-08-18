package util;

import com.sun.mail.imap.IdleManager;
import javax.mail.*;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;
import java.io.IOException;import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Gmail Service Object class
 */
public class GMailService {
    String host = "imap.gmail.com";
    String user;
    String pass;


    private volatile boolean isMessageReceived;
    private volatile String messageString;

    private Folder inboxFolder;
    private IdleManager idleManager;

    /**
     * Example of util.GMailService usage
     * @param args - dummy args for main()
     */
    public static void main(String[] args) {
        String messageSubject = "...";
        String messageTo = "...";
        String messageFrom = "...";

        GMailService gMailService = new GMailService();
        gMailService.connect();
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println("Content: " + message);
    }

    /**
     * Default util.GMailService constructor with predefined user/pass credentials
     */
    public GMailService(){
        this.user = "user@gmail.com";
        this.pass = "password";
    }

    /**
     * Custom util.GMailService constructor that allows to set user/pass credentials
     * @param user - gMail acc username
     * @param pass - gMail acc pass
     */
    public GMailService(String user, String pass){
        this.user = user;
        this.pass = pass;
        System.out.println(user +", " +pass);
    }

    /**
     * Method for connection establishment to Gmail service
     */
    public synchronized void connect() {
        Properties properties = new Properties();
        properties.put("mail.imap.usesocketchannels", "true");
        properties.put("mail.imap.ssl.enable", "true");
        properties.put("mail.store.protocol", "imap");

        System.out.println("connect" + user + ", " + pass);

        try {
            Session session = Session.getInstance(properties);
            Store store = session.getStore();
            try {
                store.connect(host, user, pass);
            } catch (AuthenticationFailedException e) {
                throw new Exception("Make sure 'Allow less secure apps' is 'ON' on gmail account here "
                        + "https://myaccount.google.com/lesssecureapps" + "\n" + e.getMessage());
            }

            ExecutorService executorService = Executors.newCachedThreadPool();
            idleManager = new IdleManager(session, executorService);

            inboxFolder = store.getFolder("inbox");
            System.out.println("inboxFolder - " + inboxFolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get text of email in html format
     * @param messageSubject - a subject of message that is expected to receive
     * @param messageTo - a recepient of message that is expected to receive
     * @param messageFrom - a sender of message that is expected to receive
     * @param timeoutInSec - time period to wait for a message
     * @return - text of message in html format if message is received or a StackTrace of exception
     */
    public String waitMessage(String messageSubject, String messageTo, String messageFrom,
                              long timeoutInSec){
        System.out.println("start wait");
        System.out.println("messageSubject: "+messageSubject + ", messageTo: "+messageTo+", messageFrom: "+messageFrom);
        try {
            inboxFolder.open(Folder.READ_WRITE);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("point 1");
        inboxFolder.addMessageCountListener(new MessageCountAdapter() {
            public synchronized void messagesAdded(MessageCountEvent ev) { //synchronized
                System.out.println("point 2");
                try {
                    Message[] messages = ev.getMessages();
                    System.out.println("messages.length - " + messages.length);
                    for (Message message : messages) {
                        String from = message.getFrom()[0].toString();
                        String to = message.getAllRecipients()[0].toString();
                        String subject = message.getSubject().toString();
                        System.out.println();
                        System.out.println(message.getFrom()[0].toString());
                        System.out.println(message.getAllRecipients()[0].toString());
                        System.out.println(message.getSubject().toString());
                        System.out.println();

                        if (from.contains(messageFrom) && to.contains(messageTo) && subject.contains(messageSubject)) {
                            isMessageReceived = true;
                            System.out.println("isMessageReceived = true");
                            System.out.println("message: "+message);
                            messageString = getText(message);
                            idleManager.stop();
                        }
                    }

                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        try {
            idleManager.watch(inboxFolder);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis();
        while (true) {
            if (isMessageReceived && messageString != null) {
                break;
            } else if ((System.currentTimeMillis() - startTime) > timeoutInSec * 1000) {
                idleManager.stop();
                break;
            }
        }
        System.out.print(messageString);
        return messageString;
    }

    private boolean textIsHtml = false;


    /**
     * Return the primary text content of the message.
     */
    private String getText(Part p) throws
            MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            String s = (String)p.getContent();
            textIsHtml = p.isMimeType("text/html");
            return s;
        }

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart)p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }

        return null;
    }

}