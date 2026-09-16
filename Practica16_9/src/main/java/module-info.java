module ni.edu.uam.practica16_9 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.practica16_9 to javafx.fxml;
    exports ni.edu.uam.practica16_9;
}