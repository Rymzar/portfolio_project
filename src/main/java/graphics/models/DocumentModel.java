package graphics.models;

import document.Document;
import graphics.controllers.TasterDocumentListener;
import graphics.controllers.TitleDocumentListener;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DocumentModel {
    private Document document;
    private javax.swing.text.Document titleDocument;
    private javax.swing.text.Document tasterDocument;

    public DocumentModel(Document document) {
        this.document = document;
        this.titleDocument = new PlainDocument();
        try {
            this.titleDocument.insertString(0, document.getTitle(), null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        this.titleDocument.addDocumentListener(new TitleDocumentListener(this.document));

        this.tasterDocument = new PlainDocument();
        try {
            this.tasterDocument.insertString(0, document.getTaster(), null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        this.tasterDocument.addDocumentListener(new TasterDocumentListener(this.document));
    }

    public javax.swing.text.Document getTitleModel() {
        return titleDocument;
    }

    public javax.swing.text.Document getTasterModel() {
        return tasterDocument;
    }

    public Document getDocument() {
        return document;
    }
}

