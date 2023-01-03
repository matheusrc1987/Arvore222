public class Homem extends Pessoa implements ControleDePeso{

    private double peso;
    private double altura;

    public Homem(String nome, int idade, Pessoa pai, Pessoa mae, double peso, double altura) {
        super(nome, idade, pai, mae);
        this.setPeso(peso);
        this.setAltura(altura);
        this.sexo = 'M';
    }

    @Override
    public void calculoIMC() {
        double imc =  this.getPeso()/(this.getAltura()*this.getAltura());
        String expressaoIMC = "O IMC de " + this.getNome() + " é " + String.format("%.3f", imc);
        if (imc < 18.5) {
            expressaoIMC += " (peso baixo)";
        } else if (imc < 25) {
            expressaoIMC += " (peso normal)";
        } else if (imc < 30) {
            expressaoIMC += " (sobrepeso)";
        } else if (imc < 40) {
            expressaoIMC += " (obesidade)";
        } else {
            expressaoIMC += " (obesidade grave)";
        }
        System.out.println(expressaoIMC);
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
		
    	if (peso > 0) {
    		this.peso = peso;
    	} else {
    		System.out.println("O peso deve ser maior que zero.");
    	}
    	
	}

	public void setAltura(double altura) {
		if (altura > 0) {
			this.altura = altura;
		} else {
			System.out.println("Altura deve ser maior que zero.");
		}
		
		
	}

	public double getAltura() {
        return altura;
    }
}
