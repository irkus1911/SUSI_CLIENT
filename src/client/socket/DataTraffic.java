/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.socket;

import java.io.ObjectOutputStream;
import java.net.Socket;
import lib.dataModel.User;
import lib.exceptions.ConnectException;
import lib.exceptions.IncorrectEmailException;
import lib.exceptions.IncorrectPasswordException;
import lib.exceptions.IncorrectUserException;
import lib.exceptions.PasswordDontMatchException;
import lib.exceptions.UserDontExistException;
import lib.exceptions.UserExistException;
import lib.interfaces.Logicable;
import lib.message.Message;
import lib.message.Msg;

/**
 *
 * @author Adrian Franco
 */
public class DataTraffic implements Logicable {

    @Override
    public User signIn(User user) throws IncorrectUserException, IncorrectPasswordException, UserDontExistException, PasswordDontMatchException, ConnectException {
        Message msg = new Message();
        Message msg2 = new Message();
        User usr = new User();
        ClientSocket socket = new ClientSocket();

        msg.setUser(user);
        msg.setMsg(Msg.SIGNIN);

        msg2 = socket.createSocket(msg);

        return msg2.getUser();
    }

    @Override
    public User signUp(User user) throws IncorrectUserException, IncorrectPasswordException, IncorrectEmailException, UserExistException, PasswordDontMatchException, ConnectException {

        Message msg = new Message();
        Message msg2 = new Message();
        User usr = new User();
        ClientSocket socket = new ClientSocket();

        msg.setUser(user);
        msg.setMsg(Msg.SIGNUP);

        msg2 = socket.createSocket(msg);
        
        return msg2.getUser();
    }

}
