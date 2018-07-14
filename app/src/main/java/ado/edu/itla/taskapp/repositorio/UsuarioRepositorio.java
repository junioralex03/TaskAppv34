package ado.edu.itla.taskapp.repositorio;

import java.util.List;

import ado.edu.itla.taskapp.entidad.Usuario;

public interface UsuarioRepositorio {

   public boolean guardar(Usuario usuario);
    public Usuario buscar1(int id);
    public Usuario buscar(String username);
    List<Usuario> buscarTecnico();
}
