package com.example.recyclerview;

public class Usuario {
    private String nome;
    private String email;
    private int image;

    public Usuario(String nome, String email, int image) {
        this.nome = nome;
        this.email = email;
        this.image = image;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
