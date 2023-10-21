package clienteescritorionutricion.modelo;

import clienteescritorionutricion.modelo.pojo.Paciente;

public class JsonUtility {

    public static String createJson(Paciente paciente) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{")
                .append(addChild("nombre", paciente.getNombre(), true))
                .append(addChild("apellidoPaterno", paciente.getApellidoPaterno(), true))
                .append(addChild("apellidoMaterno", paciente.getApellidoMaterno(), true))
                .append(addChild("fechaNacimiento", paciente.getFechaNacimiento(), true))
                .append(addChild("sexo", paciente.getSexo(), true))
                .append(addChild("peso", paciente.getPeso() + "", true))
                .append(addChild("estatura", paciente.getEstatura() + "", true))
                .append(addChild("tallaInicial", paciente.getTallaInicial() + "", true))
                .append(addChild("email", paciente.getEmail(), true))
                .append(addChild("telefono", paciente.getTelefono(), true))
                .append(addChild("password", paciente.getPassword(), true))
                .append(addChild("fotografia", "", true))
                .append(addChild("idDomicilio", paciente.getIdDomicilio() + "", true))
                .append(addChild("idMedico", paciente.getIdMedico() + "", false))
                .append("}");

        return jsonBuilder.toString();
    }

    private static String addChild(String key, String val, boolean comma) {
        return String.format("\"%s\":\"%s\"%s", key, val, comma ? "\"," : "");
    }

    private static String getPayload() {
        return "{\n"
                + "    \"nombre\": \"Fátima\",\n"
                + "    \"apellidoPaterno\": \"Cigarroa\",\n"
                + "    \"apellidoMaterno\": \"Reyes\",\n"
                + "    \"fechaNacimiento\": \"2002-07-20\",\n"
                + "    \"sexo\": \"F\",\n"
                + "    \"peso\": 60.0,\n"
                + "    \"estatura\": 1.5,\n"
                + "    \"tallaInicial\": 50,\n"
                + "    \"email\": \"fatima23@gmail.com\",\n"
                + "    \"telefono\": \"2254879634\",\n"
                + "    \"password\": \"fatima23\",\n"
                + "    \"idMedico\": 2\n"
                + "}";
    }

}
