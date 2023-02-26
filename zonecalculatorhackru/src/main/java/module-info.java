module com.example.zonecalculatorhackru {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zonecalculatorhackru to javafx.fxml;
    exports com.example.zonecalculatorhackru;
}