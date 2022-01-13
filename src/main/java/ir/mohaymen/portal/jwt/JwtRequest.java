package ir.mohaymen.portal.jwt;

import lombok.Data;

import java.io.Serializable;


/** 4.
 * This class is required for storing the username and password we recieve from the client.
 * */
@Data
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest() {

    }

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

}
