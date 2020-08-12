package com.recomovie.service;/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */


import com.recomovie.dao.UsuarioDAO;
import com.recomovie.dto.UsuarioDTO;
import com.recomovie.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDao;

    public void crearUsuario(UsuarioDTO u) throws ParseException {
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.seteMail(u.geteMail());
        usuarioNuevo.setNombreUsuario(u.getNombreUsuario());
        usuarioNuevo.setPassword(u.getPassword());
        usuarioNuevo.setFechaNacimineto(DateFormat.getDateInstance().parse(u.getFechaNacimiento()));

        usuarioDao.creaUsuario(usuarioNuevo);
    }


}
