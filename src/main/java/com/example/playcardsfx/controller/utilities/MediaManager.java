package com.example.playcardsfx.utilities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

/**
 * MediaManager là singleton class, phụ trách điều khiển các thao tác liên quan đến việc phát nhạc nền và âm thanh trong ứng dụng.
 * <p>
 * Class tiện ích, cần lưu trữ trạng thái nên là singleton clas
 * <p>
 * singleton là 1 design pattern , nó đảm bảo rằng chỉ có 1 thể hiện duy nhất suốt vàng đời ứng dụng.
 *<p>
 * Nói cách khác là nó sẽ tạp 1 đối tượng duy nhất và toàn cục, quan trọng là nó có thể lưu ddc trạng thái
 *<p>
 * nó khá hữu dụng khi sử lí những thao tác, chức năng xuyên suốt cả ứng dụng (như nhạc nền, thay con trỏ, chuyển scene ,vv.)
 *<p>
 * nói ra dài lắm nên ae nào chưa biết thì tự tìm hiểu nhé.
 * <p>

 * <p>
 * Lớp này áp dụng mô hình Singleton để đảm bảo chỉ có một thể hiện duy nhất của MediaManager.
 * </p>
 */
public class MediaManager {

    // Thể hieenj của MediaManager
    private static MediaManager instance;

    // MediaPlayer để phát nhạc nền
    private MediaPlayer backgroundPlayer;

    private MediaManager() {}


    public static MediaManager getInstance() {
        if (instance == null) {
            instance = new MediaManager();
        }
        return instance;
    }

    /**
     * Method này dùng để phát nhạc nền.
     *<p>
     * Hỗ trợ từ a tới á.
     * <p>
     * Thêm tùy biến âm lượng cho gane.....
     *
     * @param filePath Pathh tới nhạc nền
     * @param volume   Âm lượng của nhac (0 tới 1.0)
     */
    public void playBackgroundMusic(String filePath, double volume) {
        stopBackgroundMusic(); // Dừng nhạc nền cũ trước khi phát nhạc mới

        try {
            URL resource = getClass().getResource(filePath);
            Media media = new Media(resource.toExternalForm());
            backgroundPlayer = new MediaPlayer(media);
            backgroundPlayer.setVolume(volume);
            backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Lặp lại nhạc nền
            backgroundPlayer.play();
        } catch (Exception e) {
            System.err.println("Lỗi khi tải nhạc nền: " + e.getMessage());
            e.printStackTrace();
        }
    }



    /**
     *Tk nãy xử lí âm thanh hiệu ứng , như nhấn vô button hay gì gì đó :V
     *
     * @param filePath Path tệp âm thanh click.
     * @param volume   Âm lượng (từ 0.0 đến 1.0).
     */
    public void playClickSound(String filePath, double volume) {
        try {
            // Tạo đối tượng MediaPlayer và phát âm thanh click
            Media media = new Media(getClass().getResource(filePath).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Lỗi khi tải âm thanh click: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Thằng này giải quyết lặt vặt như giải phóng tài nguyên, tắt nhạc nền
     */
    public void stopBackgroundMusic() {
        if (backgroundPlayer != null) {
            backgroundPlayer.stop();
            backgroundPlayer.dispose(); // Giải phóng tài nguyên
            backgroundPlayer = null;    // Đặt lại để tránh lỗi khi sử dụng sau
        }
    }
}
