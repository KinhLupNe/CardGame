package com.example.playcardsfx.model.gamelogic.samloc;




import com.example.playcardsfx.model.enities.Card;

import java.util.*;

// Các phương thức hỗ trợ kiểm tra và tạo đại diện
// Các phương thức hỗ trợ kiểm tra và tạo đại diện
public class CardHelper {
    private String rank;
    private List<Integer> numbers;

    // Tạo đại diện từ danh sách các lá bài
    public CardRepresentative generateRepresentative(List<Card> cards) {
        if (cards == null || cards.isEmpty()) return null;

        // Sắp xếp bài theo số
        // Lấy danh sách số bài
        numbers = new ArrayList<>();
        for (Card card : cards) {
            rank = card.getRank();
            int value;

            // Chuyển đổi rank sang số
            if (rank.equals("J")) {
                value = 11;
            } else if (rank.equals("Q")) {
                value = 12;
            } else if (rank.equals("K")) {
                value = 13;
            } else if (rank.equals("A")) {
                value = 14;
            } else if (rank.equals("2")) {
                value = 15;
            } else {
                value = Integer.parseInt(rank);
            }
            numbers.add(value);
        }
        numbers.sort(Integer::compareTo);

        // Sắp xếp cards theo giá trị số của rank
        cards.sort(Comparator.comparingInt(card -> {
            String rank = card.getRank();
            if (rank.equals("J")) return 11;
            else if (rank.equals("Q")) return 12;
            else if (rank.equals("K")) return 13;
            else if (rank.equals("A")) return 14;
            else if (rank.equals("2")) return 15;
            else return Integer.parseInt(rank);
        }));

        // Số lượng lá bài
        int count1 = cards.size();

        if (count1 == 1) {
            // Trường hợp 1 lá bài lẻ
            return new CardRepresentative(count1, Collections.max(numbers), 1);
        } else if (count1 == 2 && numbers.get(0).equals(numbers.get(1))) {
            // Trường hợp đôi
            return new CardRepresentative(count1, Collections.max(numbers), 2);
        } else if (count1 == 3 && isTriple(numbers)) {
            // Trường hợp tam
            return new CardRepresentative(count1, Collections.max(numbers), 3);
        } else if (count1 == 4 && isQuad(numbers)) {
            // Trường hợp tứ
            return new CardRepresentative(count1, Collections.max(numbers), 4);
        } else if (count1 >= 3 && isStraight(numbers)) {
            // Trường hợp dây (đại diện là số lớn nhất trong dãy)
            return new CardRepresentative(count1, Collections.max(numbers), 5);
        } else {
            return null; // Không hợp lệ
        }
    }

    // Kiểm tra xem một bộ có phải là tam (3 lá giống nhau) hay không
    private static boolean isTriple(List<Integer> numbers) {
        return numbers.stream().distinct().count() == 1 && numbers.size() == 3;
    }

    // Kiểm tra xem một bộ có phải là tứ (4 lá giống nhau) hay không
    private static boolean isQuad(List<Integer> numbers) {
        return numbers.stream().distinct().count() == 1 && numbers.size() == 4;
    }

    // Kiểm tra xem danh sách số có phải là dây hợp lệ không
    private static boolean isStraight(List<Integer> numbers) {
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) != numbers.get(i - 1) + 1) {
                return false;
            }
        }
        return true;
    }
}