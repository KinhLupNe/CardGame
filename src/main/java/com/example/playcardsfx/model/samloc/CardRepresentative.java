package com.example.playcardsfx.model.samloc;

public class CardRepresentative {
    private int length; // Số lượng lá bài trong tổ hợp
    private int number; // Số đặc trưng (số lớn nhất hoặc đại diện)
    private int type;   // Loại tổ hợp bài (1 = lẻ, 2 = đôi, 3 = tam, 4 = tứ, 5 = dây)

    public CardRepresentative(int length, int number, int type) {
        this.length = length;
        this.number = number;
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public int getNumber() {
        return number;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "(" + length + ", " + number + ", " + type + ")";
    }

    public int compareTo(CardRepresentative currentHand) {
        // So sánh loại tổ hợp bài (type)
        if (this.type != currentHand.type) {
            return Integer.compare(this.type, currentHand.type);
        }

        // Nếu loại tổ hợp giống nhau, so sánh độ dài (length)
        if (this.length != currentHand.length) {
            return Integer.compare(this.length, currentHand.length);
        }

        // Nếu độ dài giống nhau, so sánh số đại diện (number)
        return Integer.compare(this.number, currentHand.number);
    }

}

