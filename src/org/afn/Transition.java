package org.afn;

public class Transition {
    
    private Estados origin;
    private Estados end;
    private String value;

    /**
     * Construtor da classe com parâmetros.
     * @param origin O estado de origem da transição.
     * @param end O estado de destino da transição.
     * @param value O valor dessa transição.
     */
    public Transition(Estados origin, Estados end, String value) {
        this.origin = origin;
        this.end    = end;
        this.value  = value;
    }

    /**
     * Pega o estado de origem.
     * @return Estados - Retorna o estado de origem.
     */
    public Estados getOrigin() {
        return origin;
    }

    /**
     * Seta o estado de origem.
     * @param origin O estado a ser setado.
     */
    public void setOrigin(Estados origin) {
        this.origin = origin;
    }

    /**
     * Pega o estado de destino.
     * @return Estados - Retorna o estado de destino.
     */
    public Estados getEnd() {
        return end;
    }

    /**
     * Seta o estado de destino.
     * @param end O estado a ser setado.
     */
    public void setEnd(Estados end) {
        this.end = end;
    }

    /**
     * Pega o valor da transição.
     * @return String - Retorna o valor.
     */
    public String getValue() {
        return value;
    }

    /**
     * Adiciona no valor da transição mais outro valor.
     * @param value O valor a ser adicionado.
     */
    public void setValue(String value) {
        this.value += value;
    }
    
    /**
     * Seta o novo valor na transição.
     * @param value O novo valor a ser setado.
     */
    public void setNewValue(String value) {
        this.value = value;
    }

    /**
     * Utilizada pegar os dados do objeto.
     * @return String - Retorna os dados do objeto em forma de String.
     */
    @Override
    public String toString() {
        return "Origem:    " + this.origin.getNome() + "\n" +
               "Transicao: " + this.value            + "\n" +
               "Destino:   " + this.end.getNome()    + "\n";
    }
}