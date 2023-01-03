import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// OBS: em tese, é boa prática chamar os getters e setters
// mesmo dentro da mesma classe, mas não fizemos para simplificar

public abstract class Pessoa {
	// OBS: Como teremos subclasses, a visibilidade das variáveis
	// de instância será protected
	protected String nome;
	protected int idade;
	protected Pessoa conjuge;
	protected List<Pessoa> exConjuges = new ArrayList<>();
	protected Pessoa pai;
	protected Pessoa mae;

	protected char sexo;
	
	// Esta lista será compartilhada entre todas as pessoas
	// Esta lista simula um BD
	protected static List<Pessoa> todasPessoas = new ArrayList<>();
	
	public Pessoa(String nome, int idade, Pessoa pai, Pessoa mae) {
		this.nome = nome;
		this.setIdade(idade);
		this.pai = pai;
		this.mae = mae;
			
		todasPessoas.add(this);
		// OBS: na vida real, poderíamos adicionar esta pessoa no BD
	}

	// Métodos
	
	public void divorciar(Pessoa p) {
		
		// só posso me divorciar de p se eu estiver casado 
		// com p e p comigo
		if( this.getConjuge() == null ) {
			System.out.println(this.getNome() + " é tão traumatizado "
					+ "com casamento que quer se divorciar sem "
					+ "ser casado(a)!!!");
			return;
		}
		// TODO: fazer outras verificações 
		
		if( this == p.getConjuge() ) { // pode divorciar
			this.getExConjuges().add(p); // p entra como ex de this
			p.getExConjuges().add(this); // this entra como ex de p
			this.setConjuge(null); // liberta this
			p.setConjuge(null); // liberta p
			System.out.println(this.nome + " e " + p.nome + 
					" entraram no Tinder! Divórcio realizado!");
			
		} else {
			System.out.println("### ERRO: " + this.getNome()
					+ " e " + p.getNome() +
					" não podem divorciar pois não estão casados");
		}
		
		
	}


	public void verificarParentesco(Pessoa pessoa) {
		String parentesco = this.getParentesco(pessoa);
		if (parentesco != null) {
			System.out.println(parentesco);
		}
	}

	private boolean isParente(Pessoa pessoa) {
		String relacaoParentesco = this.getParentesco(pessoa);
		return (relacaoParentesco != null &&
				!relacaoParentesco.endsWith(" não são parentes"));
	}

	private String getParentesco(Pessoa pessoa) {
		// OBS: this é xxx de p

		String relacao;

		// OBS: vocês podem encontrar alguns erros de
		// NullPointerException neste método - tratar!
		if (pessoa == null) {
			System.out.println("### ERRO: Não é possível comparar parentesco com nulo");
			return null;
		}
		
		if( this == pessoa ) {
			System.out.println("### ERRO: Você é você!");
			return null;
		} else if( this == pessoa.getConjuge() ) { // marido, esposa
			relacao = this.sexo == 'M' ? " é marido de ": " é esposa de ";
		} else if( this == pessoa.getPai() || this == pessoa.getMae()) { // pai
			relacao = getExpressaoBaseadaEmSexo(" é pai de ", " é mãe de ");
		} else if(
			( (this.getPai() != null && pessoa.getPai() != null) &&
					this.getPai() == pessoa.getPai() ) ||
			( (this.getMae() != null && pessoa.getMae() != null) &&
					this.getMae() == pessoa.getMae() ) ) { // irmão, irmã
			relacao = getExpressaoBaseadaEmSexo(" é irmão de ", " é irmã de ");
		} else if(
				((pessoa.getMae() != null && this == pessoa.getMae().getMae()) ||
						(pessoa.getPai() != null && this == pessoa.getPai().getMae()))  ||
						((pessoa.getMae() != null && this == pessoa.getMae().getPai()) ||
								(pessoa.getPai() != null && this == pessoa.getPai().getPai()))
		) { // avó/avô
			relacao = getExpressaoBaseadaEmSexo(" é avô de ", " é avó de ");
		} else if(this.getPai() == pessoa || this.getMae() == pessoa) { // filho
			relacao = getExpressaoBaseadaEmSexo(" é filho de ", " é filha de ");
		} else if (
				((pessoa.getPai() != null && pessoa.pai.pai != null) &&
						(this.getPai() == pessoa.pai.getPai() || this.getMae() == pessoa.getPai().getMae())) ||
				((pessoa.getMae() != null && pessoa.getMae().getMae() != null) &&
						(this.getPai() == pessoa.getMae().getPai() || this.getMae() == pessoa.getMae().getMae()))
		) { // tio/tia
			relacao = getExpressaoBaseadaEmSexo(" é tio de ", " é tia de ");
		} else if (
				((this.getPai() != null && this.getPai().getPai() != null) &&
						(pessoa.getPai() == this.getPai().getPai() || pessoa.getMae() == this.getPai().getMae())) ||
						((this.getMae() != null && this.getMae().getMae() != null) &&
								(pessoa.getPai() == this.getMae().getPai() || pessoa.getMae() == this.getMae().getMae()))
		) { // sobrinho/sobrinha
			relacao = getExpressaoBaseadaEmSexo(" é sobrinho de ", " é sobrinha de ");
		} else if(
				((this.getMae() != null && pessoa == this.getMae().getMae()) ||
						(this.getPai() != null && pessoa == this.getPai().getMae())) ||
						((this.getMae() != null && pessoa == this.getMae().getPai()) ||
						(this.getPai() != null && pessoa == this.getPai().getPai()))
				) { // neto/neta
			relacao = getExpressaoBaseadaEmSexo(" é neto de ", " é neta de ");
		} else {
			// sem parentesco
			return this.getNome() + " e " + pessoa.getNome() + " não são parentes";
		}
		return this.getNome() + relacao + pessoa.getNome();
	}

