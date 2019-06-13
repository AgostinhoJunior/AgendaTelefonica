/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.uteis;

/**
 *
 * @author william.mauro
 */
public class Texto {

    // Mensagens de Sucesso Padrões
    public static final String SUCESSO_CADASTRAR = "Cadastrado com Sucesso!";

    public static final String SUCESSO_EDITAR = "Editado com Sucesso!";

    public static final String SUCESSO_DELETAR = "Excluido com Sucesso!";

    public static final String SUCESSO_DESATIVAR = "Desativado com Sucesso!";

    public static final String SUCESSO_REMOVER = "Sucesso ao remover!";

    // Mensagens de Erro Padrões
    public static final String ERRO_DELETAR = "Erro ao excluir";

    public static final String ERRO_CADASTRAR = "Erro ao Cadastrar!";

    public static final String ERRO_EDITAR = "Erro ao Editar!";

    public static final String ERRO_DESATIVAR = "Erro ao desativar!";

    // Mensagens de Campos Vazios Padrões
    public static final String VAZIO_CAMPO = "O Campo está vazio!";

    public static final String VAZIO_CAMPOS = "Algum dos campos estão vazios!";

    // Mensagem de Campos ou Componentes não selecionados.
    public static final String SELECIONADA_LINHA = "Nenhuma Linha Selecionada";

    public static final String SELECIONADA_COLUNA = "Nenhuma Coluna Selecionada";

    public static final String SELECIONADA_BOTAO = "Nenhum Botão Selecionado";

    // Perguntas de Confirmação Padrões.
    public static final String PERGUNTA_EXCLUIR = "Você deseja Realmente excluir ";

    public static final String PERGUNTA_DESATIVAR = "Você deseja Realmente desativar ";

    public static final String PERGUNTA_EDITAR = "Você deseja Realmente editar ";

    public static final String PERGUNTA_REMOVER_ITEM_ENTRADA = "Você deseja remover este item da lista de Entrada?";

    public static final String PERGUNTA_REMOVER_ITEM_VENDA = "Você deseja remover este item da lista de Venda?";

    public static final String ERRO_USUARIO = "Usuário não encontrado!";

    public static final String SENHA_USUARIO = "A senha não bate com o login de usuário encontrado!";

    public static final String ERRO_INTERFACE = "Atenção , Este sistema operacional "
            + "não suporta a mudança de interface pela JVM , "
            + "por favor contate o desenvolvedor.";

    public static final String ERRO_COVERTER_CAMPO_DATA = "Atenção, Erro ao converter o campo para data";

    public static final String ERRO_COVERTER_CAMPO_CEP = "Atenção, o campo de [CEP] deve conter 8 digitos Ex:[00000000]";

    // Mensagens de Bean Categoria 
    public static final String CATEGORIA_NULL = "Para criar uma categoria, preencha todos os campos obrigatórios";

    public static final String CATEGORIA_NOME = "O campo [Nome] não pode conter ser vazio ou conter somente espaços";

    public static final String CATEGORIA_NOME_TAMANHO = "O campo [Nome] deve ter entre 5 e 50 caracteres";

    public static final String CATEGORIA_ATIVO_NULO = "O campo [Ativo] não pode ser nulo";

}
