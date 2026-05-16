/*
Modele exceções para um sistema de pagamentos: PaymentException (base unchecked),
com subclasses: InsufficientFundsException (inclui amount faltante),
CardExpiredException, FraudSuspectedException. Crie uma NetworkException checked
separada (para timeout de gateway). Implemente PaymentService.process() que pode
lançar qualquer uma. Mostre no caller como tratar cada tipo diferentemente com
múltiplos catches em ordem do mais específico ao mais geral.

Data: 16/05/2026
 */


// Exceção Checked (Exige declaração no 'throws' e tratamento obrigatório)
class NetworkException extends Exception {
    public NetworkException(String message) {
        super(message);
    }
}

// Exceção Base Unchecked (Não exige tratamento obrigatório)
class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }
}

// Subclasses de PaymentException
class InsufficientFundsException extends PaymentException {
    private final double missingAmount; // Valor faltante

    public InsufficientFundsException(String message, double missingAmount) {
        super(message);
        this.missingAmount = missingAmount;
    }

    public double getMissingAmount() {
        return missingAmount;
    }
}
class CardExpiredException extends PaymentException {
    public CardExpiredException(String message) {
        super(message);
    }
}
class FraudSuspectedException extends PaymentException {
    public FraudSuspectedException(String message) {
        super(message);
    }
}
class PaymentService {
    // O throws NetworkException é obrigatório aqui
    public void process(String cenario) throws NetworkException {
        System.out.println("Processando pagamento: [" + cenario + "]");

        switch (cenario) {
            case "SEM_SALDO":
                // Faltam R$ 150.50 para completar a transação
                throw new InsufficientFundsException("Saldo em conta insuficiente.", 150.50);

            case "CARTAO_EXPIRADO":
                throw new CardExpiredException("O cartão de crédito está vencido.");

            case "FRAUDE":
                throw new FraudSuspectedException("Comportamento anômalo detectado. Transação bloqueada.");

            case "TIMEOUT":
                throw new NetworkException("Timeout ao conectar com o Gateway de Pagamento.");

            case "ERRO_DESCONHECIDO_PAGAMENTO":
                throw new PaymentException("Erro genérico na validação do pagamento.");

            case "SUCESSO":
                System.out.println("Pagamento aprovado com sucesso!\n");
                break;

            default:
                throw new IllegalArgumentException("Cenário inválido.");
        }
    }
}


public class Hard {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        // Vamos testar diferentes cenários para ver os catches em ação
        String[] cenarios = {"SEM_SALDO", "TIMEOUT", "CARTAO_EXPIRADO", "ERRO_DESCONHECIDO_PAGAMENTO", "SUCESSO"};

        for (String cenario : cenarios) {
            try {
                service.process(cenario);

            } catch (InsufficientFundsException e) {
                // Muito Específico: Conseguimos acessar métodos exclusivos dessa exceção
                System.err.println("FALHA: " + e.getMessage());
                System.err.println("   -> Recarregue sua conta. Faltam: R$ " + e.getMissingAmount() + "\n");

            } catch (CardExpiredException | FraudSuspectedException e) {
                // Multi-catch (Java 7+): Trata mais de uma exceção específica da mesma forma
                System.err.println("PROBLEMA COM O CARTÃO/SEGURANÇA: " + e.getMessage());
                System.err.println("   -> Por favor, contate a administradora do cartão.\n");

            } catch (PaymentException e) {
                // Mais Geral (Negócio): Captura qualquer outra PaymentException não mapeada acima
                System.err.println("ERRO DE PAGAMENTO GENÉRICO: " + e.getMessage());
                System.err.println("   -> Tente novamente mais tarde com outro método de pagamento.\n");

            } catch (NetworkException e) {
                // Específico Checked: Problema de infraestrutura
                System.err.println("ERRO DE REDE: " + e.getMessage());
                System.err.println("   -> Nosso sistema está instável. Tentando novamente em 5 segundos...\n");

            } catch (Exception e) {
                // O Mais Geral de Todos: Captura qualquer outra falha imprevista (como NullPointerException)
                System.err.println("ERRO CRÍTICO DESCONHECIDO: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
