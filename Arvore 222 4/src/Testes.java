
public class Testes {

	public static void main(String[] args) {
		 Pessoa donaNeves = new Mulher("Dona Neves", 80, null, null);
		 Pessoa donaNeves2 = new Mulher("Dona Neves", 80, null, null);
		Homem quicoPai = new Homem("Quico Pai", 50, null, donaNeves, 67, 1.54);
		 
		 System.out.println(quicoPai.getAltura());
		 System.out.println(quicoPai.getPeso());

	}

}
