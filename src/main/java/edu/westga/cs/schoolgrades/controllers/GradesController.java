package edu.westga.cs.schoolgrades.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.westga.cs.schoolgrades.model.AverageOfGradesStrategy;
import edu.westga.cs.schoolgrades.model.CompositeGrade;
import edu.westga.cs.schoolgrades.model.DropLowestStrategy;
import edu.westga.cs.schoolgrades.model.Grade;
import edu.westga.cs.schoolgrades.model.GradeCalculationStrategy;
import edu.westga.cs.schoolgrades.model.SimpleGrade;
import edu.westga.cs.schoolgrades.model.SumOfGradesStrategy;
import edu.westga.cs.schoolgrades.model.WeightedGrade;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class GradesController
{
    private static final double QUIZ_WEIGHT = 0.2;
    private static final double HOMEWORK_WEIGHT = 0.3;
    private static final double EXAM_WEIGHT = 0.5;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField finalGradeTextField;
    @FXML
    private ListView<SimpleGrade> quizzesListView;
    @FXML
    private TextField quizSubtotalTextField;
    @FXML
    private ListView<SimpleGrade> homeworkListView;
    @FXML
    private TextField homeworkSubtotalTextField;
    @FXML
    private ListView<SimpleGrade> examsListView;
    @FXML
    private TextField examSubtotalTextField;
    @FXML
    private Button recalculateButton;
    private ObservableList<SimpleGrade> quizGrades;
    private ObservableList<SimpleGrade> homeworkGrades;
    private ObservableList<SimpleGrade> examGrades;
    private DoubleProperty quizSubtotalProperty;
    private DoubleProperty homeworkSubtotalProperty;
    private DoubleProperty examSubtotalProperty;
    private DoubleProperty finalGradeProperty;
    
    @FXML
    void initialize() {
        assert this.finalGradeTextField != null : "fx:id=\"finalGradeTextField\" was not injected: check your FXML file 'GradesGui.fxml'.";
        assert this.recalculateButton != null : "fx:id=\"recalculateButton\" was not injected: check your FXML file 'GradesGui.fxml'.";
        assert this.quizzesListView != null : "fx:id=\"quizzesListView\" was not injected: check your FXML file 'GradesGui.fxml'.";
        assert this.quizSubtotalTextField != null : "fx:id=\"quizSubtotalTextField\" was not injected: check your FXML file 'GradesGui.fxml'.";
        assert this.homeworkListView != null : "fx:id=\"homeworkListView\" was not injected: check your FXML file 'GradesGui.fxml'.";
        assert this.homeworkSubtotalTextField != null : "fx:id=\"homeworkSubtotalTextField\" was not injected: check your FXML file 'GradesGui.fxml'.";
        assert this.examsListView != null : "fx:id=\"examsListView\" was not injected: check your FXML file 'GradesGui.fxml'.";
        assert this.examSubtotalTextField != null : "fx:id=\"examSubtotalTextField\" was not injected: check your FXML file 'GradesGui.fxml'.";
        this.setupGui();
    }
    
    private void setupGui() {
        this.setupState();
        this.setupQuizListView();
        this.setupHomeworkListView();
        this.setupExamListView();
        this.setupQuizSubtotalTextField();
        this.setupHomeworkSubtotalTextField();
        this.setupExamSubtotalTextField();
        this.setupFinalGradeTextField();
        this.populateWithTestGrades();
    }
    
    private void recalculateAll() {
        this.calculatFinalGradeSubtotal();
    }
    
    private Grade calculatFinalGradeSubtotal() {
        final Grade quizSubtotal = new WeightedGrade(this.calculateQuizSubtotal(), 0.2);
        final Grade homeworkSubtotal = new WeightedGrade(this.calculateHomeworkSubtotal(), 0.3);
        final Grade examSubtotal = new WeightedGrade(this.calculateExamSubtotal(), 0.5);
        final CompositeGrade compositeGrade = new CompositeGrade(new SumOfGradesStrategy());
        compositeGrade.add(quizSubtotal);
        compositeGrade.add(homeworkSubtotal);
        compositeGrade.add(examSubtotal);
        this.finalGradeProperty.set(compositeGrade.getValue());
        return compositeGrade;
    }
    
    private Grade calculateExamSubtotal() {
        final GradeCalculationStrategy strategy = new AverageOfGradesStrategy();
        final CompositeGrade compositeGrade = new CompositeGrade(strategy);
        compositeGrade.addAll((List<? extends Grade>)this.examGrades);
        this.examSubtotalProperty.set(compositeGrade.getValue());
        return compositeGrade;
    }
    
    private Grade calculateHomeworkSubtotal() {
        final GradeCalculationStrategy strategy = new DropLowestStrategy(new AverageOfGradesStrategy());
        final CompositeGrade compositeGrade = new CompositeGrade(strategy);
        compositeGrade.addAll((List<? extends Grade>)this.homeworkGrades);
        this.homeworkSubtotalProperty.set(compositeGrade.getValue());
        return compositeGrade;
    }
    
    private Grade calculateQuizSubtotal() {
        final GradeCalculationStrategy strategy = new SumOfGradesStrategy();
        final CompositeGrade compositeGrade = new CompositeGrade(strategy);
        compositeGrade.addAll((List<? extends Grade>)this.quizGrades);
        this.quizSubtotalProperty.set(compositeGrade.getValue());
        return compositeGrade;
    }
    
    private void setupFinalGradeTextField() {
        this.finalGradeTextField.textProperty().bindBidirectional((Property)this.finalGradeProperty, (StringConverter)new NumberStringConverter());
    }
    
    private void setupExamSubtotalTextField() {
        this.examSubtotalTextField.textProperty().bindBidirectional((Property)this.examSubtotalProperty, (StringConverter)new NumberStringConverter());
    }
    
    private void setupHomeworkSubtotalTextField() {
        this.homeworkSubtotalTextField.textProperty().bindBidirectional((Property)this.homeworkSubtotalProperty, (StringConverter)new NumberStringConverter());
    }
    
    private void setupQuizSubtotalTextField() {
        this.quizSubtotalTextField.textProperty().bindBidirectional((Property)this.quizSubtotalProperty, (StringConverter)new NumberStringConverter());
    }
    
    private void populateWithTestGrades() {
        for (int i = 0; i < 2; ++i) {
            final SimpleGrade grade = new SimpleGrade(i * 10);
            this.quizGrades.add((SimpleGrade)grade);
        }
        for (int i = 5; i >= 1; --i) {
            final SimpleGrade grade = new SimpleGrade(i * 20);
            this.homeworkGrades.add((SimpleGrade)grade);
        }
        this.examGrades.add((SimpleGrade)new SimpleGrade(99.0));
        this.examGrades.add((SimpleGrade)new SimpleGrade(67.0));
        this.examGrades.add((SimpleGrade)new SimpleGrade(73.0));
        this.examGrades.add((SimpleGrade)new SimpleGrade(88.0));
        this.quizSubtotalProperty.set(88.5);
        this.homeworkSubtotalProperty.set(9.85);
        this.examSubtotalProperty.set(42.64);
        this.finalGradeProperty.set(75.03);
    }
    
    private void setupState() {
    	List<SimpleGrade> list = new ArrayList<SimpleGrade>();
		  this.quizGrades =
		  (ObservableList<SimpleGrade>)FXCollections.observableArrayList(list);
		  this.homeworkGrades =
		  (ObservableList<SimpleGrade>)FXCollections.observableArrayList(list);
		  this.examGrades =
		  (ObservableList<SimpleGrade>)FXCollections.observableArrayList(list);
		  this.finalGradeProperty = (DoubleProperty)new SimpleDoubleProperty();
        this.examSubtotalProperty = (DoubleProperty)new SimpleDoubleProperty();
        this.homeworkSubtotalProperty = (DoubleProperty)new SimpleDoubleProperty();
        this.quizSubtotalProperty = (DoubleProperty)new SimpleDoubleProperty();
    }
    
    private void setupQuizListView() {
        this.quizzesListView.setItems((ObservableList)this.quizGrades);
        Callback value = (Callback)new GradeCellFactory();
		this.quizzesListView.setCellFactory(value);
    }
    
    private void setupHomeworkListView() {
        this.homeworkListView.setItems((ObservableList)this.homeworkGrades);
        Callback value = (Callback)new GradeCellFactory();
		this.homeworkListView.setCellFactory(value);
    }
    
    private void setupExamListView() {
        this.examsListView.setItems((ObservableList)this.examGrades);
        Callback value = (Callback)new GradeCellFactory();
		this.examsListView.setCellFactory(value);
    }
    
    @FXML
    void onRecalculateButtonClick(final ActionEvent event) {
        this.recalculateAll();
    }
    
    @FXML
    void onAddExamMenuItemClick(final ActionEvent event) {
        this.examGrades.add((SimpleGrade)new SimpleGrade(0.0));
        this.sendFocusToLastCell(this.examsListView);
    }
    
    @FXML
    void onAddHomeworkMenuItemClick(final ActionEvent event) {
        this.homeworkGrades.add((SimpleGrade)new SimpleGrade(0.0));
        this.sendFocusToLastCell(this.homeworkListView);
    }
    
    @FXML
    void onAddQuizMenuItemClick(final ActionEvent event) {
        this.quizGrades.add((SimpleGrade)new SimpleGrade(0.0));
        this.sendFocusToLastCell(this.quizzesListView);
    }
    
    private void sendFocusToLastCell(final ListView<SimpleGrade> listView) {
        listView.getSelectionModel().selectLast();
        final int index = listView.getSelectionModel().getSelectedIndex();
        listView.requestFocus();
        listView.getFocusModel().focus(index);
    }
}
