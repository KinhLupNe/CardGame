module com.example.playcardsfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    // Mở gói cho JavaFX để tải FXML và sử dụng reflection
    opens com.example.playcardsfx to javafx.fxml;

    opens com.example.playcardsfx.controller.menucontroller to javafx.fxml;
    // Xuất các gói nếu cần thiết
    exports com.example.playcardsfx;


    exports com.example.playcardsfx.controller.menucontroller;
    exports com.example.playcardsfx.utilities;
    opens com.example.playcardsfx.utilities to javafx.fxml;
    exports com.example.playcardsfx.model.samloc;
    opens com.example.playcardsfx.model.samloc to javafx.fxml;
    exports com.example.playcardsfx.model.bacay;
    opens com.example.playcardsfx.model.bacay to javafx.fxml;
    exports com.example.playcardsfx.controller.gameplaycontroller;
    opens com.example.playcardsfx.controller.gameplaycontroller to javafx.fxml;
    exports com.example.playcardsfx.model.enities;


}
