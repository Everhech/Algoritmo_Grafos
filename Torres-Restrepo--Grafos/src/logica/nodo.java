package logica;

/**
 *
 * @author Andres Torres Ciceri y Edwin Orlando Restrepo M.
 */
public class nodo {

    String val;
    String[][] adya;

    public nodo(String val, String[][] adya) {
        this.val = val;
        this.adya = adya;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String[][] getAdya() {
        return adya;
    }

    public void setAdya(String[][] adya) {
        this.adya = adya;
    }
}
