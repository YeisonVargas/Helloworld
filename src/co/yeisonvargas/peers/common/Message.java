/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.yeisonvargas.peers.common;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author yeison_vargas
 */
public class Message implements Serializable, Comparable<Message> {
    
    private String content;
    private String username;
    private Date date;

    public Message(String content, String username) {
        this.content = content;
        this.username = username;
        this.date = new Date();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Message o) {
        if(this.date.after(o.date)) {
            return 1;
        }
        
        if(this.date.before(o.date)) {
            return -1;
        }
        
        return 0;
    }
    
    
    
}
