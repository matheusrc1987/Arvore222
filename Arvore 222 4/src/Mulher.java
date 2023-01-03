public class Mulher extends Pessoa{
    public Mulher(String nome, int idade, Pessoa pai, Pessoa mae) {
        super(nome, idade, pai, mae);
        this.sexo = 'F';
    }
}
