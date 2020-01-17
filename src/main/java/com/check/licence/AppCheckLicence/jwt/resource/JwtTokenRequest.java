package com.check.licence.AppCheckLicence.jwt.resource;

import java.io.Serializable;

public class  JwtTokenRequest implements Serializable {
  
  private static final long serialVersionUID = -5616176897013108345L;

  private String username;
    private String password;
    /*{
    	"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTc5MzUzODg0LCJpYXQiOjE1Nzg3NDkwODR9.wSxzQBsYRxROSvUTBO3pZkBNFqD-5drNrJUbZtQd54piTQa8nVp2U9VxURCXdEU1YpK92Zv5Tthnq4sehMpDXw"
    	}
    	jwt.io // mySecret
    	Autherization : Bearer token
*/
    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

