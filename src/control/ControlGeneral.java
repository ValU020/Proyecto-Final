package control;

public class ControlGeneral {
    
    private ControlEvento controlEv;
    private ControlUsuario controlUs;
    private ControlTicket controlTk;
    
    public ControlGeneral() {
        controlEv = new ControlEvento();
        controlUs = new ControlUsuario();
        controlTk = new ControlTicket();
    }

    // MÉTODOS DE USUARIO
    public void guardarNuevoUsuario(String nombre, String contraseña, boolean esComprador){
        controlUs.registrarUsuario(nombre, contraseña, esComprador);
    }
    
    public boolean buscarUsuario(String nombre, String contraseña) {
        return controlUs.validarInicio(nombre, contraseña);
    }
    
    public boolean validarRegistro(String nombre) {
    		return controlUs.validarRegistro(nombre);
    }
    
    public void añadirId(String nombre, String contraseña, String id) {
        controlUs.guardarId(nombre, contraseña, id);
    }
    
    public boolean definirInstancia(String nombre) {
        return controlUs.verificarInstancia(nombre);
    }
    
    // MÉTODOS DE TICKETS
    public String crearNuevoTicket(String idEvento, String nombreComprador, double precio, int numeroSilla) 
            throws Exception {
        return controlTk.crearTicket(idEvento, nombreComprador, precio, numeroSilla);
    }
    
    public ControlTicket getControlTicket() {
        return controlTk;
    }
    
    public ControlEvento getControlEvento() {
        return controlEv;
    }
    
    public ControlUsuario getControlUsuario() {
        return controlUs;
    }
}