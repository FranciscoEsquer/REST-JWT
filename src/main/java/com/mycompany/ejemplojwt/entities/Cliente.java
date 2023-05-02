/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemplojwt.entities;

import java.io.Serializable;
/**
 *
 * @author Felix
 */

public class Cliente implements Serializable {

    private int id;
    private String nombre;
    private String RFC;

    public Cliente(){}

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", RFC=" + RFC + '}';
    }
    
    public Cliente(int id, String nombre, String RFC) {
        this.id = id;
        this.nombre = nombre;
        this.RFC = RFC;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }
    
}
