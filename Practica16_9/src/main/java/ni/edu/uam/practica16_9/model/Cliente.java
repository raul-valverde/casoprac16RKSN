package ni.edu.uam.practica16_9.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
//En este caso se remueve getter y setter pq Data ya los contiene dentro y mejroa la estetica y orden del programa
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private String nombres;
    private String apellidos;
    private String tipoCliente;
    private String ciudad;
    private String tipoSolicitud;
    private String servicios;
    private String fotoPath;
    private LocalDate fechaNacimiento;

    public String getNombreCompleto() {
        return (nombres != null ? nombres : "") + " " + (apellidos != null ? apellidos : "");
    }
}