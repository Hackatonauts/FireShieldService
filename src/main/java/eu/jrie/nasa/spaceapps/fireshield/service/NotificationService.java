package eu.jrie.nasa.spaceapps.fireshield.service;

import eu.jrie.nasa.spaceapps.fireshield.model.Position;
import eu.jrie.nasa.spaceapps.fireshield.model.User;
import eu.jrie.nasa.spaceapps.fireshield.respository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationService {

    private UserRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    private static int ALERT_RADIUS = 5_000_000;
    private static String ALERT_TEXT = "Warning! Fire reported in your area. More information: ";

    public NotificationService(UserRepository repository) {
        this.repository = repository;
    }

    void sendFireAlerts(final Position firePosition, final String fireId) {
        final List<User> users = repository.findAll();
        GeoService.filterByDistance(users.stream(), User::getPosition, firePosition, ALERT_RADIUS)
                .forEach(u -> sendMessage(u.getFbId(), ALERT_TEXT + " http://192.168.137.82:8080//#/fire/" + fireId));
    }

    private void sendMessage(final String fbId, final String message) {
        final SendMessageRequest request = new SendMessageRequest(
            new SendMessageRequest.Recipient(fbId),
            new SendMessageRequest.Message(message)
        );
        restTemplate.postForEntity(
                "https://graph.facebook.com/v4.0/me/messages?access_token=EAAGFr4oCFcIBALB679HLVRmUq781rLMLSMTEDQA3gFQQJxNCHNALbMBLPgNVqBZCM3fIIMAjLg3j0A4eIzzpCpCw0g2T2qTjH7M9fkrRRIAVEHNZCm8XOZCZB3bpe40AGDznZBEwWwdhjPGLZAXRXsAQPsWP9Cqb9nS8PqvSbWSmTnfDT5LIdbVIZC9pqTPnLgZD",
                request,
                String.class
        );
    }

    private static class SendMessageRequest {

        private Recipient recipient;
        private Message message;

        public SendMessageRequest() {
        }

        public SendMessageRequest(Recipient recipient, Message message) {
            this.recipient = recipient;
            this.message = message;
        }

        public Recipient getRecipient() {
            return recipient;
        }

        public void setRecipient(Recipient recipient) {
            this.recipient = recipient;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        private static class Recipient {
            private String id;

            public Recipient() {}
            public Recipient(String id) {
                this.id = id;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        private static class Message {
            private String text;

            public Message() {}
            public Message(String text) {
                this.text = text;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
