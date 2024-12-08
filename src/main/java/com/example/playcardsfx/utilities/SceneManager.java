package com.example.playcardsfx.utilities;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

/**
 * SceneManager là  singleton class quản lý việc chuyển đổi scene trong ứng dụng.
 * <p>
 * Class tiện ích nên là singleton clas
 * <p>
 * singleton là 1 design pattern , nó đảm bảo rằng chỉ có 1 thể hiện duy nhất suốt vàng đời ứng dụng.
 * Nói cách khác là nó sẽ tạp 1 đối tượng duy nhất và toàn cục (có thể truy cập mọi nơi).
 * Nó khá hữu dụng khi sử lí những thao tác, chức năng xuyên suốt cả ứng dụng (như nhạc nền, thay con trỏ, chuyển scene ,vv.).
 * nói ra dài lắm nên ae nào chưa biết thì tự tìm hiểu nhé
 *<p>
 *
 */
public class SceneManager {

    private static SceneManager instance;
    private Stage primaryStage;

    private SceneManager() {}

    /**
     * Tạo thể hiện duy nhất của SceneManager, nói cách khác là tạo 1 đối tượng SceneManager
     *
     */
    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }



    /**
     * Thằng method phụ trách chuyển scene cũ sang scene mới
     * <p>
     * Hỗ trợ từ a tới z, chỉ cần ném fxml, css vào là xong.
     * <p>
     * Còn bonus thêm vài thứ thú vị như hiệu ứng chuyển cảnh.
     * </p>
     *
     * @param fxmlFile path đến file FXML định nghia scene mới.
     * @param cssFile  path đến file css tạo kiểu cho scene mới.
     * @param <T>      kiểu Controller của scene mới().
     *
     */
    public <T> T switchScene(String fxmlFile, String cssFile) {
        if (primaryStage == null) {
            throw new IllegalStateException("PrimaryStage chưa được thiết lập.");
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newRoot = loader.load();

            Scene currentScene = primaryStage.getScene();
            if (currentScene == null) {
                // Nấu ban đầu chưa có scene, nạp scene mới vào
                Scene scene = new Scene(newRoot);
                if (cssFile != null && getClass().getResource(cssFile) != null) {
                    scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
                }
                primaryStage.setScene(scene);
                SceneManager.getInstance().setCursor();
                primaryStage.show();
            } else {
                // Nếu đã có scene trước đó, thêm hiệu ứng chuyển cảnh cho mượt
                Parent currentRoot = currentScene.getRoot();

                // Mớ này xử lí việc chuyển cảnh , thêm scene mới vào
                FadeTransition fadeOut = new FadeTransition(Duration.millis(500), currentRoot);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.8);
                fadeOut.setOnFinished(event -> {
                    Scene scene = new Scene(newRoot);
                    if (cssFile != null && getClass().getResource(cssFile) != null) {
                        scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());

                    }
                    primaryStage.setScene(scene);
                    SceneManager.getInstance().setCursor();
                    FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newRoot);
                    fadeIn.setFromValue(0.8);
                    fadeIn.setToValue(1.0);
                    fadeIn.play();
                });
                fadeOut.play();
            }
            return loader.getController(); // Trả về controller của scene mới
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    // Xử lí thay đổi hình ảnh con trỏ
    public void setCursor() {
        if (primaryStage != null && primaryStage.getScene() != null) {
            Image cursorImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ImageSource/CursorImage/cursor.gif")));
            ImageCursor customCursor = new ImageCursor(cursorImage);
            primaryStage.getScene().setCursor(customCursor);
        }
    }
}
