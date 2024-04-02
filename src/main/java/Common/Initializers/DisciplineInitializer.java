package Common.Initializers;

import Entities.Discipline;

import java.util.ArrayList;
import java.util.HashMap;

public class DisciplineInitializer {
    public static ArrayList<Discipline> initializeDisciplines() {
        // 25 disciplinas
        Discipline discipline1 = new Discipline("EMPREENDEDORISMO", 1);
        Discipline discipline2 = new Discipline("FUNDAMENTOS DE SISTEMAS PARA INTERNET", 1);
        Discipline discipline3 = new Discipline("LÓGICA DE PROGRAMAÇÃO", 1);
        Discipline discipline4 = new Discipline("PROJETO WEBSITE ESTÁTICO", 1);
        Discipline discipline5 = new Discipline("REQUISITOS", 1);
        Discipline discipline6 = new Discipline("SISTEMAS OPERACIONAIS", 2);
        Discipline discipline7 = new Discipline("ALGORITMOS E PROGRAMAÇÃO", 2);
        Discipline discipline8 = new Discipline("FUNDAMENTOS DE BANCO DE DADOS", 2);
        Discipline discipline9 = new Discipline("JAVASCRIPT BÁSICO", 2);
        Discipline discipline10 = new Discipline("PROGRAMAÇÃO ORIENTADA A OBJETOS 1", 2);
        Discipline discipline11 = new Discipline("PROJETO SISTEMA WEB MVC E SQL", 3);
        Discipline discipline12 = new Discipline("BANCO DE DADOS AVANÇADO", 3);
        Discipline discipline13 = new Discipline("PROGRAMAÇÃO ORIENTADA A OBJETOS 2", 3);
        Discipline discipline14 = new Discipline("PROJETO BACK-END MONOLÍTICO COM ORM", 3);
        Discipline discipline15 = new Discipline("PROJETO FRONT-END WEB JAVASCRIPT", 3);
        Discipline discipline16 = new Discipline("TESTES AUTOMATIZADOS", 4);
        Discipline discipline17 = new Discipline("BANCO DE DADOS NOSQL", 4);
        Discipline discipline18 = new Discipline("INTERFACE HUMANO-COMPUTADOR", 4);
        Discipline discipline19 = new Discipline("PROJETO APLICAÇÃO PARA DISPOSITIVOS MÓVEIS", 4);
        Discipline discipline20 = new Discipline("PROJETO BACK-END MICROSSERVIÇOS E NOSQL", 4);
        Discipline discipline21 = new Discipline("SISTEMAS DISTRIBUÍDOS", 5);
        Discipline discipline22 = new Discipline("INTELIGÊNCIA COMPUTACIONAL", 5);
        Discipline discipline23 = new Discipline("LIBRAS", 5);
        Discipline discipline24 = new Discipline("PRODUÇÃO DE RELATÓRIO, ARTIGO E MONOGRAFIA", 5);
        Discipline discipline25 = new Discipline("SEGURANÇA EM SISTEMAS PARA INTERNET", 5);

        ArrayList<Discipline> disciplines = new ArrayList<>();

        disciplines.add(discipline1);
        disciplines.add(discipline2);
        disciplines.add(discipline3);
        disciplines.add(discipline4);
        disciplines.add(discipline5);
        disciplines.add(discipline6);
        disciplines.add(discipline7);
        disciplines.add(discipline8);
        disciplines.add(discipline9);
        disciplines.add(discipline10);
        disciplines.add(discipline11);
        disciplines.add(discipline12);
        disciplines.add(discipline13);
        disciplines.add(discipline14);
        disciplines.add(discipline15);
        disciplines.add(discipline16);
        disciplines.add(discipline17);
        disciplines.add(discipline18);
        disciplines.add(discipline19);
        disciplines.add(discipline20);
        disciplines.add(discipline21);
        disciplines.add(discipline22);
        disciplines.add(discipline23);
        disciplines.add(discipline24);
        disciplines.add(discipline25);

        return disciplines;
    }
}
