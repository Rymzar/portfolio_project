package document;

import latex.Latexizable;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContactDetails implements Latexizable {

    private ArrayList<Tuple> contact_details;

    public ContactDetails() {
        this(new ArrayList<>());
    }

    public ContactDetails(ArrayList<Tuple> contact_details) {
        this.contact_details = contact_details;
    }

    public ArrayList<Tuple> getContact_details() {
        return contact_details;
    }

    public void setContact_details(ArrayList<Tuple> contact_details) {
        this.contact_details = contact_details;
    }


    public void addDetails(int row, String name, String details) {
        this.contact_details.add(row, new Tuple(name, details));
    }

    public void addDetails(String name, String details) {
        this.contact_details.add(new Tuple(name, details));
    }


    public void setValueAt(int row, int col, String value) {
        Tuple entry = this.contact_details.get(row);
        switch(col) {
            case 0:
                entry.setKey(value);
                break;
            case 1:
                entry.setValue(value);
                break;
            default:
                throw new RuntimeException("Невалидная длина колонки");
        }
    }

    public String getValueAt(int row, int col) {

        Map.Entry<String, String> entry = this.contact_details.get(row);
        switch(col) {
            case 0:
                return entry.getKey();
            case 1:
                return entry.getValue();
            default:
                throw new RuntimeException("Невалидная длина колонки");
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Контактные данные{" +
                "contact_details=");

        for(Map.Entry<String, String> entry : this.contact_details) {
            result.append(entry.getKey() + ":" +  entry.getValue());
        }
        result.append('}');

        return result.toString();
    }

    @Override
    public String toLatex() {
        String startFormat =
                "\\begin{flushleft}\n" +
                        "\\begin{itemize}";
        StringBuilder detailsFormatting = new StringBuilder();
        for(Map.Entry<String,String> s : contact_details) {
            detailsFormatting.append("\\item[] " + s.getKey() + ": " + s.getValue() );
        }

        String detailsFormat = detailsFormatting.toString();
        String endFormat = "\\end{itemize}\n" +
                "\\end{flushleft}";
        return startFormat + detailsFormat + endFormat;
    }
}
