package org.afn;

public class Estados {
    
    private int id;
    private String nome;
    private boolean isFinal;
    private boolean isInicial;

    public Estados(String nome) {
        this.id        = -1;
        this.nome      = nome;
        this.isFinal   = false;
        this.isInicial = false;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setIsFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean isInicial() {
        return isInicial;
    }

    public void setIsInicial(boolean isInicial) {
        this.isInicial = isInicial;
    }

    @Override
    public String toString() {
        return "Nome:    " + this.nome      + "\n" +
               "Inicial: " + this.isInicial + "\n" +
               "Final:   " + this.isFinal   + "\n";
    }
}