package edu.westga.cs.schoolgrades.controllers;

import javafx.util.StringConverter;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.ListCell;
import edu.westga.cs.schoolgrades.model.SimpleGrade;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class GradeCellFactory implements Callback<ListView<SimpleGrade>, ListCell<SimpleGrade>>
{
    public ListCell<SimpleGrade> call(final ListView<SimpleGrade> param) {
        final TextFieldListCell<SimpleGrade> cell = (TextFieldListCell<SimpleGrade>)new TextFieldListCell();
        cell.setEditable(true);
        cell.setConverter((StringConverter)new StringConverter<SimpleGrade>() {
            public String toString(final SimpleGrade grade) {
                final String value = String.format("%.2f", grade.getValue());
                cell.setAccessibleText(value);
                return value;
            }
            
            public SimpleGrade fromString(final String text) {
                try {
                    final double value = Double.parseDouble(text);
                    return new SimpleGrade(value);
                }
                catch (NumberFormatException exc) {
                    throw new IllegalArgumentException("could not parse text");
                }
            }
        });
        return (ListCell<SimpleGrade>)cell;
    }
}
