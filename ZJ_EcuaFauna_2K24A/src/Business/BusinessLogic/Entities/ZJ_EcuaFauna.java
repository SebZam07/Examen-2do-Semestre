package Business.BusinessLogic.Entities;

public class ZJ_EcuaFauna {
    private static ZJ_EcuaFauna instance;
    private static String nombre;

    // hacer el constructor privado para evitar la inicializado
    private ZJ_EcuaFauna() { }
    protected ZJ_EcuaFauna(ZJ_EcuaFauna ZJ_EcuaFauna) { 
        if (ZJ_EcuaFauna != null)
            instance = ZJ_EcuaFauna;
    }

    public static ZJ_EcuaFauna getInstance(String nombre) {
        if (instance == null){
            instance = new ZJ_EcuaFauna();
            instance.setNombre(nombre);
        }
        return instance;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
