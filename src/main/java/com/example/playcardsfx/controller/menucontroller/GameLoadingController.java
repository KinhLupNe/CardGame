package com.example.playcardsfx.controller.menucontroller;

import com.example.playcardsfx.controller.utilities.SceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
/*
Quản lí loading đầu gane
 */
public class GameLoadingController implements Initializable {
    public static final int MAX_STEPS = 12; // Hằng số số bước tối đa của thanh bar
    public static final double TIME_STEP = 0.5;//Tốc độ chạy sau mỗi bước của thanh bar

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ImageView runAnime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (progressBar != null && runAnime != null) {

            int numSteps = MAX_STEPS; // Số bước, bạn có thể điều chỉnh tùy theo yêu cầu
            double stepSize = 1.0 / numSteps; // Tính toán giá trị tăng dần của progress bar

            Timeline timeline = new Timeline();

            // Đảm bảo giao diện đã hoàn tất để chạy
            Platform.runLater(() -> {
                double widthBar = progressBar.getPrefWidth();

                // Tạo các KeyFrame cho Timeline
                for (int i = 0; i <= numSteps; i++) {
                    final double progress = i * stepSize;
                    final double time = i * TIME_STEP;

                    KeyFrame keyFrame = new KeyFrame(Duration.seconds(time), e -> {
                        progressBar.setProgress(progress);
                        runAnime.setLayoutX(runAnime.getLayoutX() + widthBar*1.05 / numSteps);
                    });

                    timeline.getKeyFrames().add(keyFrame);
                }

                // Khi Timeline kết thúc
                timeline.setOnFinished(e -> {
                    // Chuyển cảnh sau khi timeline kết thúc
                    StartMenuController startMenuController = SceneManager.getInstance()
                            .switchScene("/com/example/playcardsfx/fxmlfile/StartMenuScene.fxml", "/com/example/playcardsfx/stylefile/Style.css");
                });

                // Bắt đầu timeline
                timeline.setCycleCount(1); // Timeline chạy 1 lần
                timeline.play(); // Bắt đầu Timeline
            });

        } else {
            System.out.println("ProgressBar or runAnime is null!");
        }
    }
}
