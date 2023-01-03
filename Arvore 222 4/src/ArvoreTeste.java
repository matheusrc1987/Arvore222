public class ArvoreTeste {

    public static void main(String[] args) {
        relacionamentosArvoreChaves();
    }

    private static void relacionamentosArvoreChaves(){
        // instanciando pessoas da árvore
        Pessoa donaNeves = new Mulher("Dona Neves", 80, null, null);
        Pessoa sirNeves = new Homem("Sir Neves", 82, null, null, 95, 1.94);
        Pessoa donaMadruga = new Mulher("Dona Madruga", 68, null, null);
        Pessoa sirMadruga = new Homem("Sir Madruga", 70, null, null, 50, 1.6);
        Pessoa quicoPai = new Homem("Quico Pai", 50, sirNeves, donaNeves, 67, 1.54);
        Pessoa donaFlorinda = new Mulher("Dona Florinda", 49, null, null);
        Pessoa professorGirafales = new Homem("Professor Girafales", 50, null, null, 110, 1.99);
        Pessoa maeChiquinha = new Mulher("Mãe da Chiquinha", 47, sirNeves, donaNeves);
        Pessoa bruxaDo71 = new Mulher("Bruxa do 71", 50, null, null);
        Pessoa seuMadruga = new Homem("Seu Madruga", 48, sirMadruga, donaMadruga, 60, 1.57);
        Pessoa chiquinha = new Mulher("Chiquinha", 8, seuMadruga, maeChiquinha);
        Pessoa quico = new Homem("Quico", 10, quicoPai, donaFlorinda, 55, 1.53);
        Pessoa kika = new Mulher("Kika", 8, quicoPai, donaFlorinda);
        Pessoa chaves = new Homem("Chaves", 8, seuMadruga, null, 50, 1.45);

        System.out.println("-------------------------");
        System.out.println("Sistema Árvore Chaves");
        System.out.println("-------------------------");


        // construindo relacionamentos adicionais entre pessoas (casamentos e divórcios)
        // Casamentos
        System.out.println("\n-------------------------");
        System.out.println("Casamentos");
        System.out.println("-------------------------");
        donaNeves.casar(sirNeves);
        donaMadruga.casar(sirMadruga);
        quicoPai.casar(donaFlorinda);
        seuMadruga.casar(maeChiquinha);

        System.out.println("\n-------------------------");
        System.out.println("Divórcios");
        System.out.println("-------------------------");
        donaFlorinda.divorciar(quicoPai);
        seuMadruga.divorciar(maeChiquinha);

        System.out.println("\n-------------------------");
        System.out.println("Novos casamentos");
        System.out.println("-------------------------");
        donaFlorinda.casar(professorGirafales);
        seuMadruga.casar(bruxaDo71);

        System.out.println("\n-------------------------");
        System.out.println("Testes de relacionamentos entre personagens");
        System.out.println("-------------------------");
        chaves.verificarParentesco(donaFlorinda);
        quicoPai.verificarParentesco(quico);
        donaFlorinda.verificarParentesco(quico);
        professorGirafales.verificarParentesco(donaFlorinda);
        donaFlorinda.verificarParentesco(professorGirafales);
        chaves.verificarParentesco(seuMadruga);
        chiquinha.verificarParentesco(seuMadruga);
        sirNeves.verificarParentesco(chiquinha);
        donaNeves.verificarParentesco(chiquinha);
        quicoPai.verificarParentesco(chiquinha);
        maeChiquinha.verificarParentesco(quico);
        quico.verificarParentesco(maeChiquinha);
        chiquinha.verificarParentesco(quicoPai);
    }
}
