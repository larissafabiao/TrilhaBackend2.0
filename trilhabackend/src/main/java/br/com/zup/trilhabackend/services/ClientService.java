package br.com.zup.trilhabackend.services;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientService {
    public static String validateCpf(String cpf) {
        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        char dig10, dig11;
        int sum1, sum2, rest, num, weight1, weight2;

        try {
            sum1 = 0;
            sum2 = 0;
            weight1 = 10;
            weight2 = 11;
            for (int i=0; i<9; i++) {
                // transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(cpf.charAt(i) - 48);

                sum1 = sum1 + (num * weight1);
                weight1 = weight1 - 1;
            }

            for(int i = 0; i < 10; i++) {
                num = (int)(cpf.charAt(i) - 48);

                sum2 = sum2 + (num * weight2);
                weight2 = weight2 - 1;
            }

            rest = 11 - (sum1 % 11);
            if ((rest == 10) || (rest == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char)(rest + 48); // converte no respectivo caractere numerico
            }

            rest = 11 - (sum2 % 11);
            if ((rest == 10) || (rest == 11))
                dig11 = '0';
            else dig11 = (char)(rest + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
                return cpf;
            } else {
                throw new IllegalArgumentException("CPF inválido");
            }
        } catch (InputMismatchException erro) {

        }
        throw new IllegalArgumentException("CPF inválido");
    }

    public static int validateAge(int age) {
        if(age < 0) {
            throw new IllegalArgumentException("idade com formato inválido");
        }
        return age;
    }

    public static String validateEmail(String email) {
        checkNull(email, "email");

        boolean isEmailValid = false;
        if (email != null && email.length() > 0) {
            //Determinamos uma expressão padrão que determina um e-mail válido através da String expression
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            //Definimos nossa expressão como um padrão e a tornamos sensível a letras maiusculas
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            //verificamos se o e-mail inserido obedece ao padrão determinado
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailValid = true;
            }
        }

        if(isEmailValid == false) {
            throw new IllegalArgumentException("Email com formato inválido");
        }
        return email;
    }

    public static String checkNull(String string, String paramether) {
        if(string == null) {
            throw new IllegalArgumentException(paramether + " não pode ser nula");
        }
        if (string.isBlank()) {
            throw new IllegalArgumentException(paramether + " precisa possuir caracteres válidos");
        }
        return string;
    }
}
