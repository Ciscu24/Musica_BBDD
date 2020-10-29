package com.proyectociscu.musica_bbdd;

import com.proyectociscu.musica_bbdd.utils.ConnectionUtil;

public class HolaInicio {
    public static void main(String[] args) {
        System.out.println(ConnectionUtil.getConnection());
    }
}
