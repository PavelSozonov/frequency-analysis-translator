package text.processor;

class TranslationException extends RuntimeException {
    TranslationException(Exception e) {
        super(e);
    }

    TranslationException(String s) {
        super(s);
    }
}
