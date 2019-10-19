package eu.jrie.nasa.spaceapps.fireshield.service;

import eu.jrie.nasa.spaceapps.fireshield.model.Position;
import eu.jrie.nasa.spaceapps.fireshield.model.User;
import eu.jrie.nasa.spaceapps.fireshield.respository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    private UserRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String FB_SERVICE_URL = "https://verbose-quartz.glitch.me/users";

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 5000)
    public void fetchNewUsers() {
        NewUsersMessage newUsers = restTemplate.getForEntity(FB_SERVICE_URL, NewUsersMessage.class).getBody();
        if(newUsers != null) {
            if(newUsers.users.size() > 0) {
                newUsers.users.forEach(u -> repository.insert(new User("u", u.psId, u.position, 500_000)));
                System.out.println("added new users");
            }
        }
    }

    private static class NewUsersMessage {
        private List<NewUser> users;

        public NewUsersMessage() {
        }

        public NewUsersMessage(List<NewUser> users) {
            this.users = users;
        }

        public List<NewUser> getUsers() {
            return users;
        }

        public void setUsers(List<NewUser> users) {
            this.users = users;
        }
    }

    private static class NewUser {
        private String psId;
        private Position position;

        public NewUser() {
        }

        public NewUser(String psId, Position position) {
            this.psId = psId;
            this.position = position;
        }

        public String getPsId() {
            return psId;
        }

        public void setPsId(String psId) {
            this.psId = psId;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }
    }
}
