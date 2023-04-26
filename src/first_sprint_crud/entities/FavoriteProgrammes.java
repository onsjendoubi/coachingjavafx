/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.entities;

/**
 *
 * @author USER
 */
public class FavoriteProgrammes {
    private int id;
    private Programme prog;

    public FavoriteProgrammes(int id, Programme prog) {
        this.id = id;
        this.prog = prog;
    }
    public FavoriteProgrammes()
    {
        
    }

    public FavoriteProgrammes(Programme prog) {
        this.prog = prog;
    }

    public int getId() {
        return id;
    }

    public Programme getProg() {
        return prog;
    }

    public void setProg(Programme prog) {
        this.prog = prog;
    }

    @Override
    public String toString() {
        return "FavoriteProgrammes{" + "id=" + id + ", prog=" + prog + '}';
    }
    
    
}