	private String getExpressaoBaseadaEmSexo(String expressaoMasculina, String expressaoFeminina) {
		return this.sexo == 'M' ? expressaoMasculina : expressaoFeminina;
	}

	public void listarFilhos() {
		//OBS: na vida real, poderíamos fazer um select no BD
		
		// imprime a lista de filhos
		System.out.println("== Lista de filhos de " + this.getNome());
		
		// percorrer a lista todasPessoas
		for(Pessoa daVez : Pessoa.todasPessoas) {
			// se o pai ou a mãe da pessoa "da vez" for eu
			if( this == daVez.getPai() ||
				this == daVez.getMae() ) {
				// essa pessoa é meu/minha filho/a
				// imprimir
				System.out.println(daVez.getNome());
			}
		}
		

		

		
	}

	public void listarCasamentos(){
		if (this.getConjuge() == null && this.getExConjuges().isEmpty()) {
			System.out.println(this.getNome() + " não se casou ainda");
		} else if (this.getConjuge() != null && this.getExConjuges().isEmpty()) {
			System.out.println(this.getNome() + " é casado(a) com " + this.getConjuge().getNome());
		} else if (getConjuge() != null) {
			String mensagem = this.getNome() + " é casado(a) com " + this.getConjuge().getNome();
			mensagem += " e já foi casado(a) com: ";
			for (Pessoa ex: this.getExConjuges()) {
				mensagem += ex.getNome() + ", ";
			}
			System.out.println(mensagem.substring(0, mensagem.length() - 2));

		} else {
			String mensagem = this.getNome() + " está divorciado(a)";
			mensagem += " e já foi casado(a) com: ";
			for (Pessoa ex: exConjuges) {
				mensagem += ex.getNome() + ", ";
			}
			System.out.println(mensagem.substring(0, mensagem.length() - 2));
		}

	}
	
	public void casar(Pessoa p) {

		// Não pode casar com null
		if( p == null ) {
			System.out.println("# ERRO! Não pode casar com null");
			return;
		}

		// Não pode casar se já for casado
		if( this.getConjuge() != null || p.getConjuge() != null ) {
			System.out.println("# ERRO! Não pode casar se já for casado");
			return;
		}
		// Não pode casar consigo mesmo
		if( this == p ) {
			System.out.println("# ERRO! Não pode casar consigo mesmo");
			return;
		}

		// não pode casar se tiver menos de 16 anos
		if (this.getIdade() < 16 || p.getIdade() < 16){
			System.out.println("# ERRO! Não pode casar se tiver menos de 16 anos");
			return;
		}
		
		// não pode casar com parente
		if (this.isParente(p)) {
			System.out.println("# ERRO! Não pode casar com parente");
			return;
		}
		
		// Finalmente pode casar
		this.setConjuge(p);
		p.setConjuge(this);
		System.out.println(this.getNome() + " e " + p.getNome() + " estão casados");
		
	}
	
	public void verificarEstadoCivil() {
		// Solteiro - conjuge null e lista vazia
		// Casado - conjuge != null
		// Divorciado - conjuge null e lista com alguém
		if( this.getConjuge() == null && this.getExConjuges().isEmpty() ) {
			System.out.println(this.getNome() + " é solteiro(a)");
		} else if( this.getConjuge() != null ) {
			System.out.println(this.getNome() + " é casado(a)");
		} else if( this.getConjuge() == null 
				&& !this.getExConjuges().isEmpty()) {
			System.out.println(this.getNome() + " é divorciado(a)");
		} else {
			// OBS: em teoria nunca vai entrar aqui, mas deixamos preparado para expansões futuras
			System.out.println("Estado civil desconhecido");
		}
		
		// TODO opcional: verificar se a pessoa é homem 
		// ou mulher e exibir a mensagem personalizada
	}
	
	// Getters e Setters

	public static Pessoa gerarNovaPessoa(Pessoa pai, Pessoa mae, String nome, char sexo){

		if (pai == null && mae == null) {
			System.out.println("### ERRO: pai ou mãe devem ser não nulos");
			return null;
		}

		if (sexo == 'F'){
			return new Mulher(nome, 0, pai, mae);
		} else if (sexo == 'M') {
			double peso = 3 * Math.random() + 2 ;
			double altura = 0.2 * Math.random() + 0.4 ;
			return new Homem(nome, 0, pai, mae, peso , altura);
		} else {
			System.out.println("### ERRO: sexo informado deve ser 'F' ou 'M'");
			return null;
		}
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		
		if (idade > 0) {
			this.idade = idade;
		} else {
			System.out.println("Idade deve ser maior que zero");
		}
		
	}
	public Pessoa getConjuge() {
		return conjuge;
	}
	public void setConjuge(Pessoa conjuge) {
		this.conjuge = conjuge;
	}
	public List<Pessoa> getExConjuges() {
		return exConjuges;
	}
	public void setExConjuges(List<Pessoa> exConjuges) {
		this.exConjuges = exConjuges;
	}
	public Pessoa getPai() {
		return pai;
	}
	public void setPai(Pessoa pai) {
		this.pai = pai;
	}
	public Pessoa getMae() {
		return mae;
	}
	public void setMae(Pessoa mae) {
		this.mae = mae;
	}
	public static List<Pessoa> getTodasPessoas() {
		return todasPessoas;
	}
	public static void setTodasPessoas(List<Pessoa> todasPessoas) {
		Pessoa.todasPessoas = todasPessoas;
	}

	
	public boolean equals (Pessoa p) {
		if (this.nome == p.nome && this.idade == p.idade && this.pai == p.pai && this.mae == p.mae && this.sexo == p.sexo 
				&& this.conjuge == p.conjuge) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	}
	
