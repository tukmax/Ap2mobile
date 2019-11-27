package com.example.weather.controller;

import com.example.weather.DAO.CidadeDAO;
import com.example.weather.DBHelper.ConexaoSQLite;
import com.example.weather.models.CidadeModel;

public class CidadeCtrl {

    private CidadeDAO cidadeDAO;

    public CidadeCtrl(ConexaoSQLite conexaoSQLite){
        cidadeDAO = new CidadeDAO(conexaoSQLite);
    }

    public long salvarCidadeCtrl(CidadeModel ccidade){
        return this.cidadeDAO.salvarCidade(ccidade);

    }

}
