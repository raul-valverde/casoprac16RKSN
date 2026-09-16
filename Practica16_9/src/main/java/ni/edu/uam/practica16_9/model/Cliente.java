package ni.edu.uam.practica16_9.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
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
