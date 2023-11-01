package com.example.projetfinalandanss;

import java.io.Serializable;

public class User implements Serializable {
    private String Type;
    private String nomUsager;
    private String nomAuComplet;
    private String motPasse;
    private int userId;

    // Constructors
    public User() {
        // Default constructor
    }

    public User(String type, String nomUsager, String nomAuComplet, String motPasse, int userId) {
        this.Type = type;
        this.nomUsager = nomUsager;
        this.nomAuComplet = nomAuComplet;
        this.motPasse = motPasse;
        this.userId = userId;
    }

    // Getters and setters
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getNomUsager() {
        return nomUsager;
    }

    public void setNomUsager(String nomUsager) {
        this.nomUsager = nomUsager;
    }

    public String getNomAuComplet() {
        return nomAuComplet;
    }

    public void setNomAuComplet(String nomAuComplet) {
        this.nomAuComplet = nomAuComplet;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
