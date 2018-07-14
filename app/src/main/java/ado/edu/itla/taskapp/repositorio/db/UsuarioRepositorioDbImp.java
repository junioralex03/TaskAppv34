package ado.edu.itla.taskapp.repositorio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;


public class UsuarioRepositorioDbImp implements UsuarioRepositorio {

    private ConexionDb conexionDb;
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_EMAIL = "email";
    private static final String CAMPO_CONTRASENA = "contrasena";
    private static final String CAMPO_TIPO_USUARIO = "tipoUsuario";
    private static final String TABLA_NOMBRE = "usuario";

    public UsuarioRepositorioDbImp(Context context) {

        conexionDb = new ConexionDb(context);
    }

    @Override
    public boolean guardar(Usuario usuario) {

      /*  if (usuario.getId() != null && usuario.getId() > 0)
            return actualizar(usuario);*/

        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, usuario.getNombre());
        cv.put(CAMPO_EMAIL, usuario.getEmail());
        cv.put(CAMPO_CONTRASENA, usuario.getContrasena());
        cv.put(CAMPO_TIPO_USUARIO, usuario.getTipoUsuario().name());

        SQLiteDatabase db = conexionDb.getWritableDatabase();
        if (usuario.getId() != null && usuario.getId() > 0){
        int cantidad = db.update(TABLA_NOMBRE, cv, "id = ?" , new String[](usuario.getId().toString()));

        db.close();
        return cantidad > 0;

    } else {
        Long id = db.insert(TABLA_NOMBRE, null, cv);
        db.close();

            if(id.intValue() > 0){
                usuario.setId(id.intValue());
                return true;

        }

        return false;
    }

    @Override
 /*   public boolean actualizar(Usuario usuario) {

        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, usuario.getNombre());
        cv.put(CAMPO_EMAIL, usuario.getEmail());
        cv.put(CAMPO_CONTRASENA, usuario.getContrasena());
    //    cv.put(CAMPO_TIPO_USUARIO, usuario.getTipoUsuario());

        SQLiteDatabase db = conexionDb.getWritableDatabase();
        int cantidad = db.update(TABLA_USUARIO, cv, "id == ?", new String[]{usuario.getId().toString()});

        db.close();

        return cantidad > 0;

    }*/

    @Override
   // public Usuario buscar (int id) {return null;}



    @Override

    public List<Usuario> buscar ( String buscar) {

        //TODO: buscar los USUARIIOS por nombre (LIKE)

        List<Usuario> usuarios = new ArrayList<>();

        SQLiteDatabase db1 = conexionDb.getReadableDatabase();
        String[] columnas = {"id", CAMPO_NOMBRE, CAMPO_EMAIL, CAMPO_CONTRASENA , CAMPO_TIPO_USUARIO};
        Cursor cr = db1.query(TABLA_NOMBRE, columnas, null, null , null, null, null);
        cr.moveToFirst();

        while (!cr.isAfterLast()) {
            // buscamos los campos en cada registro.

            int id1 = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE) );
            String email = cr.getString(cr.getColumnIndex(CAMPO_EMAIL) );
            String contrasena = cr.getString(cr.getColumnIndex(CAMPO_CONTRASENA) );
            String tipoUsuario = cr.getString(cr.getColumnIndex(CAMPO_TIPO_USUARIO) );


           usuarios.add( new Usuario(id, nombre, email));
            cr.moveToNext();
        }
        cr.close();
        db1.close();

        return usuarios;
    }

    }

    @Override
    public Usuario buscar1(int id) {

        //TODO: buscar el usuario

        List<Usuario> usuario = new ArrayList<>();

        SQLiteDatabase db1 = conexionDb.getReadableDatabase();
        String[] columnas = {"id", CAMPO_NOMBRE, CAMPO_EMAIL, CAMPO_CONTRASENA , CAMPO_TIPO_USUARIO};
        Cursor cr = db1.query(TABLA_NOMBRE, columnas, null, null , null, null, null);
        cr.moveToFirst();

        while (!cr.isAfterLast()) {
            // buscamos los campos en cada registro.

            id = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE) );
            String email = cr.getString(cr.getColumnIndex(CAMPO_EMAIL) );



            usuario.add( new Usuario(id, nombre, email)));
            cr.moveToNext();
        }
        cr.close();
        db1.close();

        return (Usuario) usuario;
    }

           }
}
