package clienteescritorionutricion.modelo;
import clienteescritorionutricion.modelo.pojo.Paciente;
import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtility {
    
 public static String createJson(Paciente paciente) {
    StringBuilder jsonBuilder = new StringBuilder();
    jsonBuilder.append("{")
               .append(addChild("nombre", paciente.getNombre(), true))
               .append(addChild("apellidoPaterno", paciente.getApellidoPaterno(),true))
               .append(addChild("apellidoMaterno", paciente.getApellidoMaterno(), false))
               .append("}");

    return jsonBuilder.toString();
}

private static String addChild(String key, String val, boolean comma) {
    return String.format("\"%s\":\"%s\"%s", key, val, comma ? "\",":"");
}

private static String toJson() {
    Gson gson = new Gson();
    ObjectMapper mapper = new ObjectMapper();
    return gson.toJson(new Paciente());
    
    
 }
}
