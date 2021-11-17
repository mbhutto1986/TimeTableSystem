package com.mbhutto;

import com.mbhutto.gui.Home;

import java.sql.SQLException;

public class Project {
    public static void main (String args[])
    {
        try {
            Home h = new Home();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
