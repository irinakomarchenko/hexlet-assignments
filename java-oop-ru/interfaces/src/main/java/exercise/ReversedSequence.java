package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final String text;
    public ReversedSequence(String text) {
        this.text = text;
    }


     @Override
    public int length() {
        return text.length();
    }

    @Override
    public char charAt(int index) {
        return text.charAt(text.length()- 1 - index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {

        String reversed = new StringBuilder(text).reverse().toString();
        return reversed.subSequence(start,end);
    }

    @Override
    public String toString() {
        return new StringBuilder(text).reverse().toString();
    }
}

// END
