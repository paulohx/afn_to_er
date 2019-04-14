package org.afn;

public class Principal {
    
    public static void main(String[] args) {
        
        /*Cria um objeto do tipo afn*/
        AFN afn = new AFN();
        
        /*Cria um objeto para iniciar a leitura do arquivo*/
        ReadFile leitura = new ReadFile();
        
        /*Requisito - Receber o nome do arquivo por linha de comando.*/
        afn = leitura.Leitura(args[0], afn);
        
        /*Cria um objeto para manipular os arquivos que vao ser gerados*/
        Graphviz teste = new Graphviz();
        
        /*A primeira imagem e o afn lido*/
        teste.dotAFN(afn, "Afn lido do arquivo.", "afn.dot");
        teste.dotToPNG("afn.dot", "afn.png");
        
        /*Cria um objeto para comecar o processo de remocao dos estados*/
        RemovalAlgorithm remove = new RemovalAlgorithm(afn);

        /*Insere no afn um novo estado inicial, ligando os antigos iniciais a ele*/
        remove.putNewInitial();
        
        /*Insere no afn um novo estado final, ligando os antigos finais a ele*/
        remove.putNewFinal();
        
        /*Retorna o afn para manipular ele aqui de fora*/
        afn = remove.getAfn();
        
        /*A segunda imagem e o afn apos ter sido adicionado o novo estado inicial e o novo final*/
        teste.dotAFN(afn, "Adicionando estado inicial e final.", "afn1.dot");
        teste.dotToPNG("afn1.dot", "afn1.png");
                
        for (int i = 0; i < afn.getRemovedState().size(); i++) {

            /*Antes de remover o estado gera uma nova imagem*/
            teste.dotAFNColorindo(afn, "Antes de remover " + afn.getRemovedState().get(i).getNome() + ".", "afn" + ((i + 2) + i) + ".dot", afn.getRemovedState().get(i));
            teste.dotToPNG("afn" + ((i + 2) + i) + ".dot", "afn" + ((i + 2) + i) + ".png");
            
            /*Removendo cada estado na ordem informada no arquivo*/
            afn = remove.RemovendoEstado(afn.getRemovedState().get(i));
            
            /*A cada estado removido e gerado uma nova imagem*/
            teste.dotAFN(afn, "Depois de remover " + afn.getRemovedState().get(i).getNome() + ".", "afn" + ((i + 3) + i) + ".dot");
            teste.dotToPNG("afn" + ((i + 3) + i) + ".dot", "afn" + ((i + 3) + i) + ".png");
        }
        
        /*Requisito - Exibir a expressao regular na tela*/
        System.out.println("Expressão regular equivalente: " + afn.getDelta().get(0).getValue());
    }
}