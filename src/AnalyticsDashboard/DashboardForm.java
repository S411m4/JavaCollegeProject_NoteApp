/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyticsDashboard;

import AnalyticsDashboard.DateCalculator;
import DatabaseHelpers.DatabaseHelper;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import raven.chart.ChartLegendRenderer;
import raven.chart.bar.HorizontalBarChart;
import raven.chart.data.category.DefaultCategoryDataset;
import raven.chart.data.pie.DefaultPieDataset;
import raven.chart.line.LineChart;
import raven.chart.pie.PieChart;

/**
 *
 * @author Dell
 */
public class DashboardForm extends SimpleForm {

    public DashboardForm() {
        init();
    }

    @Override
    public void formRefresh() {
        //lineChart.startAnimation();
        pieChart1.startAnimation();
        pieChart2.startAnimation();
        pieChart3.startAnimation();

    }

    @Override
    public void formInitAndOpen() {

    }

    @Override
    public void formOpen() {
    }

    private void init() {
        setLayout(new MigLayout("wrap,fill,gap 10", "fill"));

        // Title bar with close button
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(new Color(30, 30, 30)); // Dark color for the title bar
        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setForeground(Color.WHITE); // White text
        JButton closeButton = new JButton("X");
        closeButton.addActionListener((ActionEvent event) -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose(); // Dispose the frame when button is clicked
        });

        // Style the close button
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setOpaque(false);

        titleBar.add(titleLabel, BorderLayout.CENTER);
        titleBar.add(closeButton, BorderLayout.EAST);
        add(titleBar, "span, growx"); // Ensure the title bar spans the entire width

        createPieChart();
//        createLineChart();
    }

    private void createPieChart() {
        pieChart1 = new PieChart();
        JLabel header1 = new JLabel("Today Tasks");
        header1.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart1.setHeader(header1);
        pieChart1.getChartColor().addColor(Color.decode("#007E33"), Color.decode("#CC0000"));
        pieChart1.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        pieChart1.setDataset(getTodayTaskDoneUnDone());
        add(pieChart1, "split 3,height 290");

        pieChart2 = new PieChart();
        JLabel header2 = new JLabel("Overall Tasks");
        header2.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart2.setHeader(header2);
        pieChart2.getChartColor().addColor(Color.decode("#00BA38"), Color.decode("#F87666D"));
        pieChart2.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        pieChart2.setDataset(getOverallTasksDoneUnDone());
        add(pieChart2, "height 290");

        pieChart3 = new PieChart();
        JLabel header3 = new JLabel("Notes Tags");
        header3.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart3.setHeader(header3);

        pieChart3.getChartColor().addColor(Color.decode("#FFB900"), Color.decode("#0063B1"), Color.decode("#68768A"), Color.decode("#D13438"), Color.decode("#744DA9"), Color.decode("#107C10"));

        pieChart3.setChartType(PieChart.ChartType.DONUT_CHART);
        pieChart3.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        pieChart3.setDataset(getNoteTagsData());
        add(pieChart3, "height 290");  
    }




    private DefaultPieDataset getNoteTagsData() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

        var databaseData = DatabaseHelper.getNotesCountByTag();

        for (var key : databaseData.keySet()) {
//            System.out.println("key: " + key + ", value: " + databaseData.get(key));

            int value = databaseData.get(key);

            if (value > 0) {
                dataset.addValue(key, value);
            }
        }
        return dataset;
    }

    public DefaultPieDataset getOverallTasksDoneUnDone() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

        var databaseData = DatabaseHelper.getOverallTaskCounts();
        for (var key : databaseData.keySet()) {
//            System.out.println("key: " + key + ", value: " + databaseData.get(key));

            int value = databaseData.get(key);

            if (value > 0) {
                dataset.addValue(key, value);
            }
        }
        return dataset;
    }

    public DefaultPieDataset getTodayTaskDoneUnDone() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

        var databaseData = DatabaseHelper.getTodaysTaskCounts();
        for (var key : databaseData.keySet()) {
            System.out.println("key: " + key + ", value: " + databaseData.get(key));

            int value = databaseData.get(key);

            if (value > 0) {
                dataset.addValue(key, value);
            }
        }
        return dataset;
    }
    



    private PieChart pieChart1;
    private PieChart pieChart2;
    private PieChart pieChart3;

}
