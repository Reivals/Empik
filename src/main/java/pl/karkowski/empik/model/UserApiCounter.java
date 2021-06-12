package pl.karkowski.empik.model;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Michal on 11.06.2021
 */

@Entity
@Getter
public class UserApiCounter {
    @Id
    private String login;
    private Long requestCount;

    public UserApiCounter() {
        this.requestCount = 0L;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void incrementCounter() {
        requestCount++;
    }

    @Override
    public String toString() {
        return "UserApiCounter{" +
                "login='" + login + '\'' +
                ", requestCount=" + requestCount +
                '}';
    }
}
