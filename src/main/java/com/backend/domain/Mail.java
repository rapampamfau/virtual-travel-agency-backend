package com.backend.domain;

public final class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;

    private Mail(String mailTo, String subject, String message) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
    }

    public static class MailBuilder {
        private String mailTo;
        private String subject;
        private String message;

        public MailBuilder mailTo(String mailTo) {
            this.mailTo = mailTo;
            return this;
        }

        public MailBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailBuilder message(String message) {
            this.message = message;
            return this;
        }

        public Mail build() {
            return new Mail(mailTo, subject, message);
        }
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
